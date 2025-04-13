output "ec2_public_ip" {
  value = module.ecs.ec2_public_ip
}

output "remote_user" {
  value = "ubuntu"
}

output "ssh_private_key" {
  value     = tls_private_key.ec2_key.private_key_pem
  sensitive = true
}
