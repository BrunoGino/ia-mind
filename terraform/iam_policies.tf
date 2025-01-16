#####################################
#    DEPLOYMENT POLICY - PART ONE
#####################################
data "aws_iam_policy_document" "deployment_policy_document_1" {

  version = "2012-10-17"
  statement {
    sid    = "S3Control"
    effect = "Allow"
    actions = [
      "s3:CreateBucket",
      "s3:DeleteBucket",
      "s3:Put*",
      "s3:Get*",
      "s3:List*"
    ]
    resources = [
      "arn:aws:s3:::iamind-terraform-state-backend",
      "arn:aws:s3:::iamind-terraform-state-backend/terraform.tfstate",
      "arn:aws:s3:::iamind-access-logs-bucket",
      "arn:aws:s3:::iamind-access-logs-bucket/*"
    ]
  }

  statement {
    sid    = "DynamoDBControl"
    effect = "Allow"
    actions = [
      "dynamodb:CreateTable",
      "dynamodb:TagResource",
      "dynamodb:UntagResource",
      "dynamodb:List*",
      "dynamodb:Describe*",
      "dynamodb:Get*",
      "dynamodb:Put*"
    ]
    resources = [
      "arn:aws:dynamodb:eu-west-1:108782061116:table/iamind-terraform-state-lock",
      "arn:aws:dynamodb:eu-west-1:108782061116:table/iamind-*",
      "arn:aws:dynamodb:eu-west-1:108782061116:table/iamind_*"
    ]
  }


  statement {
    sid    = "WAFControl"
    effect = "Allow"
    actions = [
      "wafv2:Get*",
      "wafv2:List*",
      "wafv2:*Resource",
      "wafv2:AssociateWebACL",
      "wafv2:CreateWebACL",
      "wafv2:DeleteWebACL",
      "wafv2:Update*"
    ]
    resources = [
      "arn:aws:wafv2:eu-west-1:108782061116:regional/managedruleset/*/*",
      "arn:aws:wafv2:eu-west-1:108782061116:regional/webacl/iamind/*"
    ]
  }

  statement {
    sid    = "Route53Control"
    effect = "Allow"
    actions = [
      "route53:ListHostedZones"
    ]
    resources = [
      "*"
    ]
  }

  statement {
    sid    = "IAMControl"
    effect = "Allow"
    actions = [
      "iam:Get*",
      "iam:List*",
      "iam:Tag*",
      "iam:Untag*",
      "iam:Delete*",
      "iam:Update*",
      "iam:PassRole",
      "iam:Create*",
      "iam:*Policy",
      "iam:AddUserToGroup",
      "iam:RemoveUserFromGroup"
    ]
    resources = [
      "arn:aws:iam::108782061116:role/iamind*",
      "arn:aws:iam::108782061116:policy/iamind*",
      "arn:aws:iam::108782061116:role/aws_gino_sol*",
      "arn:aws:iam::108782061116:role/AWSServiceRole*",
      "arn:aws:iam::108782061116:policy/aws_gino_sol*",
      aws_iam_group.iamind_developers.arn,
      aws_iam_user.iamind_local_dev.arn
    ]
  }

  statement {
    sid    = "APIGatewayControl"
    effect = "Allow"
    actions = [
      "apigateway:POST",
      "apigateway:GET",
      "apigateway:PATCH",
      "apigateway:DELETE",
      "apigateway:PUT",
    ]
    resources = [
      "arn:aws:apigateway:eu-west-1::/tags/*",
      "arn:aws:apigateway:eu-west-1::/apis",
      "arn:aws:apigateway:eu-west-1::/apis/*",
      "arn:aws:apigateway:eu-west-1::/apis/*/apimappings",
      "arn:aws:apigateway:eu-west-1::/apis/*/apimappings/*",
      "arn:aws:apigateway:eu-west-1::/apis/*/authorizers",
      "arn:aws:apigateway:eu-west-1::/apis/*/authorizers/*",
      "arn:aws:apigateway:eu-west-1::/apis/*/cors",
      "arn:aws:apigateway:eu-west-1::/apis/*/deployments",
      "arn:aws:apigateway:eu-west-1::/apis/*/deployments/*",
      "arn:aws:apigateway:eu-west-1::/apis/*/domainnames",
      "arn:aws:apigateway:eu-west-1::/apis/*/domainnames/*",
      "arn:aws:apigateway:eu-west-1::/apis/*/exports/*",
      "arn:aws:apigateway:eu-west-1::/apis/*/integrations",
      "arn:aws:apigateway:eu-west-1::/apis/*/integrations/*",
      "arn:aws:apigateway:eu-west-1::/apis/*/integrationresponses",
      "arn:aws:apigateway:eu-west-1::/apis/*/integrationresponses/*",
      "arn:aws:apigateway:eu-west-1::/apis/*/models",
      "arn:aws:apigateway:eu-west-1::/apis/*/models/*",
      "arn:aws:apigateway:eu-west-1::/apis/*/routes",
      "arn:aws:apigateway:eu-west-1::/apis/*/routes/*",
      "arn:aws:apigateway:eu-west-1::/apis/*/requestparameters/*",
      "arn:aws:apigateway:eu-west-1::/apis/*/routeresponses",
      "arn:aws:apigateway:eu-west-1::/apis/*/routeresponses/*",
      "arn:aws:apigateway:eu-west-1::/apis/*/routesettings/*",
      "arn:aws:apigateway:eu-west-1::/apis/*/stages",
      "arn:aws:apigateway:eu-west-1::/apis/*/stages/*",
      "arn:aws:apigateway:eu-west-1::/apis/*/stages/*/accesslogsettings",
      "arn:aws:apigateway:eu-west-1::/domainnames/*",
      "arn:aws:apigateway:eu-west-1::/vpclinks",
      "arn:aws:apigateway:eu-west-1::/vpclinks/*",
    ]
  }

  statement {
    sid    = "ECSControl"
    effect = "Allow"
    actions = [
      "ecs:List*",
      "ecs:Describe*",
      "ecs:Get*",
      "ecs:Put*",
      "ecs:Delete*",
      "ecs:Create*",
      "ecs:TagResource",
      "ecs:UntagResource",
      "ecs:Register*",
      "ecs:Deregister*",
      "ecs:Update*"
    ]
    resources = [
      "*",
      "arn:aws:ecs:eu-west-1:108782061116:cluster/iamind*",
      "arn:aws:ecs:eu-west-1:108782061116:service-deployment/iamind/*/*",
      "arn:aws:ecs:eu-west-1:108782061116:task/iamind/*",
      "arn:aws:ecs:eu-west-1:108782061116:container-instance/iamind/*",
      "arn:aws:ecs:eu-west-1:108782061116:task-definition/*:*",
      "arn:aws:ecs:eu-west-1:108782061116:service/iamind/*",
      "arn:aws:ecs:eu-west-1:108782061116:task-set/iamind/*/*",
      "arn:aws:ecs:eu-west-1:108782061116:service-revision/iamind/*/*",
      "arn:aws:ecs:eu-west-1:108782061116:capacity-provider/*"
    ]
  }

  statement {
    sid    = "ELBControl"
    effect = "Allow"
    actions = [
      "elasticloadbalancing:set*",
      "elasticloadbalancing:Modify*",
      "elasticloadbalancing:Describe*",
      "elasticloadbalancing:Delete*",
      "elasticloadbalancing:Delete*",
      "elasticloadbalancing:Create*",
      "elasticloadbalancing:AddTags",
      "elasticloadbalancing:RemoveTags"
    ]
    resources = [
      "*",
      "arn:aws:elasticloadbalancing:eu-west-1:108782061116:targetgroup/*/*",
      "arn:aws:elasticloadbalancing:eu-west-1:108782061116:loadbalancer/net/*/*",
      "arn:aws:elasticloadbalancing:eu-west-1:108782061116:loadbalancer/app/*/*",
      "arn:aws:elasticloadbalancing:eu-west-1:108782061116:listener/app/iamind*",
      "arn:aws:elasticloadbalancing:eu-west-1:108782061116:listener-rule/app/iamind*"
    ]
  }

  statement {
    sid    = "VPCEC2Control"
    effect = "Allow"
    actions = [
      "ec2:Revoke*",
      "ec2:Authorize*",
      "ec2:Update*",
      "ec2:Create*",
      "ec2:Describe*",
      "ec2:Terminate*",
      "ec2:Provision*",
      "ec2:Deprovision*",
      "ec2:Allocate*",
      "ec2:Modify*",
      "ec2:Get*",
      "ec2:Attach*",
      "ec2:Detach*",
      "ec2:Delete*",
      "ec2:Associate*",
      "ec2:Replace*"
    ]
    resources = [
      "*",
      "arn:aws:ec2:eu-west-1:108782061116:subnet/subnet*",
      "arn:aws:ec2::108782061116:ipam/*",
      "arn:aws:ec2::108782061116:ipam-pool/ipam-pool-*",
      "arn:aws:ec2:eu-west-1:108782061116:ipam-pool/*",
      "arn:aws:ec2::108782061116:ipam/ipam-*"
    ]
  }

  statement {
    sid    = "CloudwatchControl"
    effect = "Allow"
    actions = [
      "cloudwatch:Describe*",
      "cloudwatch:List*",
      "cloudwatch:EnableAlarmActions",
      "cloudwatch:PutMetric*",
      "cloudwatch:TagResource",
      "cloudwatch:UntagResource",
      "cloudwatch:DeleteAlarms"
    ]
    resources = [
      "arn:aws:cloudwatch:eu-west-1:108782061116:alarm:iamind-*",
      "arn:aws:cloudwatch:eu-west-1:108782061116:metric-stream/iamind-*",
      "arn:aws:cloudwatch:eu-west-1:108782061116:service/iamind-*"
    ]
  }
}

