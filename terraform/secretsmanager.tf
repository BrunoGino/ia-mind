locals {
  secret_version = 1
}

resource "aws_secretsmanager_secret" "iamind_docker_repo_secret" {
  name = "iamind_docker_repo_secret"

  tags = local.default_tags
}


resource "aws_secretsmanager_secret_version" "iamind_kms_key_version" {
  secret_id     = aws_secretsmanager_secret.iamind_docker_repo_secret.id
  secret_string = jsonencode({ username = var.docker_hub_user, password = var.docker_hub_token })
}