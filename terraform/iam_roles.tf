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