#####################################
#    DEPLOYMENT POLICY - PART TWO
#####################################
data "aws_iam_policy_document" "deployment_policy_document_2" {

  version = "2012-10-17"
  statement {
    sid    = "ACMControl"
    effect = "Allow"
    actions = [
      "acm:Get*",
      "acm:List*",
      "acm:AddTagsToCertificate",
      "acm:DeleteCertificate",
      "acm:DescribeCertificate",
      "acm:PutAccountConfiguration",
      "acm:RemoveTagsFromCertificate",
      "acm:RequestCertificate",
      "acm:UpdateCertificateOptions"
    ]
    resources = [
      "arn:aws:acm:eu-west-1:108782061116:certificate/*"
    ]
  }

  statement {
    sid    = "LogsControl"
    effect = "Allow"
    actions = [
      "logs:Untag*",
      "logs:Tag*",
      "logs:Create*",
      "logs:Describe*",
      "logs:List*",
      "logs:Get*",
      "logs:Create*",
      "logs:Delete*",
      "logs:Put*",
      "logs:Update*"
    ]
    resources = [
      "arn:aws:logs:eu-west-1:108782061116:log-group:/aws/ecs/iamind-cluster:log-stream",
      "arn:aws:logs:eu-west-1:108782061116:log-group:/aws/vpc/iamind*",
      "arn:aws:logs:eu-west-1:108782061116:log-group:/ecs/*",
      "arn:aws:logs:eu-west-1:108782061116:log-group::log-stream*"

    ]
  }

  statement {
    sid    = "SecretsManagement"
    effect = "Allow"
    actions = [
      "secretsmanager:Get*",
      "secretsmanager:Describe*",
      "secretsmanager:List*",
      "secretsmanager:DeleteResourcePolicy",
      "secretsmanager:PutResourcePolicy",
      "secretsmanager:ValidateResourcePolicy",
      "secretsmanager:TagResource",
      "secretsmanager:UntagResource",
      "secretsmanager:UpdateSecret",
      "secretsmanager:RotateSecret",
      "secretsmanager:RestoreSecret",
      "secretsmanager:PutSecretValue",
      "secretsmanager:DeleteSecret",
      "secretsmanager:CreateSecret",
      "secretsmanager:CancelRotateSecret"
    ]
    resources = [
      "arn:aws:secretsmanager:eu-west-1:108782061116:secret:iamind*"
    ]
  }

  statement {
    sid    = "KMSManagement"
    effect = "Allow"
    actions = [
      "kms:List*",
      "kms:Describe*",
      "kms:Get*",
      "kms:Cancel*",
      "kms:Connect*",
      "kms:Create*",
      "kms:Decrypt",
      "kms:DeleteAlias",
      "kms:Disable*",
      "kms:Enable*",
      "kms:Encrypt",
      "kms:Update*",
      "kms:RevokeGrant",
      "kms:RetireGrant",
      "kms:PutKeyPolicy",
      "kms:CreateGrant",
      "kms:TagResource",
      "kms:UntagResource"
    ]
    resources = [
      "*",
      "arn:aws:kms:eu-west-1:108782061116:key/iamind*",
      "arn:aws:kms:eu-west-1:108782061116:alias/iamind"
    ]
  }
}


