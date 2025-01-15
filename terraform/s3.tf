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
  bucket = "iamind-access-logs-bucket"

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

resource "aws_s3_bucket_policy" "iamind_access_logs_bucket_policy" {
  bucket = aws_s3_bucket.iamind_access_logs_bucket.id
  policy = <<POLICY
    {
      "Version": "2012-10-17",
      "Statement": [
        {
          "Effect": "Allow",
          "Principal": {
            "AWS": "arn:aws:iam::156460612806:root"
          },
          "Action": "s3:PutObject",
          "Resource": ["arn:aws:s3:::iamind-access-logs-bucket/*"]
        }
      ]
    }
  POLICY
}
