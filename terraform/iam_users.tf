resource "aws_iam_user" "iamind_local_dev" {
  name = "iamind_local_dev"

  tags = local.default_tags
}

resource "aws_iam_group" "iamind_developers" {
  name = "iamind_developers"
}

data "aws_iam_policy_document" "iamind_developers_group_policy_document" {

  version = "2012-10-17"

  statement {
    sid    = "STSAccess"
    effect = "Allow"
    actions = [
      "sts:AssumeRole"
    ]
    resources = [aws_iam_role.iamind_developer_role.arn]
  }
}

resource "aws_iam_policy" "iamind_developer_group_policy" {
  name        = "iamind_developer_group_policy"
  description = "The policy that grants permission to the group to assume the role"
  policy      = data.aws_iam_policy_document.iamind_developers_group_policy_document.json
}

resource "aws_iam_group_policy_attachment" "iamind_developer_group_policy_attch" {
  group      = aws_iam_group.iamind_developers.name
  policy_arn = aws_iam_policy.iamind_developer_group_policy.arn
}