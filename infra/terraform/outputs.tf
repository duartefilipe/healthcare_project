output "ec2_public_ip" {
  description = "Public IP of the EC2 instance"
  value       = module.ecs.ec2_public_ip
}

output "remote_user" {
  description = "Default SSH user for EC2"
  value       = "ubuntu"
}

output "ssh_private_key" {
  value       = tls_private_key.ec2_key.private_key_pem
  sensitive   = true
  description = "Chave privada para acesso SSH na instância EC2"
}
