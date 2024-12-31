module "api_gateway" {
  source  = "terraform-aws-modules/apigateway-v2/aws"
  version = "5.2.1"

  create        = true
  name          = "iamind-http"
  description   = "IAMind API Gateway"
  protocol_type = "HTTP"

  domain_name                  = "api.iamind.com"
  create_domain_records        = true
  create_certificate           = true
  create_domain_name           = true
  disable_execute_api_endpoint = false

  # cors_configuration = {
  #   allow_headers = ["content-type", "x-amz-date", "authorization", "x-api-key", "x-amz-security-token", "x-amz-user-agent"]
  #   allow_methods = ["*"]
  #   allow_origins = ["*"]
  # }

  # stage_access_log_settings = {
  #   create_log_group            = true
  #   log_group_retention_in_days = 7
  #   format = jsonencode({
  #     context = {
  #       domainName              = "$context.domainName"
  #       integrationErrorMessage = "$context.integrationErrorMessage"
  #       protocol                = "$context.protocol"
  #       requestId               = "$context.requestId"
  #       requestTime             = "$context.requestTime"
  #       responseLength          = "$context.responseLength"
  #       routeKey                = "$context.routeKey"
  #       stage                   = "$context.stage"
  #       status                  = "$context.status"
  #       error = {
  #         message      = "$context.error.message"
  #         responseType = "$context.error.responseType"
  #       }
  #       identity = {
  #         sourceIP = "$context.identity.sourceIp"
  #       }
  #       integration = {
  #         error             = "$context.integration.error"
  #         integrationStatus = "$context.integration.integrationStatus"
  #       }
  #     }
  #   })
  # }

  tags = local.default_tags
}