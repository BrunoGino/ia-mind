#####################################
#     DEPLOYMENT ROLE
#####################################
data "aws_iam_policy_document" "deployment_assume_role_policy_document" {

  version = "2012-10-17"
  statement {
    effect  = "Allow"
    actions = ["sts:AssumeRoleWithWebIdentity"]
    principals {
      type        = "Federated"
      identifiers = ["arn:aws:iam::108782061116:oidc-provider/token.actions.githubusercontent.com"]
    }

    condition {
      test     = "StringEquals"
      variable = "token.actions.githubusercontent.com:aud"
      values   = ["sts.amazonaws.com"]
    }

    condition {
      test     = "StringLike"
      variable = "token.actions.githubusercontent.com:sub"
      values = [
        "sts.amazonaws.com",
        "repo:BrunoGino/ia-mind:ref:refs/heads/main"
      ]
    }
  }

  statement {
    effect  = "Allow"
    actions = ["sts:AssumeRole"]
    principals {
      type = "Service"
      identifiers = [
        "application-autoscaling.amazonaws.com",
        "apigateway.amazonaws.com",
        "ecs-tasks.amazonaws.com",
        "ec2.amazonaws.com",
        "ecs.amazonaws.com",
        "edgelambda.amazonaws.com",
        "events.amazonaws.com",
        "lambda.amazonaws.com"
      ]
    }
  }

}

resource "aws_iam_role" "deployment_role" {
  name                 = "aws_gino_sol_deployment"
  max_session_duration = 3600

  assume_role_policy = data.aws_iam_policy_document.deployment_assume_role_policy_document.json

  tags = local.default_tags
}

resource "aws_iam_role_policy_attachment" "deployment_role_policy_attch_1" {
  role       = aws_iam_role.deployment_role.name
  policy_arn = aws_iam_policy.deployment_policy_1.arn
}

resource "aws_iam_role_policy_attachment" "deployment_role_policy_attch_2" {
  role       = aws_iam_role.deployment_role.name
  policy_arn = aws_iam_policy.deployment_policy_2.arn
}

#####################################
#    ECS TAKS ROLE
#####################################

data "aws_iam_policy_document" "ecs_tasks_assume_role_policy_document" {

  version = "2012-10-17"
  statement {
    effect  = "Allow"
    actions = ["sts:AssumeRole"]
    principals {
      type = "Service"
      identifiers = [
        "application-autoscaling.amazonaws.com",
        "ecs-tasks.amazonaws.com",
        "events.amazonaws.com"
      ]
    }

  }
}

resource "aws_iam_role" "ecs_tasks_role" {
  name = "aws_gino_sol_iamind_ecs_tasks"

  assume_role_policy = data.aws_iam_policy_document.ecs_tasks_assume_role_policy_document.json

  tags = local.default_tags
}

resource "aws_iam_role_policy_attachment" "ecs_tasks_role_policy_attch" {
  role       = aws_iam_role.ecs_tasks_role.name
  policy_arn = aws_iam_policy.ecs_tasks_policy.arn
}

#####################################
#    VPC FLOW LOGS ROLE
#####################################
resource "aws_iam_role" "iamind_flow_logs_role" {
  name = "iamind_flow_logs_role"

  assume_role_policy = jsonencode({
    Version = "2012-10-17",
    Statement = [
      {
        Action    = "sts:AssumeRole",
        Effect    = "Allow",
        Principal = { Service = "vpc-flow-logs.amazonaws.com" }
      }
    ]
  })
}

#####################################
#    DEVELOPER ROLE
#####################################
data "aws_iam_policy_document" "iamind_developer_assume_role_policy_document" {

  version = "2012-10-17"

  statement {
    sid    = "STSAccess"
    effect = "Allow"
    actions = [
      "sts:AssumeRole"
    ]
    principals {
      type        = "AWS"
      identifiers = [aws_iam_user.iamind_local_dev.arn]
    }
  }
}


resource "aws_iam_role" "iamind_developer_role" {
  name = "iamind_developer_role"

  assume_role_policy = data.aws_iam_policy_document.iamind_developer_assume_role_policy_document.json

  tags = local.default_tags
}

resource "aws_iam_role_policy_attachment" "iamind_developer_role_policy_attch" {
  role       = aws_iam_role.iamind_developer_role.name
  policy_arn = aws_iam_policy.iamind_developer_policy.arn
}