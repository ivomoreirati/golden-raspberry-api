# Movie Service - Quarkus

Este é um serviço Java baseado no framework **Quarkus**, utilizando **arquitetura mvc** . O serviço carrega dados de um arquivo CSV (`Movielist.csv`) no banco de dados em memória (**H2**) no startup.

---

## Tecnologias

- Java 17+
- Quarkus
- Maven
- Arquitetura MVC
- JPA / Hibernate (com Panache)
- RESTEasy Reactive
- H2 Database
- CSV (Apache Commons CSV)
- JUnit 5 + REST Assured

---

## Executando o Projeto

### 1. Clone o projeto

```bash
git clone https://github.com/seu-usuario/movie-service.git
cd movie-service
```

### 2. Rode em modo dev (hot reload ativado)

```bash
mvn quarkus:dev
```

### 3. Acesse os endpoints

- `GET /movies` → Lista todos os filmes carregados do CSV
- `GET /awards/intervals/2` → Lista o intervalo de premiações, onde podemos escolher se queremos 1 ou mais produtores, piores e melhores.

---

## Testes

### 1. Teste de integração

```bash
mvn clean install
```
---

##  Exemplos de Requests

###  Listar todos os filmes

```bash
curl http://localhost:8080/movies
```

### Buscar premiacoes

```bash
curl http://localhost:8080/awards/intervals/2
```