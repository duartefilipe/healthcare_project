resource "tls_private_key" "ec2_key" {
  algorithm = "RSA"
  rsa_bits  = 4096
}

resource "aws_instance" "main" {
  ami           = "ami-0fc5d935ebf8bc3bc" 
  instance_type = "t2.micro"
  key_name      = null 

  tags = {
    Name = "healthcare-ec2"
  }
}
