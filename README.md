
# 📌 Usuário API

Microserviço desenvolvido em **Java 17** com **Spring Boot 3**, responsável pelo gerenciamento de usuários, incluindo autenticação com **JWT**, cadastro de endereços e telefones.

---

## 🚀 Tecnologias Utilizadas

- Java 17
- Spring Boot 3.5.5
- Spring Web
- Spring Security + JWT
- Spring Data JPA
- PostgreSQL
- Lombok

---

## 📂 Estrutura do Projeto

```
src/main/java/br/com/brunoedubems/usuario
│── business
│   ├── converter
│   ├── dto
│   └── UsuarioService
│
│── controller
│   └── UsuarioController
│
│── infrastructure
│   ├── entity
│   │   ├── Usuario
│   │   ├── Endereco
│   │   └── Telefone
│   ├── repository
│   ├── security
│   │   ├── JwtRequestFilter
│   │   ├── JwtUtil
│   │   ├── SecurityConfig
│   │   └── UserDetailsServiceImpl
│   └── exceptions
│
└── UsuarioApplication
```

---

## ⚙️ Configuração do Projeto

### Pré-requisitos

- Java 17+
- Gradle ou Maven
- PostgreSQL

### Configuração do banco (application.properties ou application.yml)

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/usuario_db
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### Rodando a aplicação

Com **Gradle**:

```bash
./gradlew bootRun
```

Com **Maven**:

```bash
mvn spring-boot:run
```

---

## 📌 Endpoints Principais

### 🔐 Autenticação

**POST** `/usuario/login`  

**Request:**

```json
{
  "email": "teste@email.com",
  "senha": "123456"
}
```

**Response:**

```
Bearer <jwt_token>
```

---

### 👤 Usuário

- **POST** `/usuario` → cria novo usuário
- **GET** `/usuario?email=teste@email.com` → busca usuário por e-mail
- **PUT** `/usuario` → atualiza dados do usuário (necessário header `Authorization`)
- **DELETE** `/usuario/{email}` → remove usuário

---

### 📞 Telefone

**PUT** `/usuario/telefone?id={id}`

**Request:**

```json
{
  "numero": "987654321",
  "ddd": "11"
}
```

---

### 🏠 Endereço

**PUT** `/usuario/endereco?id={id}`

**Request:**

```json
{
  "rua": "Rua das Flores",
  "numero": 100,
  "complemento": "Apto 101",
  "cidade": "São Paulo",
  "estado": "SP",
  "cep": "01010-000"
}
```

---

## 🛡️ Segurança

A autenticação é feita via **JWT**:

1. O usuário faz login em `/usuario/login`.
2. Recebe um token JWT.
3. Envia o token no header `Authorization` para acessar endpoints protegidos.

---

## 🧑‍💻 Autor

Desenvolvido por **Bruno Eduardo** 👨‍💻
