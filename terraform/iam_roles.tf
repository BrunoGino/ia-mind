import {
  to = aws_iam_role.deployment_role
  id = "aws-gino-sol-deployment"
}

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
      test     = "StringEquals"
      variable = "token.actions.githubusercontent.com:sub"
      values   = ["sts.amazonaws.com"]
    }

  }
}

resource "aws_iam_role" "deployment_role" {
  name                 = "aws-gino-sol-deployment"
  max_session_duration = 3600

  assume_role_policy = data.aws_iam_policy_document.deployment_assume_role_policy_document.json

  tags = local.default_tags
}

resource "aws_iam_role_policy_attachment" "deployment_role_policy_attch" {
  role       = aws_iam_role.deployment_role.name
  policy_arn = aws_iam_policy.deployment_policy.arn
}