data "aws_iam_policy_document" "deployment_policy_document" {

  version = "2012-10-17"
  statement {
    sid    = "S3Control"
    effect = "Allow"
    actions = [
      "s3:CreateBucket",
      "s3:DeleteBucket",
      "s3:PutBucketVersioning",
      "s3:PutEncryptionConfiguration",
      "s3:PutBucketTagging",
      "s3:PutObject*",
      "s3:Get*",
      "s3:List*"
    ]
    resources = [
      "arn:aws:s3:::iamind-terraform-state-backend",
      "arn:aws:s3:::iamind-terraform-state-backend/terraform.tfstate"
    ]
  }

  statement {
    sid    = "DynamoDBControl"
    effect = "Allow"
    actions = [
      "dynamodb:CreateTable",
      "dynamodb:TagResource"
    ]
    resources = [
      "arn:aws:dynamodb:eu-west-1:108782061116:table/iamind-terraform-state-lock"
    ]
  }


  statement {
    sid    = "WAFControl"
    effect = "Allow"
    actions = [
      "wafv2:Get*",
      "wafv2:List*",
      "wafv2:TagResource",
      "wafv2:UntagResource",
      "wafv2:AssociateWebACL",
      "wafv2:CreateWebACL"
    ]
    resources = [
      "arn:aws:wafv2:eu-west-1:108782061116:regional/managedruleset/*/*",
      "arn:aws:wafv2:eu-west-1:108782061116:regional/webacl/iamind/*"
    ]
  }

  statement {
    sid    = "IAMControl"
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
      "iam:PassRole",
      "iam:CreatePolicy",
      "iam:CreatePolicyVersion",
      "iam:CreateRole",
      "iam:AttachRolePolicy"
    ]
    resources = [
      "arn:aws:iam::108782061116:role/aws_gino_sol_deployment",
      "arn:aws:iam::108782061116:policy/aws_gino_sol_deployment",
      "arn:aws:iam::108782061116:role/aws_gino_sol_iamind*",
      "arn:aws:iam::108782061116:policy/aws_gino_sol_iamind*",
    ]
  }
}


resource "aws_iam_policy" "deployment_policy" {
  name        = "aws_gino_sol_deployment"
  description = "The policy that grants permissions to the deployment role"
  policy      = data.aws_iam_policy_document.deployment_policy_document.json
}