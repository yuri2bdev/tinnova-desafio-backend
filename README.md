# 📊 Desafio Técnico - Tinnova

Este projeto contém as soluções para os exercícios propostos no desafio técnico da Tinnova. As implementações foram desenvolvidas em **Java 21** com **Spring Boot**, explorando boas práticas de clean code, SOLID, testes automatizados e arquitetura RESTful.

---

## 📌 Descrição dos Exercícios

### 1. Cálculo Percentual de Votos
Calcula os percentuais de votos válidos, brancos e nulos com base no total de eleitores.

### 2. Ordenação com Bubble Sort
Recebe uma lista de inteiros e retorna a lista ordenada usando o algoritmo Bubble Sort.

### 3. Cálculo de Fatorial
Recebe um número inteiro positivo e retorna o fatorial.

### 4. Soma de Múltiplos de 3 ou 5
Soma todos os números múltiplos de 3 ou 5 abaixo de 10.

### 5. Cadastro de Veículos
API RESTful com CRUD completo de veículos, incluindo filtros por marca, ano, status (vendido/não vendido), além de contagens por década e marca.

---

## 🛠️ Tecnologias Utilizadas

- Java 21
- Spring Boot
- Maven
- JUnit 5 e Mockito
- Spring Data JPA
- Lombok
- Swagger
- H2 Database (para testes)

---

## ▶️ Como Rodar o Projeto

1. Clone o repositório:

```bash
git clone https://github.com/seu-usuario/nome-do-repositorio.git
```

2. Navegue até a pasta do projeto:

```bash
cd nome-do-repositorio
```

3. Compile e execute:

```bash
./mvnw spring-boot:run
```

> O Swagger estará disponível em: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

---

## 🧪 Testes

Execute os testes com:

```bash
./mvnw test
```

---

## 📂 Estrutura de Pastas

```
src
 └── main
     ├── java
     │   └── com.tinnova.desafio
     └── resources
         └── application.yml
 └── test
     └── java
         └── com.tinnova.desafio
```

---

### 🔄 Testes com Postman

Você pode testar os endpoints da API utilizando a collection do Postman disponível neste repositório.

👉 [Download da Collection](./postman/veiculos-api.postman_collection.json)

Importe no seu Postman e divirta-se! 🚀

## 💬 Contato

Caso tenha dúvidas ou sugestões, fique à vontade para entrar em contato!

---


