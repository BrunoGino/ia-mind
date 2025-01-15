resource "aws_iam_user" "iamind_local_dev" {
  name = "iamind_local_dev"

  tags = local.default_tags
}

resource "aws_iam_group" "iamind_developers" {
  name = "iamind_developers"
}


resource "aws_iam_group_policy_attachment" "iamind_developer_group_policy_attch" {
  group      = aws_iam_group.iamind_developers.name
  policy_arn = aws_iam_policy.iamind_developer_policy.arn
}

resource "aws_iam_group_membership" "iamind_local_developer_membership" {
  name = "iamind_local_developer_membership"

  users = [
    aws_iam_user.iamind_local_dev.name
  ]

  group = aws_iam_group.iamind_developers.name
}


import {
  to = aws_iam_access_key.iamind_local_access_key
  id = var.access_key_id
}

resource "aws_iam_access_key" "iamind_local_access_key" {
  user = aws_iam_user.iamind_local_dev.name
}
