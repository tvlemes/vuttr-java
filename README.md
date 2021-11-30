# Projeto Vuttr Back-end

![](/assets/images/background.png)

[![NPM](https://img.shields.io/github/license/thiagovilarinholemes/project-vuttr-back-end)](https://github.com/thiagovilarinholemes/project-vuttr-back-end/blob/main/LICENSE)

<a href='https://project-vuttr.herokuapp.com/'> Demo para a API no Heroku</a>

<a href='https://project-vuttr.herokuapp.com/swagger-ui.html'> Demo para o Swagger</a>

<!-- TABLE OF CONTENTS -->
<details open="open">
  <summary>Indície</summary>
  <ol>
    <li>
      <a href="#sobre-o-projeto">Sobre o projeto</a>
      <ul>
        <li><a href="#frameworks-e-bibliotecas-utilizadas">Frameworks e bibliotecas utilizadas</a></li>
      </ul>
    </li>
    <li>
      <a href="#iniciando-o-projeto">Iniciando o projeto</a>
      <ul>
        <li><a href="#pré-requisitos">Pré requisitos</a></li>
      </ul>
    </li>
    <li>
      <a href="#uri">URI</a>
      <ul>
         <li><a href="#home">Home</a></li>
        <li><a href="#autenticação">Autenticação</a></li>
        <li><a href="#usuário">Usuário</a></li>
        <li><a href="#função">Função</a></li>
        <li><a href="#ferramenta">Ferramenta</a></li>
      </ul>
    </li>
    <li><a href="#licença">Licença</a></li>
    <li><a href="#contatos">Contatos</a></li>
  </ol>
</details>


<!-- Sobre o projeto -->
## Sobre o projeto

<p>Este projeto tem por finalidade criar um Back-end para o desafio da BossaBox. Nele foi construído um Back-end, VUTTR (Very Useful Tools to Remember), para gerenciar ferramentas com seus respectivos nomes, links, descrições e tags.</p>
<p>Também foi implementado autenticação JWT, contendo usuário - Users e funcões - Roles.</p>

### Frameworks e bibliotecas utilizadas

* [Spring Boot](https://spring.io/)
  * [web](https://spring.io/guides/gs/serving-web-content/)
  * [jpa](https://spring.io/projects/spring-data-jpa)
  * [security](https://spring.io/projects/spring-security)
  * [java-jwt](https://auth0.com/blog/spring-boot-authorization-tutorial-secure-an-api-java/)
  * [devtools](https://docs.spring.io/spring-boot/docs/1.5.16.RELEASE/reference/html/using-boot-devtools.html)
  * [jjwt](https://github.com/jwtk/jjwt)
  * [validation](https://spring.io/guides/gs/validating-form-input/)
  * [postgresql](https://spring.io/projects/spring-data-jpa)
  * [lombok](https://projectlombok.org/)
  * [swagger2](https://swagger.io/)
* [H2](http://www.h2database.com/html/main.html)
* [Postgres](https://www.postgresql.org/)

<!-- Iniciando o projeto -->
## Iniciando o projeto

Para iniciar o projeto é necessário baixar o repositório e importar para IDE(Eclipse, Intellij, VS Code...) de preferência. 

### Pré requisitos

* [Java SDK](https://www.oracle.com/br/java/technologies/javase/javase-jdk8-downloads.html)
* [Postgres](https://www.postgresql.org/download/)

<!-- URI -->
## URI

URI para acesso a API.

Para ter acesso as ferramentas - Tools, o usuário deve estar logado.

<!-- Home -->
### Home
* GET `/api/home`

<!-- Autenticação -->
### Autenticação
* POST `/api/authenticate`

  JSON enviado pelo body:
  ```
  {
    "email": "email",
    "pass": "senha"
  }
  ```

<b>OBS.: </b> O Token é retornado no body

<!-- Usuário -->
### Usuário

* GET `/api/users`
* GET `/api/users?page=0&size=20`
* GET `/api/users?name=name`
* GET `/api/users/{id}`
* POST `/api/users`

  JSON enviado pelo body:
  ```
  {
    "name": "Fulano de Tal",
    "email": "fulanodetal@gmail.com",
    "password": "123",
    "userStatus": 1,
    "role": 
      {
        "id": 1,
        "nameRole": "ROLE_ADMIN",
        "description": "Função de Administrador do Sistema"
      }
  }
  ```
 
* PUT `/api/users/{id}`

  JSON enviado pelo body:
  ```
  {
    "id": 1,
    "name": "Thiago Vilarinho Lemes ATUALIZADO",
    "email": "ATUALIZADO",
    "userName": "thiago.lemes",
    "password": "123 ATUALIZADO",
    "userStatus": "ACTIVE",
     "role": 
        {
            "id": 3,
            "nameRole": "ROLE_USE - ATUALIZADO",
            "description": "Função de Administrador do Sistema - ATUALIZADO"
        }
  }
  ```
 
* DELETE `/api/users/{id}`

<!-- Função -->
### Função

* GET `/api/roles`
* GET `/api/roles?page=0&size=20`
* GET `/api/roles?name=name`
* GET `/api/roles/{id}`
* POST `/api/roles`

  JSON enviado pelo body:
  ```
  {
    "name": "ROLE_USER",
    "description": "Função de Teste do Sistema"
  }
  ```
 
* PUT `/api/users/{id}`

  JSON enviado pelo body:
  ```
  {
    "id": 4,
    "name": "ROLE_TEST - ATUALIZADO",
    "description": "Função de Teste do Sistema - ATUALIZADO"
  }
  ```
 
* DELETE `/api/roles/{id}`

<!-- Tools -->
### Ferramenta

* GET `/api/tools`
* GET `/api/tools?page=0&size=20`
* GET `/api/tools?name=name`
* GET `/api/tools/{id}`
* POST `/api/tools`

  JSON enviado pelo body:
  ```
  {
    "title": "ReactJS",
    "link": "https://pt-br.reactjs.org/",
    "description": "Framework ReactJS - JavaScript / TypeScript",
    "tags": "ReactJS JavaScript TypeScript"
  }
  ```
 
* PUT `/api/tools/{id}`

  JSON enviado pelo body:
  ```
  {
    "title": "ReactJS ATUALIZADO",
    "link": "https://pt-br.reactjs.org/ ATUALIZADO",
    "description": "Framework ReactJS - JavaScript / TypeScript ATUALIZADO",
    "tags": "ReactJS JavaScript TypeScript ATUALIZADO"
  }
  ```
 
* DELETE `/api/tools/{id}`

<!-- Licença -->
## Licença

Distribuído sob a licença MIT License. Veja a `LICENSE` para mais informações.

<!-- Contatos -->
## Contatos

Autor: <i>`Thiago Vilarinho Lemes`</i>

Site: [<i>`https://thiagolemes.tech`/</i>](https://thiagolemes.tech/)

Email: <i>`lemes_vilarinho@yahoo.com.br`</i>

Likedin: [<i>`Thiago Vilarinho Lemes`</i>](https://www.linkedin.com/in/thiago-vilarinho-lemes-b1232727/)
