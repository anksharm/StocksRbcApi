# StocksDepo

# Sample REST CRUD API with Spring Boot, Postgres and JPA

## Steps to Setup

**1. Clone the application**

```bash

```

**2. Create Postgres database**
```bash
create database rbc_stockapi
```

**3. Change postgres username and password as per your installation**

+ open `src/main/resources/application.properties`

+ change `spring.datasource.username` and `spring.datasource.password` as per your postgres installation

**4. Build and run the app using maven**

```bash
mvn package
java -jar target/bc_stockdemo-0.0.1-SNAPSHOT.jar

```

Alternatively, you can run the app without packaging it using -

```bash
mvn spring-boot:run
```
(server.port =9000) is set up at application.properties. You can change that if needed. 
The app will start running at <http://localhost:9000>.

## Explore Rest APIs

The app defines following CRUD APIs.

    GET /stocks
    
    POST /stocks
    
    GET /stocks/{stockname}
    
    POST /stocks/bulkupload
