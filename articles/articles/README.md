# Spring Boot REST APIs for Articles With Junit 5

Spring Boot project for the creation of RESTful APIS for Articles with JUnit 5 Tests. 
It includes integration test (for controller) and unit tests (for service).

## Technologies 

* Spring Boot (spring-boot-starter-web, spring-boot-starter-tomcat, spring-boot-starter-test).
* Java 17.
* Tomcat 8.5.x.
* Maven.
* JUnit 5.

## Architecture 

* **Controller:** is the presentation layer where the end points are located.Handles incoming HTTP requests related to articles and returns appropriate responses.
* **Service:** Contains business logic related to articles. It processes data received from the controller layer and interacts with the repository layer for data persistence.
* **Repository:** Manages data persistence and retrieval operations for articles, typically by extending Spring Data JPA repositories.
* **Domain:** Defines the core entities representing the data model of the application.
* **Data Transfer Object(DTOs):** Facilitates the transfer of data between layers, especially between the client and server, without exposing the internal data structures.



## Dependency's

1. **Spring Data JPA**: 
	For database access and integration with JPA.
	
2. **Spring Boot Starter Validation**: 
	For adding validation support to the application.
	
3. **Spring Boot Starter Web**: 
	For building web applications and RESTful APIs.
	
4. **Spring Boot Devtools**: 
	For development-time features like auto-restart and live reload.

5. **H2 Database**: 
	In-memory database for testing and development.
	
6. **Project Lombok**: 
	Reduces boilerplate code by auto-generating methods like getters and setters.

7. **Spring Boot Starter Test (JUnit 5)**: 
	For testing Spring Boot applications with JUnit 5 and other testing libraries.
	
## Exposed methods

Get article by country code. HTTP Method: GET

```
http://localhost:6060/report/es
```

Create an article. HTTP Method: POST

```
http://localhost:6060/article
```
```
{
"url":"https://www.rte.ie/news/weather/2025/0110/1007-weather-cold-snap/",
"socialScore": 60,
"countryCode": "ie"
}
```

Delete article by URL. HTTP Method: DELETE

```
http://localhost:6060/article?url=https://www.rte.ie/news/weather/2025/0110/1007-weather-cold-snap/
```

## Version Used

1. Java - **17.0.3.1**
2. Spring Boot - **3.0.4**
3. Postman - **11.31.0**

## Documentation and Examples

* [JUnit 5 Documentation](https://junit.org/junit5/docs/current/user-guide/): JUnit 5 documentation + migration guide
* [Spring Boot Testing Documentation](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#boot-features-testing-spring-boot-applicationso): Spring Boot section that talks about testing
* [Spring Boot Integration Testing](https://reflectoring.io/spring-boot-test/): Integration test example in Spring Boot
* [Spring Initializr](https://start.spring.io): To generate the structure of a Spring Boot Project
* [Spring Boot Testing](https://www.baeldung.com/spring-boot-testing)
