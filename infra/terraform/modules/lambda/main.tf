resource "aws_lambda_function" "notify" {
  function_name = "notification-handler"
  role          = var.lambda_role
  handler       = "index.handler"
  runtime       = "nodejs18.x"
  filename      = var.lambda_zip_path
}
