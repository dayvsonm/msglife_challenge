# Documentação da API

Esta é a documentação da API, que descreve os principais detalhes e funcionalidades disponíveis.

## Swagger

A documentação completa da API está disponível no Swagger. Para acessar, siga os passos abaixo:

1. Certifique-se de que o projeto está em execução localmente. Para iniciar o projeto, use o comando:



```bash
docker-compose up --build
```


2. Após iniciar o projeto, acesse o Swagger pelo navegador usando o seguinte URL:
```bash
http://localhost:8080/swagger-ui.html
```
Isso abrirá a interface do Swagger, onde você pode explorar todos os endpoints disponíveis, suas operações, parâmetros e modelos de dados.

OBS: existe uma collection POSTMAN para utilização nos arquivos do projeto chamada:

```bash
MSGLIFE Challenge.postman_collection.json
```

## Teste de Upload CSV

Para testar a funcionalidade de upload de arquivos CSV, utilize o arquivo chamado `mortalitytable.csv` que está na raiz do projeto. O arquivo CSV deve seguir o seguinte formato:
```bash
isoCode,year,rate_female,rate_male
BR,1903,4.3,2.4
BR,1904,4.3,5.4
PT,1903,3.0,4.2
```

## Carga Inicial de Dados

Para o recurso `Population`, foi utilizada uma carga inicial que é realizada sempre que o projeto inicializa. Essa carga inicial é obtida da API mock:
```bash
https://run.mocky.io/v3/0040f608-05f7-4635-a113-e52da013b2c0
```


Essa carga inicial preenche o banco de dados com dados de populações pré-existentes, garantindo que haja informações disponíveis para consulta.

## Tratamento para POST sem Population Cadastrada

Ao realizar um POST para recursos que dependem da entidade `Population` e não houver uma entrada correspondente no banco de dados, o sistema cria automaticamente um mock de `Population` com um valor de Range Integer. Isso garante que as operações possam prosseguir mesmo sem dados reais de população.


## Tecnologias Utilizadas

O projeto utiliza as seguintes tecnologias principais:

- **Java 21**: Linguagem de programação base do projeto.
- **Spring Boot**: Framework utilizado para desenvolvimento de aplicativos em Java.
- **Spring Boot Starter Data JPA**: Componente do Spring Boot para acesso e manipulação de dados usando o padrão de persistência JPA (Java Persistence API).
- **Spring Boot Starter Web**: Componente do Spring Boot para desenvolvimento de aplicativos web RESTful.
- **Spring Boot DevTools**: Ferramenta que melhora a experiência de desenvolvimento Spring Boot com suporte a reinicialização automática.
- **PostgreSQL**: Banco de dados relacional utilizado para armazenamento de dados.
- **Flyway**: Ferramenta utilizada para controle de versão e migração de banco de dados.
- **Lombok**: Biblioteca Java que reduz a verbosidade do código gerando automaticamente métodos como getters, setters e construtores.
- **Springdoc OpenAPI**: Biblioteca para gerar automaticamente a documentação da API no formato OpenAPI (anteriormente conhecido como Swagger).
- **Jackson Databind**: Biblioteca para processamento de JSON em Java.
- **OpenCSV**: Biblioteca para leitura e escrita de arquivos CSV em Java.

