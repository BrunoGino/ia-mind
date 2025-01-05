resource "aws_acm_certificate" "iamind_certificate" {
  domain_name       = "www.iamind.com"
  validation_method = "DNS"
  key_algorithm     = "RSA_2048"

  tags = merge(local.default_tags, { Name = "iamind-certificate" })

  lifecycle {
    create_before_destroy = true
  }
}