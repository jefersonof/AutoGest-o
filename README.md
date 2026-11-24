# AutoGestão Pro — Java

Migração inicial do sistema para Java 21, Spring Boot, Thymeleaf, JPA e MySQL.

## Pré-requisitos

- Java 21
- Maven 3.6.3 ou superior
- MySQL com o banco `oficina`

## Configuração

O usuário padrão é `root` e a senha vazia. Se seu MySQL usa senha, no Windows PowerShell execute:

```powershell
$env:DB_USER="root"
$env:DB_PASSWORD=""
mvn spring-boot:run
```

Sem senha:

```powershell
mvn spring-boot:run
```

Abra `http://localhost:8080`.

## O que já está pronto

- Cadastro do cliente junto com seu primeiro veículo.
- Consulta de clientes e respectivos veículos.
- Inclusão de outros veículos em um cliente existente.
- Busca de endereço pelo ViaCEP.
- Relacionamento JPA 1:N entre `clientes` e `veiculos`.
- Layout responsivo inspirado em painéis AdminLTE.


O Hibernate está configurado com `ddl-auto=update`: ele aproveita as tabelas existentes e cria/ajusta o que estiver faltando, sem apagar os dados.
<!-- Teste de comentário-->
