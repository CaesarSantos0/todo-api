# Todo API - API RESTful de Gerenciamento de Tarefas (CRUD)

## Descrição

Este projeto é uma API RESTful desenvolvida com **Spring Boot** e **PostgreSQL** para realizar operações CRUD (Create, Read, Update, Delete) em uma lista de tarefas. Ele foi desenvolvido para demonstrar o uso de boas práticas no desenvolvimento de APIs, como validação de dados, manuseio de erros, e documentação automática com Swagger.

## Tecnologias Utilizadas

As seguintes tecnologias foram utilizadas para o desenvolvimento deste projeto:

- **Java 22**: Linguagem de programação utilizada para implementar a API.
- **Spring Boot 3.3.3**: Framework para simplificar a criação de aplicativos Java, especialmente APIs RESTful.
- **PostgreSQL**: Banco de dados relacional utilizado para armazenar as informações das tarefas.
- **Spring Data JPA**: Framework para facilitar o acesso aos dados do banco, mapeando entidades diretamente para o banco de dados.
- **Springdoc OpenAPI (Swagger)**: Utilizado para gerar documentação automática da API e criar uma interface interativa para testes.
- **Maven**: Gerenciador de dependências e automação de builds.
- **JUnit 5**: Framework de testes utilizado para garantir o correto funcionamento da aplicação.
  
## Habilidades Utilizadas e Desenvolvidas

Este projeto envolve o uso de diversas habilidades técnicas e práticas:

### Habilidades Utilizadas
- **Criação de APIs RESTful**: Design e implementação de uma API seguindo os princípios REST.
- **Validação de Dados**: Uso de anotações de validação como `@NotBlank`, `@Min`, e `@Max` para garantir a integridade dos dados.
- **Persistência de Dados com JPA**: Uso do Spring Data JPA para realizar operações no banco de dados de forma simples e eficaz.
- **Manuseio de Exceções**: Implementação de tratamento global de exceções com `@ControllerAdvice` e exceções personalizadas.

### Habilidades Desenvolvidas
- **Testes Automatizados**: Utilização do JUnit para criar testes automatizados de integração, garantindo que a API funcione como esperado.
- **Documentação com Swagger**: Habilidade de documentar e testar APIs de forma interativa através do Swagger.
- **Deploy de APIs**: Publicação do projeto em plataformas de controle de versão como GitHub, facilitando a colaboração e o versionamento.

## Funcionalidades da API

A API oferece as seguintes funcionalidades:

- **Criar uma nova tarefa**: Um usuário pode criar uma tarefa fornecendo um título, descrição, status de conclusão e prioridade.
- **Listar todas as tarefas**: Lista todas as tarefas registradas no banco de dados.
- **Atualizar uma tarefa existente**: Atualiza os dados de uma tarefa, como título, descrição, prioridade e status de conclusão.
- **Excluir uma tarefa**: Remove uma tarefa do sistema.
- **Validações de Dados**: Validações são aplicadas a campos obrigatórios como título e descrição, e a prioridade deve estar entre 0 e 5.



## EndPoints da API
### 1. Criar uma nova tarefa
**POST** `/tasks`

#### Request Body:
```json
{
  "title": "Tarefa de Exemplo",
  "description": "Descrição da tarefa",
  "completed": false,
  "priority": 2
}
```
#### Resposta de Sucesso (201):
```json
{
  "id": 1,
  "title": "Tarefa de Exemplo",
  "description": "Descrição da tarefa",
  "completed": false,
  "priority": 2
}
```
### 2. Listar todas as tarefas
**GET** `/tasks`

#### Resposta de sucesso (200):
```json
[
  {
    "id": 1,
    "title": "Tarefa de Exemplo",
    "description": "Descrição da tarefa",
    "completed": false,
    "priority": 2
  },
  ...
]
```
### 3. Atualizar uma tarefa existente
**PUT** `/tasks/{id}`

#### Request Body:
```json
{
  "title": "Tarefa Atualizada",
  "description": "Descrição atualizada da tarefa",
  "completed": true,
  "priority": 3
}
```
#### Resposta de sucesso (200):
```json
{
  "id": 1,
  "title": "Tarefa Atualizada",
  "description": "Descrição atualizada da tarefa",
  "completed": true,
  "priority": 3
}
```
### 4. Excluir uma Tarefa
**DELETE** `/tasks/{id}`

#### Resposta de Sucesso (204 - No Content)

# Como Acessar a Documentação da API pelo Swagger

Este guia explica como acessar a documentação da API usando a interface interativa do **Swagger**, que permite explorar e testar os endpoints da API.

## Passo a Passo

### 1. Certifique-se de que a API está rodando

