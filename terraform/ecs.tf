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
    weight            = 100
    base              = 1
    capacity_provider = "FARGATE_SPOT"
  }

  default_capacity_provider_strategy {
    weight            = 0
    capacity_provider = "FARGATE"
  }
}

resource "aws_ecs_task_definition" "user_ms_task_definition" {
  family = "user_ms_task"
  container_definitions = jsonencode([
    {
      name      = "user_ms_task",
      image     = "brunogino/iamind-user-ms:latest",
      essential = true,
      logConfiguration = {
        logDriver     = "awslogs",
        secretOptions = [],
        options = {
          awslogs-group         = "/ecs/user_ms_task-logs",
          awslogs-region        = "eu-west-1",
          awslogs-stream-prefix = "ecs"
        }
      },
      portMappings = [
        {
          containerPort = 8080,
          hostPort      = 8080
        }
      ],
      repositoryCredentials = {
        credentialsParameter = "arn:aws:secretsmanager:eu-west-1:108782061116:secret:iamind_docker_hub_secret-XQ0rOK"
      }
    }
  ])
  requires_compatibilities = ["FARGATE"]
  network_mode             = "awsvpc"
  execution_role_arn       = aws_iam_role.deployment_role.arn
  task_role_arn            = aws_iam_role.ecs_tasks_role.arn
  memory                   = 512
  cpu                      = 256
  tags                     = local.default_tags

}

resource "aws_ecs_service" "user_ms_service" {
  depends_on      = [aws_ecs_task_definition.user_ms_task_definition]
  name            = "user_ms_service"
  cluster         = aws_ecs_cluster.iamind_ecs_cluster.id
  task_definition = aws_ecs_task_definition.user_ms_task_definition.arn
  launch_type     = "FARGATE"
  desired_count   = 1

  network_configuration {
    assign_public_ip = true
    security_groups  = [aws_security_group.iamind_sg_tls_http.id]
    subnets          = [aws_subnet.iamind_subnet_public1.id]
  }

  load_balancer {
    target_group_arn = aws_lb_target_group.iamind_user_ms_tg.arn
    container_name   = "user_ms_task"
    container_port   = 8080
  }

  tags = local.default_tags

}

