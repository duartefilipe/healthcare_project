variable "security_group_id" {
  description = "ID do Security Group para associar à instância EC2"
  type        = string
}

variable "key_name" {
  description = "Nome da chave SSH (AWS Key Pair) para acesso à instância"
  type        = string
}

variable "subnet_id" {
  description = "Subnet ID to launch the EC2 instance"
  type        = string
}
