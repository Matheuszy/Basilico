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
src/main/java/com/Codexsystem/Basilico/Basilico/ │ ├── catalog/ │ ├── controller/ # BebidaController, RefeicaoController │ ├── dto/ # BebidaRequestDto, BebidaResponseDto, etc. │ ├── model/ # Bebida, Refeicao (JPA entities) │ ├── repository/ # BebidaRepository, RefeicaoRepository │ └── services/ # BebidaService, RefeicaoService │ ├── ordering/ │ ├── controller/ # PedidoController, ClienteController │ ├── dto/ # PedidoRequestDto, PedidoResponseDto, etc. │ ├── enums/ # StatusPedido, StatusPagamento │ ├── model/ # Pedido, Cliente (JPA entities) │ ├── repository/ # PedidoRepository, ClienteRepository │ └── services/ # PedidoService, ClienteService │ ├── management/ │ ├── controller/ # UsuarioController │ ├── dto/ # RegisterRequestDto, RegisterResponseDto │ ├── model/ # Usuario (JPA entity com UserDetails) │ ├── repository/ # UsuarioRepository │ └── services/ # UsuarioService │ └── configuration/ └── security/ # SecurityConfig (filtros, roles, autorização)
src/main/resources/ │ ├── application.properties # Configurações do banco, segurança └── db/migration/ ├── V1_create_table_bebida.sql ├── V2_create_table_refeicao.sql ├── V3_create_table_usuario.sql ├── V4_create_table_cliente.sql ├── V5_create_table_pedido.sql └── V6_add_role_to_usuario.sql

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
🔑 Autenticação
Método
Endpoint
Autenticação
Descrição
POST
/user/create
❌
Registrar novo usuário
POST
/auth/login
❌
Login (HTTP Basic)


🍱 Catálogo - Refeições (/refeicao)
Método
Endpoint
Role
Descrição
POST
/criar/refeicao
ADMIN
Criar refeição
GET
/obter/refeicao?nome=X
❌
Buscar por nome
GET
/obter/{id}
❌
Buscar por ID
PATCH
/update/refeicao?id=X
ADMIN
Atualizar refeição
DELETE
/delete/refeicao?id=X
ADMIN
Deletar refeição

🥤 Catálogo - Bebidas (/bebida)
Método
Endpoint
Role
Descrição
POST
/criar/bebida
ADMIN
Criar bebida
GET
/obter/bebida?nome=X
❌
Buscar por nome
GET
/obter/{id}
❌
Buscar por ID
PATCH
/update/bebida?id=X
ADMIN
Atualizar bebida
DELETE
/delete/bebida?id=X
ADMIN
Deletar bebida

🛒 Pedidos (/pedidos)
Método
Endpoint
Role
Descrição
GET
/?clienteId=X
ADMIN, USER
Listar pedidos de um cliente
GET
/{id}?clienteId=X
ADMIN, USER
Buscar pedido específico
POST
/create/order
USER
Criar novo pedido
DELETE
/delete/order/{id}
ADMIN, USER
Cancelar pedido (soft delete)

👤 Clientes (/client)
Método
Endpoint
Descrição
POST
/newclient
Criar novo cliente
GET
/
Listar todos os clientes
DELETE
/deleteclient?id=X
Deletar cliente

👨‍💼 Usuários (/user)
Método
Endpoint
Descrição
POST
/create
Criar novo usuário com role
GET
/?username=X
Buscar usuário por username
DELETE
/deleteu?username=X
Deletar usuário

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
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
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
