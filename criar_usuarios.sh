#!/bin/bash

URL="http://localhost:8081/auth/register"
HEADER="Content-Type: application/json"

curl -X POST $URL -H "$HEADER" -d '{
  "name": "Admin User",
  "document": "12345678900",
  "birthDate": "1990-01-01",
  "username": "admin",
  "password": "admin",
  "role": "ADMIN"
}'

curl -X POST $URL -H "$HEADER" -d '{
  "name": "Paciente User",
  "document": "98765432100",
  "birthDate": "1995-05-05",
  "username": "paciente",
  "password": "paciente",
  "role": "PACIENTE"
}'

curl -X POST $URL -H "$HEADER" -d '{
  "name": "Medico User",
  "document": "11122233300",
  "birthDate": "1980-10-10",
  "username": "medico",
  "password": "medico",
  "role": "MEDICO"
}'

curl -X POST $URL -H "$HEADER" -d '{
  "name": "Medico User 1",
  "document": "11122233300",
  "birthDate": "1980-10-10",
  "username": "medico1",
  "password": "medico1",
  "role": "MEDICO"
}'

curl -X POST $URL -H "$HEADER" -d '{
  "name": "Medico User 2",
  "document": "11122233300",
  "birthDate": "1980-10-10",
  "username": "medico2",
  "password": "medico2",
  "role": "MEDICO"
}'

curl -X POST $URL -H "$HEADER" -d '{
  "name": "Medico User 3,
  "document": "11122233300",
  "birthDate": "1980-10-10",
  "username": "medico2",
  "password": "medico2",
  "role": "MEDICO"
}'

curl -X POST $URL -H "$HEADER" -d '{
  "name": "Paciente User 1",
  "document": "98765432100",
  "birthDate": "1995-05-05",
  "username": "paciente1",
  "password": "paciente1",
  "role": "PACIENTE"
}'

curl -X POST $URL -H "$HEADER" -d '{
  "name": "Paciente User 2",
  "document": "98765432100",
  "birthDate": "1995-05-05",
  "username": "paciente2",
  "password": "paciente2",
  "role": "PACIENTE"
}'

curl -X POST $URL -H "$HEADER" -d '{
  "name": "teste",
  "document": "98765432100",
  "birthDate": "1995-05-05",
  "username": "teste",
  "password": "teste",
  "role": "MEDICO"
}'