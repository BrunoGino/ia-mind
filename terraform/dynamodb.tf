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