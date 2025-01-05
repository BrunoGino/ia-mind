resource "aws_lb" "iamind_alb" {
  name               = "iamind-alb"
  internal           = false
  load_balancer_type = "application"
  security_groups    = [aws_security_group.iamind_sg_tls_http.id]
  subnets            = [aws_subnet.iamind_subnet_public1.id, aws_subnet.iamind_subnet_public2.id]

  enable_deletion_protection = true

  access_logs {
    bucket  = aws_s3_bucket.iamind_access_logs_bucket.id
    prefix  = "iamind-alb"
    enabled = true
  }

  tags = local.default_tags
}

resource "aws_lb_target_group" "iamind_alb_tg_https" {
  name     = "iamind-alb-tg-https"
  port     = 443
  protocol = "TCP"
  vpc_id   = aws_vpc.iamind_vpc.id
}

resource "aws_lb_listener" "iamind_alb_listener_https" {
  load_balancer_arn = aws_lb.iamind_alb.arn
  port              = "443"
  protocol          = "HTTPS"
  ssl_policy        = "ELBSecurityPolicy-2016-08"
  certificate_arn   = aws_acm_certificate.iamind_certificate.arn

  default_action {
    type             = "forward"
    target_group_arn = aws_lb_target_group.iamind_alb_tg_https.arn
  }
}
