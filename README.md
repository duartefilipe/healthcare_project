# 🏥 Healthcare Project

Este projeto é uma plataforma de gestão de pacientes e agendamentos médicos, construída com uma arquitetura de microserviços utilizando Spring Boot e Angular. 
O sistema é voltado para ambientes com múltiplos perfis de usuário: **ADMIN**, **MÉDICO** e **PACIENTE**.

---

## Tecnologias Utilizadas

### Backend
- Java 17 + Spring Boot 3.4.4
- Spring Security (com autenticação básica)
- Spring Data JPA + PostgreSQL
- RestTemplate para comunicação entre serviços
- Docker e Docker Compose
- Prometheus (monitoramento)
- Grafana (monitoramento)
- Actuator com endpoints customizados

### Frontend
- Angular Standalone
- Login, listagem e cadastro de pacientes
- Exibição de funcionalidades por tipo de perfil (Admin, Médico, Paciente)

### Infraestrutura
- Planejado para provisionamento em AWS com Terraform (Arquivos na pasta infra onde temos os dados do monitoramento e futuro)
  - EC2 (containers dos serviços)
  - RDS (PostgreSQL)
  - S3 (Seria para fazer futuramente adicionando um arquivo de historico do paciente)
  - Lambda (eventualmente para notificações quando uma consulta é agendada para o paciente)
- Prometheus rodando no Docker (monitoramento local em `/actuator/prometheus`)

---

## Arquitetura de Microsserviços

### `auth-service`
- Responsável pelo cadastro, autenticação e listagem de usuários
- Endpoint de login (`/auth/login`) e verificação (`/auth/me`)
- CRUD completo para usuários
- Perfis suportados: `ADMIN`, `MEDICO`, `PACIENTE`
- Password criptografado com BCrypt

### `medical-service`
- Responsável por gerenciar pacientes
- Cadastro de pacientes só permitido para `ADMIN` e `MÉDICO`
- Endpoint `/patients` com autenticação
- Consulta a usuários via `auth-service`

### Futuramente teria que implementar o patience-service para ficar mais organizado

---

## Autenticação

- Implementada via **HTTP Basic Auth**
- Angular envia as credenciais com cada requisição via Interceptor
- `auth-service` valida as credenciais no endpoint `/auth/me`
- Somente usuários autenticados podem acessar `/patients`

---

