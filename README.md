# 🚗 VelocityCar API

VelocityCar é uma API REST desenvolvida com **Spring Boot** e **Spring Data JPA**, com persistência em banco de dados relacional. O projeto oferece um CRUD completo para as entidades **Carro** e **Cliente**, desenvolvido como projeto acadêmico na disciplina de **Java Advanced**.

---

## 📋 Tecnologias Utilizadas

- Java 17+
- Spring Boot
- Spring Data JPA
- Spring Web (REST)
- Banco de dados relacional (H2 / MySQL / PostgreSQL)
- Maven

---

## 📦 Entidades

### Carro

Representa os veículos cadastrados no sistema.

### Cliente

Representa os clientes cadastrados no sistema.

---

## 🔧 Endpoints

### Carro

| Método | Endpoint       | Descrição              |
| ------ | -------------- | ---------------------- |
| GET    | `/carros`      | Lista todos os carros  |
| GET    | `/carros/{id}` | Busca carro por ID     |
| POST   | `/carros`      | Cadastra um novo carro |
| PUT    | `/carros/{id}` | Atualiza um carro      |
| DELETE | `/carros/{id}` | Remove um carro        |

### client

| Método | Endpoint       | Descrição                |
| ------ | -------------- | ------------------------ |
| GET    | `/client`      | Lista todos os clientes  |
| GET    | `/client/{id}` | Busca cliente por ID     |
| POST   | `/client`      | Cadastra um novo cliente |
| PUT    | `/client/{id}` | Atualiza um cliente      |
| DELETE | `/client/{id}` | Remove um cliente        |

---

## ▶️ Como Executar

1. **Clone o repositório:**

   ```bash
   git clone https://github.com/seu-usuario/velocitycar.git
   cd velocitycar
   ```

2. **Configure o banco de dados** no arquivo `src/main/resources/application.properties`.

3. **Execute o projeto:**

   ```bash
   ./mvnw spring-boot:run
   ```

4. A API estará disponível em `http://localhost:8080`.

## 🐳 Executando com Docker e MySQL

1. Crie a imagem e suba os dois containers:

   ```bash
   docker compose up --build -d
   ```

2. A API ficará em `http://localhost:8080` e o MySQL em `localhost:3306`.

3. Para parar tudo:

   ```bash
   docker compose down
   ```

4. Se quiser preservar os dados do MySQL, o volume `mysql_data` já foi configurado.

## 🖥️ Como subir na VM

O fluxo mais simples é este, usando imagem publicada em registry:

1. Gere a imagem localmente ou em um pipeline de CI.
2. Faça login no registry e publique a imagem.
3. Na VM, instale Docker e o plugin `docker compose`.
4. Puxe a imagem com `docker pull`.
5. Suba com `docker compose -f docker-compose.prod.yml up -d`.

### Exemplo com Docker Hub

Na sua máquina principal:

```bash
podman build -t seuusuario/velocitycar-api:1.0 .
podman login docker.io
podman push seuusuario/velocitycar-api:1.0
```

Na VM:

```bash
docker login
docker pull seuusuario/velocitycar-api:1.0
API_IMAGE=seuusuario/velocitycar-api:1.0 docker compose -f docker-compose.prod.yml up -d
```

Se quiser, também dá para usar outro registry, como GHCR, mudando apenas o nome da imagem.

---

## 🎓 Informações Acadêmicas

> Projeto desenvolvido como atividade avaliativa na disciplina de **Java Advanced**.

---

## 📄 Licença

Este projeto é de apenas uso acadêmico.
