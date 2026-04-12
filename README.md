# Lobar

API REST em Spring Boot para gerenciamento de cardapio, com foco inicial em **bebidas** e **refeicoes**.

## Visao geral

O projeto esta organizado em camadas (`controller`, `services`, `repository`, `model`, `dto`) e usa:

- Spring Boot (Web MVC)
- Spring Data JPA
- Flyway
- Bean Validation
- PostgreSQL
- Lombok

## Estrutura principal

```text
src/main/java/com/Codexsystem/Basilico/Basilico/
  BasilicoApplication.java
  cardapio/
    controller/
    dto/
    model/
    repository/
    services/
  Usuario/
    model/
    repository/
```

Migracoes SQL ficam em:

- `src/main/resources/db/migration/V1_bebida.sql`
- `src/main/resources/db/migration/V1_refeicao.sql`

## Requisitos

- Java 17
- Maven (ou Maven Wrapper do projeto)
- PostgreSQL em execucao

## Configuracao

Atualmente, `src/main/resources/application.properties` define apenas:

- `spring.application.name=Lobar`

Para executar com banco PostgreSQL, configure as propriedades de datasource (via `application.properties` ou variaveis de ambiente), por exemplo:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/lobar
spring.datasource.username=postgres
spring.datasource.password=postgres
spring.jpa.hibernate.ddl-auto=validate
spring.jpa.show-sql=true
```

## Como executar

### Windows (PowerShell)

```powershell
.\mvnw.cmd spring-boot:run
```

### Linux/macOS

```bash
./mvnw spring-boot:run
```

A aplicacao sobe por padrao em `http://localhost:8080`.

## Endpoints atuais

### Bebida

- `POST /bebida/criar/bebida`
- `GET /bebida/obter/bebida?nome={nome}`
- `GET /bebida/obter/{id}`

Exemplo de payload (`POST /bebida/criar/bebida`):

```json
{
  "nome": "Suco",
  "descricao": "Suco natural"
}
```

### Refeicao

- `POST /refeicao/criar/refeicao`
- `GET /refeicao/obter/refeicao?nome={nome}`
- `GET /refeicao/obter/{id}`

Exemplo de payload (`POST /refeicao/criar/refeicao`):

```json
{
  "nome": "Prato executivo",
  "descricao": "Arroz, feijao e frango"
}
```

## Testes

Para executar os testes:

### Windows (PowerShell)

```powershell
.\mvnw.cmd test
```

### Linux/macOS

```bash
./mvnw test
```

## Observacoes importantes

- Os endpoints de criacao hoje retornam DTOs, mas nao persistem os dados no banco.
- Existem validacoes na camada de servico para nome, descricao e valores monetarios.
- Revise as migracoes Flyway antes de uso em producao (tipos/nomes de colunas e sintaxe SQL).

## Proximos passos sugeridos

- Integrar os endpoints de criacao com os services/repositories para persistencia real.
- Padronizar os campos monetarios entre entidade, DTO e script SQL (`preco` x `valor`).
- Adicionar tratamento global de excecoes com respostas HTTP consistentes.
- Incluir testes de integracao para os endpoints REST.
