import {
  to = aws_acm_certificate.iamind_certificate
  id = "arn:aws:acm:eu-west-1:108782061116:certificate/d20d2a02-ad75-43d1-91c5-8573047c5856"
}


resource "aws_acm_certificate" "iamind_certificate" {
  domain_name       = "api.iamind-app.online"
  validation_method = "DNS"
  key_algorithm     = "RSA_2048"

  tags = merge(local.default_tags, { Name = "iamind-certificate" })

  lifecycle {
    create_before_destroy = true
  }
}