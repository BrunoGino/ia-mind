terraform {
  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "~> 5.0"
    }
  }

  backend "s3" {
    region         = "eu-west-1"
    bucket         = "iamind-terraform-state-backend"
    key            = "terraform.tfstate"
    dynamodb_table = "terraform_state"
  }
}

provider "aws" {
  region = local.main_aws_region
}

