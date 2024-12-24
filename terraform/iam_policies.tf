import {
  to = aws_iam_policy.deployment_policy
  id = "arn:aws:iam::108782061116:policy/aws-gino-sol-deployment"
}

data "aws_iam_policy_document" "deployment_policy_document" {

  version = "2012-10-17"
  statement {
    sid    = "TerraformStateFullAccess"
    effect = "Allow"
    actions = [
      "s3:*",
      "dynamodb:*"
    ]
    resources = [
      "arn:aws:s3:::iamind-terraform-state-backend",
      "arn:aws:dynamodb:eu-west-1:108782061116:table/iamind-terraform-state-lock",
      "arn:aws:dynamodb:eu-west-1:108782061116:table/iamind-terraform-state-lock/*"
    ]
  }

  statement {
    sid    = "AuthorizeDeployment"
    effect = "Allow"
    actions = [
      "iam:Get*",
      "iam:List*",
      "iam:DeletePolicyVersion",
      "iam:TagRole",
      "iam:UntagRole",
      "iam:DeletePolicy",
      "iam:UpdateAssumeRolePolicy",
      "iam:UpdateRoleDescription",
      "iam:PassRole"
    ]
    resources = [
      "arn:aws:iam::108782061116:policy/aws-gino-sol-deployment",
      "arn:aws:iam::108782061116:role/aws-gino-sol-deployment"
    ]
  }
}


resource "aws_iam_policy" "deployment_policy" {
  name        = "aws-gino-sol-deployment"
  description = "The policy that grants permissions to the deployment role"
  policy      = data.aws_iam_policy_document.deployment_policy_document.json
}