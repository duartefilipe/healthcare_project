resource "aws_instance" "main" {
  ami                    = "ami-0fc5d935ebf8bc3bc"
  instance_type          = "t2.micro"
  key_name               = var.key_name
  vpc_security_group_ids = [var.security_group_id]
  subnet_id              = var.subnet_id

  associate_public_ip_address = true

  tags = {
    Name = "healthcare-ec2"
  }
}
