resource "aws_cloudwatch_log_group" "session_management_task_logs" {
  name              = "/ecs/session_management_task-logs"
  retention_in_days = 7 # Adjust the retention period as needed

  tags = local.default_tags
}
