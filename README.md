# 🌿 Basilico API

> API REST desenvolvida com **Spring Boot 4.0** para gerenciar um ecossistema completo de restaurante — desde o catálogo de produtos até o fluxo de pedidos, com autenticação segura baseada em roles e gestão centralizada de usuários.

---

## 🚀 Visão Geral

A **Basilico API** foi projetada com foco em **segurança, escala, organização e clareza de domínio**, aplicando conceitos modernos como:

- **Domain-Driven Design (DDD)**
- **Bounded Contexts**
- **Spring Security com Roles**
- **Clean Architecture**
- **Autenticação e Autorização por Perfil**

A aplicação segue um modelo de **pedido com cancelamento lógico** (soft delete), mantendo histórico completo de todas as transações.

---

## 🧠 Arquitetura

O sistema foi estruturado por **contextos delimitados**, separando responsabilidades de forma clara e escalável.

### 📦 Contextos (Bounded Contexts)

#### 🟢 `catalog`
Gerenciamento de catálogo de produtos
- **Bebidas**: criação, listagem, atualização e exclusão
- **Refeições**: gerenciamento completo do menu
- **Validação**: DTOs com Jakarta Validation
- **Acesso**: Leitura aberta, criação restrita a `ADMIN`

#### 🔵 `ordering`
Fluxo completo de vendas e processamento de pedidos
- **Clientes**: registro e gestão
- **Pedidos**: criação, consulta, atualização de status, cancelamento lógico
- **Status**: `SOLICITADO` → `EM_PREPARACAO` → `ENTREGUE` | `CANCELADO`
- **Cálculo automático**: valor total dos itens
- **Segurança por role**: `USER` cria, `ADMIN` gerencia

#### 🟣 `management`
Administração do sistema e controle de acesso
- **Usuários**: criação (registro), busca, exclusão
- **Roles**: `ADMIN`, `USER`
- **Autenticação**: integrada com Spring Security
- **Autorização**: controle via `@PreAuthorize` nos endpoints

---

## 🛠️ Stack Tecnológica

| Tecnologia | Versão | Propósito |
|-----------|--------|----------|
| **Java** | 17 | Linguagem base |
| **Spring Boot** | 4.0.5 | Framework principal |
| **Spring Security** | 6.x | Autenticação e autorização |
| **Spring Data JPA** | 4.0.5 | ORM e persistência |
| **PostgreSQL** | 15+ | Banco de dados |
| **Flyway** | 9.x | Migrations versionadas |
| **Lombok** | 1.18+ | Redução de boilerplate |
| **Jakarta Validation** | 3.x | Validação de DTOs |

---

## 📁 Estrutura do Projeto
src/main/java/com/codexsystem/basilico/

├── BasilicoApplication.java

├── domain/                    # Regras de negócio puras (sem Spring)
│   ├── catalog/
│   │   ├── model/
│   │   └── enums/
│   │
│   ├── ordering/
│   │   └── model/
│   │
│   └── user/
│       └── model/

├── application/                # Casos de uso (services)
│   ├── catalog/
│   ├── ordering/
│   └── user/

├── infrastructure/             # Implementações técnicas
│   ├── persistence/
│   │   ├── catalog/
│   │   ├── ordering/
│   │   └── user/
│   │
│   ├── security/
│   │   ├── SecurityConfig.java
│   │   └── JwtFilter.java
│   │
│   └── configuration/

├── interfaces/                 # Camada de entrada (API REST)
│   ├── catalog/
│   │   └── controller/
│   │
│   ├── ordering/
│   │   └── controller/
│   │
│   └── user/
│       └── controller/
│
│   └── dto/
│
└── shared/                     # Código reutilizável
├── exception/
├── mapper/
└── utils/ation/ ├── V1_create_table_bebida.sql ├── V2_create_table_refeicao.sql ├── V3_create_table_usuario.sql ├── V4_create_table_cliente.sql ├── V5_create_table_pedido.sql └── V6_add_role_to_usuario.sql

---

## 🔐 Sistema de Segurança

### Roles Implementadas

| Role | Permissões |
|------|-----------|
| **ADMIN** | Criar/atualizar/deletar produtos; Gerenciar pedidos; Acesso total |
| **USER** | Criar pedidos; Visualizar próprios pedidos; Cancelar pedidos |

### Autenticação

- **Tipo**: HTTP Basic + Stateless Sessions
- **Codificação de Senha**: BCrypt
- **Session Policy**: STATELESS (ideal para APIs)
- **Endpoints Públicos**: `/user/create`, `/auth/login`

