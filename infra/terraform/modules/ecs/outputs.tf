output "instance_id" {
  description = "ID da instância EC2 criada"
  value       = aws_instance.main.id
}

output "ec2_public_ip" {
  value = aws_instance.main.public_ip
}
