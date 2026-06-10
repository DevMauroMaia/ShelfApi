# ShelfAPI
 
REST API para gerenciamento de produtos e categorias, desenvolvida com Spring Boot.
 
## 🛠 Tecnologias
 
- Java
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Lombok
- Maven
## 📁 Estrutura do Projeto
 
```
src/main/java/com/devmauro/ShelfAPI/
├── controller/       # Endpoints REST
├── service/          # Regras de negócio
├── database/
│   ├── model/        # Entidades JPA
│   └── repository/   # Interfaces de acesso ao banco
├── dto/              # Objetos de transferência de dados
├── exception/        # Exceções customizadas
└── handler/          # GlobalExceptionHandler
```
 
## 🔗 Endpoints
 
### Produtos `/products`
 
| Método | Rota            | Descrição               |
|--------|-----------------|-------------------------|
| GET    | `/products`     | Lista todos os produtos |
| GET    | `/products/{id}`| Busca produto por ID    |
| POST   | `/products`     | Cadastra novo produto   |
| PUT    | `/products/{id}`| Atualiza produto        |
| DELETE | `/products/{id}`| Remove produto          |
 
### Categorias `/categories`
 
| Método | Rota                      | Descrição                  |
|--------|---------------------------|----------------------------|
| GET    | `/categories`             | Lista todas as categorias  |
| GET    | `/categories/{id}`        | Busca categoria por ID     |
| GET    | `/categories/name/{name}` | Busca categoria por nome   |
| POST   | `/categories`             | Cadastra nova categoria    |
| PUT    | `/categories/{id}`        | Atualiza categoria         |
| DELETE | `/categories/{id}`        | Remove categoria           |
 
## ⚙️ Como rodar
 
### Pré-requisitos
 
- Java 17+
- PostgreSQL
- Maven
### Configuração
 
1. Clone o repositório:
```bash
git clone https://github.com/DevMauroMaia/ShelfApi.git
```
 
2. Configure as variáveis de ambiente:
```
DATABASE_USERNAME=seu_usuario
DATABASE_PASSWORD=sua_senha
```
 
3. Crie o banco de dados no PostgreSQL:
```sql
CREATE DATABASE "ShelfAPI";
```
 
4. Execute o projeto:
```bash
./mvnw spring-boot:run
```
 
A API estará disponível em `http://localhost:8080`
 
## 👤 Autor
 
**Mauro Maia** — [GitHub](https://github.com/DevMauroMaia) · [LinkedIn](https://linkedin.com/in/mauromaianeto)