### Autorização

A aplicação usa `@PreAuthorize` em endpoints críticos para validar roles:

```java
@PreAuthorize("hasRole('ADMIN')")              // Apenas admin
@PreAuthorize("hasRole('USER')")               // Apenas usuários normais
@PreAuthorize("hasAnyRole('ADMIN', 'USER')")   // Admin ou usuário
````

📡 Endpoints

🍱 Catálogo - Refeições (/refeicao)
POST   /api/v1/refeicoes        (ADMIN)
GET    /api/v1/refeicoes        (pode ter filtro ?nome=)
GET    /api/v1/refeicoes/{id}
PUT    /api/v1/refeicoes/{id}   (ADMIN)
DELETE /api/v1/refeicoes/{id}   (ADMIN)

🥤 Catálogo - Bebidas (/bebida)
POST   /api/v1/bebidas          (ADMIN)
GET    /api/v1/bebidas
GET    /api/v1/bebidas/{id}
PUT    /api/v1/bebidas/{id}     (ADMIN)
DELETE /api/v1/bebidas/{id}     (ADMIN)

🛒 Pedidos (/pedidos)
POST   /api/v1/pedidos              (USER)
GET    /api/v1/pedidos?clienteId=X  (ADMIN/USER)
GET    /api/v1/pedidos/{id}         (ADMIN/USER)
DELETE /api/v1/pedidos/{id}         (soft delete)

👤 Clientes (/client)
POST   /api/v1/clientes
GET    /api/v1/clientes
GET    /api/v1/clientes/{id}
DELETE /api/v1/clientes/{id}

👨‍💼 Usuários (/user)
POST   /api/v1/usuarios
GET    /api/v1/usuarios?username=X
GET    /api/v1/usuarios/{id}
DELETE /api/v1/usuarios/{id}

🔄 Fluxo de Status do Pedido

┌─────────────┐
│ SOLICITADO  │  (Pedido criado)
└──────┬──────┘
│
▼
┌──────────────────┐
│ EM_PREPARACAO    │  (Em produção)
└──────┬───────────┘
│
├─────────────────────┐
│                     │
▼                     ▼
┌────────┐          ┌──────────┐
│ENTREGUE│          │CANCELADO │  (Soft delete - mantém histórico)
└────────┘          └──────────┘


Observação Importante: Um pedido cancelado não é deletado do banco. Seu status muda para CANCELADO e ele permanece 
no histórico do cliente.

🔒 Exemplos de Requisições
1. Registrar Usuário (sem autenticação)

POST /user/create
Content-Type: application/json

{
"username": "joao",
"email": "joao@email.com",
"password": "senha123"
}

2. Login (HTTP Basic)
POST /auth/login
Authorization: Basic am9hbzpzZW5oYTEyMw==

3. Criar Refeição (requer ADMIN)
   POST /refeicao/criar/refeicao
   Authorization: Basic admin:adminpass123
   Content-Type: application/json

{
"nome": "Lasanha à Bolonhesa",
"descricao": "Lasanha caseira com molho especial",
"preco": 35.50
}

4. Criar Pedido (requer USER)
POST /pedidos/create/order
Authorization: Basic joao:senha123
Content-Type: application/json

{
"clienteId": 1,
"refeicoes": [1, 2],
"bebidas": [3]
}

5. Listar Pedidos do Cliente
GET /pedidos?clienteId=1
Authorization: Basic joao:senha123

6. Cancelar Pedido (soft delete)
DELETE /pedidos/delete/order/1
Authorization: Basic joao:senha123

⚙️ Configuração Local
# Banco de dados
spring.datasource.url=jdbc:postgresql://localhost:5432/basilico
spring.datasource.username=SEU_USUARIO
spring.datasource.password=SUA_SENHA
spring.datasource.driver-class-name=org.postgresql.Driver

# JPA / Hibernate
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=validate
spring.jpa.properties.hibernate.format_sql=true

# Flyway Migrations
spring.flyway.locations=classpath:db/migration
spring.flyway.baselineOnMigrate=true


2. Rodar o Projeto
# Com Maven
./mvnw spring-boot:run

# Ou com Java direto (após build)
java -jar target/Basilico-0.0.1-SNAPSHOT.jar

👨‍💻 Autor

Matheus Almeida


📄 Licença

Este projeto está sob a licença MIT.
