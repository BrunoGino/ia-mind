resource "aws_ecs_cluster" "iamind_ecs_cluster" {
  name = "iamind_ecs_cluster"

  setting {
    name  = "containerInsights"
    value = "enabled"
  }

  tags = local.default_tags
}

resource "aws_ecs_cluster_capacity_providers" "iamind_capacity_provider" {
  cluster_name = aws_ecs_cluster.iamind_ecs_cluster.name

  capacity_providers = ["FARGATE", "FARGATE_SPOT"]


  default_capacity_provider_strategy {
    weight            = 80
    base              = 1
    capacity_provider = "FARGATE_SPOT"
  }

  default_capacity_provider_strategy {
    weight            = 20
    capacity_provider = "FARGATE"
  }
}

data "template_file" "session_management_container_definitions" {
  template = file("task-definitions/task-template.json")

  vars = {
    name  = "session_management_task"
    image = "brunogino/iamind-session_management:latest"
  }
}

resource "aws_ecs_task_definition" "session_management_task_definition" {
  family                   = "session_management_task"
  container_definitions    = data.template_file.session_management_container_definitions.rendered
  requires_compatibilities = ["FARGATE"]
  network_mode             = "awsvpc"
  execution_role_arn       = aws_iam_role.deployment_role.arn
  task_role_arn            = aws_iam_role.ecs_tasks_role.arn
  memory                   = 512
  cpu                      = 256
  tags                     = local.default_tags

}

resource "aws_ecs_service" "session_management_service" {
  name            = "session_management_service"
  cluster         = aws_ecs_cluster.iamind_ecs_cluster.id
  task_definition = aws_ecs_task_definition.session_management_task_definition.arn
  launch_type     = "FARGATE"
  desired_count   = 2

  network_configuration {
    security_groups = [aws_security_group.iamind_sg_tls_http.id]
    subnets         = [aws_subnet.iamind_subnet_public1.id]
  }

  load_balancer {
    target_group_arn = aws_lb_target_group.iamind_alb_tg_https.arn
    container_name   = "session_management_task"
    container_port   = 8080
  }

  tags = local.default_tags

}
