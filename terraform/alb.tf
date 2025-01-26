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

resource "aws_lb_target_group" "iamind_user_ms_tg" {
  name        = "iamind-user-ms-tg"
  port        = 80
  protocol    = "HTTP"
  target_type = "ip"
  vpc_id      = aws_vpc.iamind_vpc.id

  health_check {
    path                = "/users-health" # Update this to match your app's health endpoint
    interval            = 30
    timeout             = 5
    healthy_threshold   = 2
    unhealthy_threshold = 2
  }
}


resource "aws_lb_listener" "iamind_alb_listener_https" {
  load_balancer_arn = aws_lb.iamind_alb.arn
  port              = "443"
  protocol          = "HTTPS"
  ssl_policy        = "ELBSecurityPolicy-2016-08"
  certificate_arn   = aws_acm_certificate.iamind_certificate.arn

  default_action {
    type = "fixed-response"

    fixed_response {
      content_type = "text/plain"
      message_body = "Page not found"
      status_code  = "404"
    }
  }
}

resource "aws_lb_listener" "http_listener" {
  load_balancer_arn = aws_lb.iamind_alb.arn
  port              = "80"
  protocol          = "HTTP"

  default_action {
    type = "redirect"

    redirect {
      port        = "443"
      protocol    = "HTTPS"
      status_code = "HTTP_301"
    }
  }
}

resource "aws_lb_listener_rule" "iamind_user_ms_rule" {
  listener_arn = aws_lb_listener.iamind_alb_listener_https.arn
  priority     = 110
  action {
    type             = "forward"
    target_group_arn = aws_lb_target_group.iamind_user_ms_tg.arn
  }

  condition {
    path_pattern {
      values = [
        "/api/users/*"
      ]
    }
  }
}
resource "aws_lb_listener_rule" "iamind_user_swagger_ui_rule" {
  listener_arn = aws_lb_listener.iamind_alb_listener_https.arn
  priority     = 200

  condition {
    path_pattern {
      values = [
        "/users-swagger-ui.html",
        "/users-api-docs*",
        "/users-health*",
        "/swagger-ui*"
      ]
    }
  }

  action {
    type             = "forward"
    target_group_arn = aws_lb_target_group.iamind_user_ms_tg.arn
  }
}

