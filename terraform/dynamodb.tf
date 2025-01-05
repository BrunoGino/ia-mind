import {
  to = aws_dynamodb_table.terraform-lock
  id = "iamind-terraform-state-lock"
}

resource "aws_dynamodb_table" "terraform-lock" {
  name                        = "iamind-terraform-state-lock"
  billing_mode                = "PAY_PER_REQUEST"
  hash_key                    = "LockID"
  deletion_protection_enabled = true
  attribute {
    name = "LockID"
    type = "S"
  }
  tags = merge(local.default_tags, { Name : "DynamoDB Terraform State Lock Table" })
}

resource "aws_dynamodb_table" "iamind_session_table" {
  name         = "iamind_session_table"
  billing_mode = "PAY_PER_REQUEST"
  hash_key     = "id"
  attribute {
    name = "id"
    type = "S"
  }
  tags = merge(local.default_tags, { Name : "IAMind session management table" })
}