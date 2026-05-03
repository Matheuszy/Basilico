# Basilico API 🌿

API REST desenvolvida em Spring Boot para o gerenciamento de um ecossistema de restaurante, abrangendo desde o catálogo de produtos até o fluxo de vendas e gestão de clientes.

## 🚀 Reestruturação de Arquitetura
O projeto foi recentemente reestruturado de uma organização por "tipo de arquivo" para uma arquitetura baseada em **Contextos Delimitados (Bounded Contexts)**, seguindo princípios de Clean Architecture:

*   **`catalog`**: Gerenciamento de Bebidas e Refeições. É o núcleo de "inventário" do sistema.
*   **`ordering`**: Fluxo dinâmico de vendas, gestão de Clientes e processamento de Pedidos com cálculo automático de valores.
*   **`management`**: Administração do sistema e controle de usuários internos (Admins).

## 🛠️ Stack Tecnológica
*   **Java 17** & **Spring Boot 3**.
*   **Spring Data JPA**: Persistência e consultas otimizadas com `FETCH JOIN`.
*   **PostgreSQL**: Banco de dados relacional para produção.
*   **Flyway**: Controle de versionamento de banco de dados (Migrations).
*   **Lombok**: Redução de boilerplate para modelos e DTOs.
*   **Jakarta Validation**: Regras de integridade nos campos de entrada.

## 📂 Organização do Projeto
```text
src/main/java/com/Codexsystem/Basilico/Basilico/
  ├── catalog/        # Modelos de Bebida e Refeicao
  ├── ordering/       # Clientes, Pedidos e Status do Pedido
  ├── management/     # Usuários Admin e regras de negócio internas
  
  ````
  
📋 Endpoints Principais
🛒 Pedidos (/pedidos)
POST /createorder: Cria um pedido. Requer clienteId e listas de itens.

GET /{id}: Busca detalhes de um pedido por ID.

GET?clienteId={id}: Histórico de pedidos de um cliente específico.

👤 Clientes (/client)
POST /newclient: Cadastro de novos clientes.

GET /{id}: Retorna dados do cliente e seus respectivos pedidos.

🍱 Catálogo (/refeicao e /bebida)
POST /criar/refeicao: Adiciona novos pratos ao sistema.

POST /criar/bebida: Adiciona novas bebidas ao sistema.

🔄 Fluxo de Status do Pedido
Os pedidos seguem um ciclo de vida rigoroso controlado por Enums para garantir a integridade do processo:

SOLICITADO: Pedido recém-criado.

EM_PREPARACAO: Em processamento na cozinha.

ENTREGUE: Finalizado com sucesso.

CANCELADO: Pedido interrompido.

⚙️ Configuração Local
Para rodar o projeto, configure as credenciais do PostgreSQL no application.properties:

spring.datasource.url=jdbc:postgresql://localhost:5432/basilico
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=validate