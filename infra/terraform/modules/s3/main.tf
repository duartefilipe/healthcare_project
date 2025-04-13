resource "aws_s3_bucket" "assets" {
  bucket = var.bucket_name
  force_destroy = true
}
