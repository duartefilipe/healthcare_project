output "ec2_public_ip" {
  description = "Public IP of the EC2 instance"
  value       = module.ecs.public_ip
}

output "remote_user" {
  description = "Default SSH user for EC2"
  value       = "ubuntu"
}

output "ssh_private_key" {
  description = "Private key to access EC2"
  value       = module.ecs.private_key_pem
  sensitive   = true
}