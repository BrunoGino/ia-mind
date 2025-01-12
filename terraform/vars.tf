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

variable "docker_hub_token" {
  type        = string
  description = "The Docker Hub token to pull images"
}

variable "docker_hub_user" {
  type        = string
  description = "The Docker Hub user to pull images"
}

variable "access_key_id" {
  type        = string
  description = "The access key id to develop locally"
}