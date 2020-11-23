# StocksDepo

# Design Decisions 

While desiging an API, the main goal for me is to thinking about the scalability of my design. Some questions to think are - 
+ Will the design work when more functionality needs to be added ? 
+ Will adding more functionality like tables/data, authorization, third-party integration, will it be feasible to add these aspects ? 
+ All services should be designed so it can is externalizable. 

While keeping some of these factors in mind, I prefer to have a more de-coupled or loose-coupled design to the API. How do we achieve that? 
I have used the Spring RESTFul framework to develop the APi with implementing layers that is Controller layer, Service layer, and DB layer.
StockController layer consists of the request mapping. 
Service Layer consists of the logic/implementation part for the requests. 
DB layer consists of the Data mapping using JPA and using the persistence api. 
Repository interface extends the JPA- use this to write custom queries if needed and use it for CRUD operations. 

**Other Layers I would like to add with time: 

Service interface - An interface for the StockService. 
Data access layer between the services and using the db layer. Makes for a better and more robust design. 


**Some key points used while designing
+ Keeping the endpoints simple - like using `/stocks` for both get and post request. Instead of `/getstocks` or `/savestocks`
+ Used right HTTP methods for type of operation. Used HTTP.GET and HTTP.POST. 
+ Used HTTP.BAD_REQUEST code for handling exceptions/errors occurred. 
+ Used HTTTP.OK code with message to show that request went fine but no/empty results for the request. 
+ Goal to create a more loosly-coupled solution by implementing controller, service and Db layer. 

# Assumptions 
+ No Authorization is needed for the get and post request. 
+ The Data is populated staright from the file, and stored in one table. 
+ There is no maintainibility added for logging/reporting using insertion timestamps for the table. 


# Improvements 

**This I would like to improve if with time: 

+ Add the DAO Pattern to the design. It will make the services layer loose coupled from the DB/Entity/DataAccess layer. 
+ Have each of controller, services and data layer in separate modules to define more de-coupling( needed in more scalable applications with more functionalities to the api)
+ Add improved handling for error message sending. Give it a defined format of customized status codes, error messages and have a error.messages file to have better maintainibility. 
+ Add environnment properties file to differentiate between production, testing, development code. 
+ Add more number of tests for functional and unit testing 
+ Add Authorization before hitting the rest api. Using Jwt tokens, will add Authorizationn as a requirement/mandate before Post Request. 
+ Add more validations capability. 

# Sample REST CRUD API with Spring Boot, Postgres and JPA

## Steps to Setup

**1. Clone the application**

```bash
https://github.com/anksharm/StocksRbcApi.git
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
java -jar target/rbc_stockdemo-0.0.1-SNAPSHOT.jar

```

Alternatively, you can run the app without packaging it using -

```bash
mvn spring-boot:run
```
(server.port =9000) is set up at application.properties. You can change that if needed. 
The app is running at for get - <http://localhost:9000/stocks>.

## Explore Rest APIs

The app defines following CRUD APIs.

    GET /stocks
    
    POST /stocks
    
    GET /stocks/{stockname}
    
    POST /stocks/bulkupload
