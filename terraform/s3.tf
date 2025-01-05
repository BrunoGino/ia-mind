import {
  to = aws_s3_bucket.terraform_state_bucket
  id = "iamind-terraform-state-backend"
}


resource "aws_s3_bucket" "terraform_state_bucket" {
  bucket              = "iamind-terraform-state-backend"
  object_lock_enabled = true

  tags = merge(local.default_tags, { Name : "The S3 backend to store the .tfstate file" })
}

resource "aws_s3_bucket_server_side_encryption_configuration" "terraform_state_bucket_encryption" {
  bucket = aws_s3_bucket.terraform_state_bucket.bucket

  rule {
    apply_server_side_encryption_by_default {
      sse_algorithm = "AES256"
    }
  }

}

resource "aws_s3_bucket" "iamind_access_logs_bucket" {
  bucket = "iamind_access_logs_bucket"

  tags = local.default_tags
}

resource "aws_s3_bucket_server_side_encryption_configuration" "iamind_access_logs_bucket_encryption" {
  bucket = aws_s3_bucket.iamind_access_logs_bucket.bucket

  rule {
    apply_server_side_encryption_by_default {
      sse_algorithm = "AES256"
    }
  }

}