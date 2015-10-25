# Spring Boot JSON

Example app demonstrating the JSON capabilities of PostgreSQL in combination with native JPA queries.

## Getting started

### System Requirements

- Java 8
- Maven 3
- PosygreSQL 9.x

### Create database

Connect to your local psql database: `psql`

Execute the following:

```sql
CREATE USER sbje_user WITH PASSWORD 'sbje_password';
CREATE DATABASE sbje;
GRANT ALL PRIVILEGES ON DATABASE sbje to sbje_user;
```

### Building and running

This project uses Spring Boot and Maven. To run locally, type:

`mvn spring-boot:run`

### Testing

Test the native JSON query with cURL:

```bash
curl --user user:secret http://localhost:8080/users/search/findUsersByHobby?hobby=reading
```