resource "aws_iam_policy" "deployment_policy_1" {
  name        = "aws_gino_sol_deployment_policy_1"
  description = "The policy that grants permissions to the deployment role"
  policy      = data.aws_iam_policy_document.deployment_policy_document_1.json
}

resource "aws_iam_policy" "deployment_policy_2" {
  name        = "aws_gino_sol_deployment_policy_2"
  description = "The policy that grants permissions to the deployment role"
  policy      = data.aws_iam_policy_document.deployment_policy_document_2.json
}

#####################################
#    ECS TASKS POLICY
#####################################
data "aws_iam_policy_document" "ecs_tasks_policy_document" {

  version = "2012-10-17"

  statement {
    sid    = "DynamoDBIntegrationAccess"
    effect = "Allow"
    actions = [
      "dynamodb:*Item",
      "dynamodb:Scan",
      "dynamodb:Query",
      "dynamodb:Describe*"
    ]
    resources = [
      "arn:aws:dynamodb:eu-west-1:108782061116:table/iamind*"
    ]
  }

  statement {
    sid    = "SNSIntegration"
    effect = "Allow"
    actions = [
      "sns:Publish",
      "sns:ListSubscriptionsByTopic",
      "sns:GetTopicAttributes"
    ]
    resources = [
      "arn:aws:sns:eu-west-1:108782061116:iamind-*"
    ]
  }

  statement {
    sid       = "SQSIntegration"
    effect    = "Allow"
    actions   = ["sqs:DeleteMessage", "sqs:GetQueueUrl", "sqs:GetQueueAttributes", "sqs:ReceiveMessage", "sqs:SendMessage"]
    resources = ["arn:aws:sqs:eu-west-1:108782061116:iamind-*"]
  }

  statement {
    sid    = "CloudwatchIntegration"
    effect = "Allow"
    actions = [
      "cloudwatch:PutMetricAlarm",
      "cloudwatch:DeleteAlarms",
      "logs:*",
    ]
    resources = [
      "arn:aws:cloudwatch:eu-west-1:108782061116:alarm:iamind-*",
      "arn:aws:logs:eu-west-1:108782061116:log-group:/ecs/*"
    ]
  }

  statement {
    sid    = "SecretsIntegration"
    effect = "Allow"
    actions = [
      "secretsmanager:GetSecretValue",
      "secretsmanager:DescribeSecret",
      "kms:Decrypt",
      "kms:DescribeKey",
      "kms:GetPublicKey",
      "kms:GetKeyPolicy",
      "kms:Encrypt"
    ]
    resources = [
      aws_secretsmanager_secret.iamind_docker_hub_secret.arn,
      aws_kms_key.iamind_kms_key.arn
    ]
  }

  statement {
    sid    = "ECRIntegration"
    effect = "Allow"
    actions = [
      "ecr:GetAuthorizationToken",
      "ecr:BatchCheckLayerAvailability",
      "ecr:GetDownloadUrlForLayer",
      "ecr:BatchGetImage"
    ]
    resources = ["*"]
  }

}

