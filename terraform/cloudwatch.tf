resource "aws_cloudwatch_log_group" "user_ms_task_logs" {
  name              = "/ecs/user_ms_task-logs"
  retention_in_days = 7

  tags = local.default_tags
}
