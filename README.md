# 🏥 Healthcare Microservices Project

Este projeto modela um sistema de saúde baseado em arquitetura de microserviços utilizando Java com Spring Boot, Angular, AWS e observabilidade com Grafana e Prometheus.

---

## 📦 Estrutura de Serviços

- `auth-service` – Autenticação
- `patient-service` – Cadastro de pacientes
- `appointment-service` – Agendamento
- `notification-service` – Notificações
- `frontend` – Angular com consumo de APIs

---

## ☁️ Cloud AWS

### Serviços Utilizados:

- EC2 – Deploy de microserviços
- RDS – PostgreSQL
- S3 – Armazenamento de logs por Lambda
- EKS – Orquestração via Kubernetes

---

## ⚙️ Como Rodar Localmente

### Requisitos:
- Docker e Docker Compose

```bash
docker-compose up --build
```

---

## ☸️ Deploy no EKS

### Usando `eksctl`:

```bash
cd infra/eks
bash eksctl-create-cluster.sh
```

### Usando `Terraform`:

```bash
cd infra/terraform
terraform init
terraform apply
```

---

## 🚀 Deploy com CI/CD

O pipeline GitHub Actions compila os serviços e faz o deploy via SCP para o EC2. Localizado em:

```bash
.github/workflows/ci-cd.yml
```

---

## 📊 Monitoramento com Grafana + Prometheus

- Prometheus coleta métricas expostas em `/actuator/prometheus`
- Grafana utiliza o datasource Prometheus configurado em `infra/grafana/datasources/prometheus.yaml`

---

## 🔧 Helm Chart

```bash
cd charts/patient-service
helm install patient-service .
```

---

## 📁 Pastas Relevantes

```
charts/                # Helm Charts
infra/terraform/       # Scripts Terraform para EKS, RDS, S3
infra/eks/             # Script eksctl
infra/grafana/         # Datasources Grafana
infra/prometheus/      # Scrape Config Kubernetes
.github/workflows/     # CI/CD GitHub Actions
```

---

## 📝 Autor

Projeto gerado com apoio do ChatGPT para testes técnicos de arquitetura moderna em saúde.