import {
  to = aws_iam_role.deployment_role
  id = "aws-gino-sol-deployment"
}

resource "aws_iam_role" "deployment_role" {
  name                 = "aws-gino-sol-deployment"
  max_session_duration = 60


  assume_role_policy = jsonencode({
    "Version" : "2012-10-17",
    "Statement" : [
      {
        "Effect" : "Allow",
        "Principal" : {
          "Federated" : "arn:aws:iam::108782061116:oidc-provider/token.actions.githubusercontent.com"
        },
        "Action" : "sts:AssumeRoleWithWebIdentity",
        "Condition" : {
          "StringEquals" : {
            "token.actions.githubusercontent.com:aud" : "sts.amazonaws.com",
            "token.actions.githubusercontent.com:sub" : "repo:BrunoGino/ia-mind:ref:refs/heads/main"
          }
        }
      }
    ]
  })
}