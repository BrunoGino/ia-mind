module "ecs" {
  source = "terraform-aws-modules/ecs/aws"

  cluster_name = "iamind-cluster"

  cluster_configuration = {
    execute_command_configuration = {
      logging = "OVERRIDE"
      log_configuration = {
        cloud_watch_log_group_name = "/aws/ecs/iamind-cluster"
      }
    }
  }

  fargate_capacity_providers = {
    FARGATE = {
      default_capacity_provider_strategy = {
        weight = 50
      }
    }
    FARGATE_SPOT = {
      default_capacity_provider_strategy = {
        weight = 50
      }
    }
  }

  services = {
    session_analytics = {
      cpu    = 1024
      memory = 4096

      container_definitions = {
        session_analytics_container = {
          cpu       = 512
          memory    = 1024
          essential = true
          image     = "xxx-image-here"
          port_mappings = [
            {
              name          = "session_analytics_container_port"
              containerPort = 5000
              protocol      = "tcp"
            }
          ]

          enable_cloudwatch_logging = true
          log_configuration = {
            logDriver = "awslogs"
            options = {
              Name                    = "container-cloudwatch-logs"
              region                  = "us-east-2"
              delivery_stream         = "xxxx-delivery-stream"
              log-driver-buffer-limit = "2097152"
            }
          }
            memory_reservation = 100
        }
      }
    }
  }

}