Antes de acessar a documentação, você deve garantir que a API está em execução localmente ou no servidor. Se estiver rodando localmente, o comando abaixo deve ser usado para iniciar a aplicação:

```bash
mvn spring-boot:run
```
Verifique no terminal se a API foi iniciada corretamente e está rodando na porta padrão (8080, a menos que tenha sido configurada outra porta).

### 2. Acesse o Swagger pelo navegador
Com a API rodando, abra o navegador de sua preferência e insira a seguinte URL na barra de endereços:

http://localhost:8080/swagger-ui.html

Isso abrirá a interface gráfica do Swagger para a sua API.

### 3. Explorar a documentação interativa

Ao acessar o link, você verá uma interface interativa que lista todos os endpoints disponíveis na API. Cada endpoint tem a seguinte estrutura:

**Método HTTP** (GET, POST, PUT, DELETE)

**Caminho do Endpoint**

**Parâmetros de Requisição** (se houver)

**Corpo da Requisição** (se necessário)

### 4. Testar os Endpoints

Na interface do Swagger, você pode testar os endpoints diretamente. Siga os passos abaixo para realizar um teste:

**Selecione o endpoint** que deseja testar clicando nele.

Preencha os parâmetros ou o corpo da requisição, se aplicável.

Clique no botão **"Try it out".**

O Swagger enviará a requisição e exibirá a resposta no mesmo local, incluindo o **código de status** (como 200, 400, 404) e o **corpo da resposta.**

### 5. Explorar as Respostas
Além de enviar requisições, o Swagger também exibe exemplos das respostas esperadas para cada endpoint, o que facilita entender o comportamento da API.

### Resolução de Problemas 
**Erro 404**: Se a página do Swagger não abrir e você receber um erro 404, verifique se a API está rodando corretamente e se você está acessando o endereço correto.

**Porta Diferente**: Se a API estiver rodando em uma porta diferente da 8080, ajuste a URL para a porta correta. Exemplo:
```bash
http://localhost:PORTA_ALTERNATIVA/swagger-ui.html
```

## Como Utilizar a API
## Pré-requisitos

Para rodar a aplicação localmente, você precisará ter o seguinte instalado:

**Java 22**

**Maven**

**PostgreSQL**

#### Passo 1: Clonar o Repositório

Clone o repositório do GitHub para a sua máquina local:
```bash
git clone https://github.com/seuusuario/todo-api.git
```
Acesse o diretório do projeto:
```bash 
cd todo-api
```
#### Passo 2: Configuração do Banco de Dados
Este projeto utiliza o PostgreSQL como banco de dados. Certifique-se de que o PostgreSQL está instalado e rodando.

Edite o arquivo ```src/main/resources/application.properties``` para configurar as credenciais do banco de dados:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/test_db
spring.datasource.username=postgres
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
```
#### Passo 3: Instalar Dependências
Use o Maven para instalar as dependências do projeto. No diretório raiz do projeto, execute:
```bash
mvn clean install
```
#### Passo 4: Rodar a Aplicação
Para rodar a aplicação, execute o seguinte comando:
```bash
mvn spring-boot:run
```
A API estará rodando em ```http://localhost:8080```.

#### Passo 5: Acessar a API e a Documentação
**API**: Acesse os endpoints da API via ```http://localhost:8080/tasks.```
**Swagger**: Acesse a Documentação da API no Swagger em ```http://localhost:8080/swagger-ui.html```.

## Testes Automatizados 
Este projeto utiliza **JUnit 5** para testes automatizados. Esses testes cobrem as funcionalidades principais da API para garantir seu funcionamento correto.

#### Rodando os Testes
Para executar os testes, use o seguinte comando:
```bash
mvn test
```
Os testes são executados automaticamente e geram um relatório no terminal sobre o status de sucesso ou falha.

## Estrutura de pastas do projeto
Aqui está uma visão geral da estrutura de pastas do projeto:
```bash
todo-api/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/todoapi/
│   │   │       ├── controller/
│   │   │       ├── model/
│   │   │       ├── repository/
│   │   │       ├── service/
│   │   │       └── exception/
│   │   └── resources/
│   │       ├── application.properties
│   ├── test/
│       └── java/
│           └── com/example/todoapi/
│               └── TaskControllerTests.java
```
# Contribuição
Contribuições são bem-vindas! Se você encontrar algum problema ou tiver sugestões de melhorias, sinta-se à vontade para abrir uma **issue** ou enviar um **pull request.**

### Como Contribuir

**Fork este repositório.**

**Crie uma nova branch** para sua funcionalidade ou correção de bug.

**Commit suas alterações.**

**Envie um Pull Request** para revisão.

## Autor
Desenvolvido por Julio Santos. Para mais informações ou contato, acesse meu perfil no [LinkedIn](https://www.linkedin.com/in/juliosantos01/).
