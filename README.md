# WirecardBackendChallenge
Wirecard Backend Challenge

## Application Design

  A aplicação foi feita em Java, em seguida listo e justifico a escolha de cada um 
  dos componentes:  

* Spring Boot 2 (framework principal) - escolhido pela facilidade de desenvolvimento 
  e sua alta portabilidade desse sistema.  
  
* Maven (ferramenta de build) - padrão bem usado e consolidado de build, inclusive 
  usado também pelas principais ferramentas DevOps.
  
  MongoDB (banco de dados) - base de dados baseada em documentos (Document Database) 
  que facilita muito o trabalho com sistemas que usam REST.
  
  Lombok (biblioteca) - para gerar os getters,setters,builders e loggers.
  
  Credit Card Validator (biblioteca) - para validar o número de cartão de crédito. 

## Application Architecture

* A aplicação usa o padrão: * Controller -> Service -> Repository ;
* Pela simplicidade do sistema não foram usados Facades;
* Para o checkout acessar a API de pagamento internamente, foi usada a instância do Controller 
  ao invés de chamada REST por estarem no mesmo sistema.  
  
## Quickstart

### pré-requisitos

* Java 11+
* docker
* docker compose  

### Subindo o sistema

Para subir aplicação precisamos de duas janelas , a primeira subimos o banco de dados 
rodando o comando na raiz do projeto:

```
docker-compose up
```

Será criando um diretório *mongo-data* com os arquivos do banco de dados, e o arquivo 
*mongo-init.js* será executado, cadastrando um _buyer_ no sistema.

Para acessar ao banco de dados use um client MongoDB padrão (ou outro como RoboMongo) 
e acesse na porta 27017. 

A segunda janela, também rodando na raiz do projeto, subirá a aplicação com Spring Boot:

```
mvnw spring-boot:run
```

### Usando o sistema

Para acessar ao sistema acesse com o browser em http://127.0.0.1:8080 , o resultado 
esperado é ver o texto *Wirecard Backend Challenge* 

### Testes do Postman

Dentro da pasta *doc* temos os testes para importar no _Postman_ , são eles:

* Home - testa a home page
* list buyers - lista todos os buyers
* payment - boleto - testa o pagamento de boleto
* payment - credit card  - testa o pagamento de cartão de crédito
* checkout - boleto - testa o checkout de boleto
* checkout - creditcard - testa o checkout de cartão de crédito
* payment status - busca o status do pagamento 
* list payments - lista todos os pagamentos

### Teste completo da API

Execute as rotinas do Postman nessa ordem:

* payment - credit card
* checkout - creditcard
* payment status 
* list payments

### TODO

Testes na API de checkout (apenas a de pagamento foi testada)

