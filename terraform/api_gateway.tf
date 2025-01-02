# resource "aws_apigatewayv2_api" "iamind_api_gateway" {
#   name          = "iamind_api_gateway"
#   protocol_type = "HTTP"

#   cors_configuration {
#     allow_headers = ["content-type", "x-amz-date", "authorization", "x-api-key", "x-amz-security-token", "x-amz-user-agent"]
#     allow_methods = ["*"]
#     allow_origins = ["*"]
#   }

#   fail_on_warnings = true

#   tags = local.default_tags
# }
