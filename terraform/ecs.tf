module "ecs" {
  source = "terraform-aws-modules/ecs/aws"

  cluster_name = "iamind-cluster"

  fargate_capacity_providers = {
    FARGATE = {
      default_capacity_provider_strategy = {
        weight = 10
      }
    }
    FARGATE_SPOT = {
      default_capacity_provider_strategy = {
        weight = 90
      }
    }
  }

  services = {
    session-management = {
      cpu    = 1024
      memory = 4096

      container_definitions = {
        session-management = {
          cpu       = 512
          memory    = 1024
          essential = true
          image     = "brunogino/iamind-session_management:latest"
          port_mappings = [
            {
              name          = "session-management"
              containerPort = 5000
              protocol      = "tcp"
            }
          ]
          readonly_root_filesystem = false

          enable_cloudwatch_logging = true
          memory_reservation        = 100
        }
      }

      load_balancer = {
        service = {
          target_group_arn = aws_lb_target_group.iamind_alb_tg_https.arn
          container_name   = "session-management"
          container_port   = 5000
        }
      }

      subnet_ids = [aws_subnet.iamind_subnet_public1.id, aws_subnet.iamind_subnet_public2.id]
      security_group_rules = {
        alb_ingress_3000 = {
          type                     = "ingress"
          from_port                = 80
          to_port                  = 5000
          protocol                 = "tcp"
          description              = "Service port"
          source_security_group_id = aws_subnet.iamind_subnet_public1.id
        }
        egress_all = {
          type        = "egress"
          from_port   = 0
          to_port     = 0
          protocol    = "-1"
          cidr_blocks = ["0.0.0.0/0"]
        }
      }
    }
  }

  tags = local.default_tags
}