# module "alb" {
#   source              = "terraform-aws-modules/alb/aws"
#   version             = "2.4.0"
#   alb_name            = "iamind-alb"
#   alb_protocols       = ["HTTPS"]
#   alb_security_groups = [aws_security_group.iamind_sg_tls_http.id]
#   certificate_arn     = "arn:aws:iam::123456789012:server-certificate/test_cert-123456789012"
#   create_log_bucket   = true
#   enable_logging      = true
#   health_check_path   = "/"
#   log_bucket_name     = "logs-us-east-2-123456789012"
#   log_location_prefix = "iamind-alb"
#   subnets             = ["subnet-abcde012", "subnet-bcde012a"]
#   tags                = merge(local.default_tags, { Name = "iamind-alb" })
#   vpc_id              = aws_vpc.iamind_vpc.id
# }