resource "aws_iam_user" "iamind_local_dev" {
  name = "iamind_local_dev"

  tags = local.default_tags
}

resource "aws_iam_group" "iamind_developers" {
  name = "developers"
}

resource "aws_iam_group_policy_attachment" "test-attach" {
  group      = aws_iam_group.iamind_developers.name
  policy_arn = aws_iam_policy.iamind_developers.arn
}