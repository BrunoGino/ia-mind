resource "aws_kms_key" "iamind_kms_key" {
  description             = "IAMind kms key"
  deletion_window_in_days = 7
  enable_key_rotation     = true
  tags                    = local.default_tags
  policy                  = <<POLICY
    {
        "Version": "2012-10-17",
        "Statement": [
            {
                "Sid": "GrantAccessToRoles",
                "Effect": "Allow",
                "Principal": {
                    "AWS": [
                        "arn:aws:iam::108782061116:role/aws_gino_sol_deployment",
                        "arn:aws:iam::108782061116:role/aws_gino_sol_iamind_ecs_tasks"
                    ]
                },
                "Action": [
                    "kms:Decrypt",
                    "kms:Describe*",
                    "kms:Encrypt",
                    "kms:Get*",
                    "kms:GenerateDataKey*",
                    "kms:List*",
                    "kms:Update*",
                    "kms:ReEncrypt",
                    "kms:*Resource",
                    "kms:*Alias",
                    "kms:PutKeyPolicy",
                    "kms:ScheduleKeyDeletion",
                    "kms:*Grant",
                    "kms:EnableKeyRotation"
                ],
                "Resource": "arn:aws:kms:eu-west-1:108782061116:key/*"
            }
        ]
    }
  POLICY
}
