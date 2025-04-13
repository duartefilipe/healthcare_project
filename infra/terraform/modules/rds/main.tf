resource "aws_db_instance" "postgres" {
  identifier        = "healthcare-db"
  engine            = "postgres"
  instance_class    = "db.t3.micro"
  allocated_storage = 20
  username          = var.db_user
  password          = var.db_password
  db_name           = var.db_name
  skip_final_snapshot = true
}