resource "aws_iam_policy" "ecs_tasks_policy" {
  name        = "aws_gino_sol_iamind_ecs_tasks"
  description = "The policy that grants permissions to the ECS tasks role"
  policy      = data.aws_iam_policy_document.ecs_tasks_policy_document.json
}


#####################################
#    VPC FLOW LOGS POLICY
#####################################

resource "aws_iam_role_policy" "iamind_flow_logs_policy" {
  name = "iamind_flow_logs_policy"
  role = aws_iam_role.iamind_flow_logs_role.id
  policy = jsonencode({
    Version = "2012-10-17",
    Statement = [
      {
        Effect   = "Allow",
        Action   = "logs:CreateLogGroup",
        Resource = "arn:aws:logs:*:*:*"
      },
      {
        Effect = "Allow",
        Action = [
          "logs:CreateLogStream",
          "logs:PutLogEvents"
        ],
        Resource = "arn:aws:logs:*:*:log-group:/aws/vpc/flow-logs:*"
      }
    ]
  })
}


#####################################
#    DEVELOPER POLICY
#####################################
data "aws_iam_policy_document" "iamind_developer_policy_document" {

  version = "2012-10-17"

  statement {
    sid    = "STSAccess"
    effect = "Allow"
    actions = [
      "sts:AssumeRole"
    ]
    resources = [aws_iam_role.iamind_developer_role.arn]
  }

  statement {
    sid    = "DynamoDBAccess"
    effect = "Allow"
    actions = [
      "dynamodb:*Item",
      "dynamodb:Scan",
      "dynamodb:Query",
      "dynamodb:Describe*",
      "dynamodb:List*"
    ]
    resources = [
      "arn:aws:dynamodb:eu-west-1:108782061116:table/iamind*",
      "arn:aws:dynamodb:eu-west-1:108782061116:table/*"
    ]
  }

  statement {
    sid    = "S3Access"
    effect = "Allow"
    actions = [
      "s3:*Object",
      "s3:Get*",
      "s3:Put*"
    ]
    resources = [
      aws_s3_bucket.iamind_access_logs_bucket.arn
    ]
  }

  statement {
    sid    = "SNSIntegration"
    effect = "Allow"
    actions = [
      "sns:Publish",
      "sns:ListSubscriptionsByTopic",
      "sns:GetTopicAttributes"
    ]
    resources = [
      "arn:aws:sns:eu-west-1:108782061116:iamind-*"
    ]
  }

  statement {
    sid       = "SQSIntegration"
    effect    = "Allow"
    actions   = ["sqs:DeleteMessage", "sqs:GetQueueUrl", "sqs:GetQueueAttributes", "sqs:ReceiveMessage", "sqs:SendMessage"]
    resources = ["arn:aws:sqs:eu-west-1:108782061116:iamind-*"]
  }

  statement {
    sid    = "CloudwatchIntegration"
    effect = "Allow"
    actions = [
      "cloudwatch:PutMetricAlarm",
      "cloudwatch:DeleteAlarms",
      "logs:CreateLogStream",
      "logs:PutLogEvents"
    ]
    resources = [
      "arn:aws:cloudwatch:eu-west-1:108782061116:alarm:iamind-*",
      "arn:aws:logs:eu-west-1:108782061116:log-group:/ecs/*"
    ]
  }

  statement {
    sid    = "SecretsIntegration"
    effect = "Allow"
    actions = [
      "secretsmanager:GetSecretValue",
      "secretsmanager:DescribeSecret",
      "kms:Decrypt",
      "kms:DescribeKey",
      "kms:GetPublicKey",
      "kms:GetKeyPolicy",
      "kms:Encrypt"
    ]
    resources = [
      aws_secretsmanager_secret.iamind_docker_hub_secret.arn,
      aws_kms_key.iamind_kms_key.arn
    ]
  }
}

resource "aws_iam_policy" "iamind_developer_policy" {
  name        = "iamind_developer_policy"
  description = "The policy that grants permissions to the developer to do local functions"
  policy      = data.aws_iam_policy_document.iamind_developer_policy_document.json
}
