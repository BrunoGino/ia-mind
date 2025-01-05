locals {
  main_aws_region = "eu-west-1"
  region          = "eu-west-1"


  default_tags = {
    component = "iamind"
  }
}


variable "session_management_docker_image" {
  type        = string
  description = "The docker image name for session_management microservice"
}