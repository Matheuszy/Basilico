# 🌿 Basilico API

> API REST desenvolvida com **Spring Boot** para gerenciar um ecossistema completo de restaurante — desde o catálogo de produtos até o fluxo de pedidos e clientes.

---

## 🚀 Visão Geral

A **Basilico API** foi projetada com foco em **escala, organização e clareza de domínio**, aplicando conceitos modernos como:

- Domain-Driven Design (DDD)
- Bounded Contexts
- Clean Architecture

---

## 🧠 Arquitetura

O sistema foi estruturado por **contextos delimitados**, separando responsabilidades de forma clara.

### 📦 Contextos

#### 🟢 `catalog`
- Gerenciamento de bebidas e refeições
- Núcleo de inventário

#### 🔵 `ordering`
- Fluxo de vendas
- Gestão de clientes
- Processamento de pedidos
- Cálculo automático de valores

#### 🟣 `management`
- Administração do sistema
- Controle de usuários internos

---

## 🛠️ Stack Tecnológica

- **Java 17**
- **Spring Boot 3**
- **Spring Data JPA**
- **PostgreSQL**
- **Flyway** (migrations)
- **Lombok**
- **Jakarta Validation**

---

## 📁 Estrutura do Projeto

```bash
src/main/java/com/Codexsystem/Basilico/Basilico/
│
├── catalog/        # Bebidas e refeições
├── ordering/       # Clientes e pedidos
├── management/     # Admins e regras internas
```

📡 Endpoints
🛒 Pedidos (/pedidos)

| Método | Endpoint          | Descrição                        |
| ------ | ----------------- | -------------------------------- |
| POST   | `/createorder`    | Criar novo pedido                |
| GET    | `/{id}`           | Buscar pedido por ID             |
| GET    | `?clienteId={id}` | Histórico de pedidos por cliente |

👤 Clientes (/client)
| Método | Endpoint     | Descrição                |
| ------ | ------------ | ------------------------ |
| POST   | `/newclient` | Criar cliente            |
| GET    | `/{id}`      | Buscar cliente + pedidos |

🍱 Catálogo

Refeições (/refeicao)
POST /criar/refeicao

Bebidas (/bebida)
POST /criar/bebida

🔄 Fluxo de Status do Pedido

SOLICITADO → EM_PREPARACAO → ENTREGUE
↘
CANCELADO

⚙️ Setup Local

1. Configure o banco de dados

spring.datasource.url=jdbc:postgresql://localhost:5432/basilico

spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha

spring.jpa.hibernate.ddl-auto=validate

2. Rodar o projeto

```bash
./mvnw spring-boot:run
```
👨‍💻 Autor

Matheus Almeida

📄 Licença

Este projeto está sob a licença MIT.


---

## 💥 Resultado

Se você colar isso no GitHub:
- Vai parecer projeto de **pleno/até sênior**
- Fica fácil de entender em 30 segundos
- Mostra arquitetura (isso pesa MUITO em vaga)

---
