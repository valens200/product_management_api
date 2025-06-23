<h1 align = "center" id = "project-details">📝 Project Details</h1>

## Overview

<p align="justify">
Productant is a simple, containerized Spring Boot REST API for managing products with full CRUD functionality. Built with Java 8 and PostgreSQL, it uses UUIDs for product identification and supports environment-variable-based configuration for flexibility. The project is fully Dockerized with Docker Compose to enable easy deployment and includes optional Swagger integration for API documentation and request validation. Productant demonstrates clean backend development practices with modern tools for scalable and maintainable applications
</p>

## Features

- Product Registration
- Updating Product
- Deleting Product
- Reading All Products
- User Authentication

## Technologies Used

- **Java**: Version 8
- **Spring Boot**:  Version 2.7.18
- **Database**: PostgreSQL

## Prerequisites
- Java Development Kit (JDK Version 8)
- PostgreSQL
- Git
- Docker
### Running the application

### Using Docker

If you don't have either javascript or java environment, please use docker to build the app and run it with zero issues

```sh
docker-compose up --build.
```

- You can also run it locally with the following steps if you don't prefer Docker.

1. Clone the repository:
   ```sh
   git https://github.com/valens200/productant_api
   ```
2. Navigate to the backend directory:
   ```sh
   cd productant_api
   ```
3. Install dependencies:
   ```sh
   ./mvnw install or mvn clean install
4. Package the application:
   ```sh
   ./mvnw clearn package
   ```  ```
    
#### The Instance can be accesed at

| Instance  |     URL       | credentials (user : password)|
|---------- |:-------------:|------:                       |
| Swagger UI   |  https://localhost:8080/swagger-ui/index.html  | admin: adminADMIN! |

<h1 align="center" id="deliverables">👨🏻‍🏫 DELIVERABLES <img src="https://api.ezeelo.com/Scripts/QRCode/Done.gif" width="40"></h1>

| Tasks   | Requested | Completed     | Remarks    |
| :---:       |    :----:   |    :---:      |    :---:      |
| Create Product | Yes | :heavy_check_mark: |Works Well 
| Get Product by Id | Yes       | :heavy_check_mark: |Works Well  |
| Get All Products | Yes        | :heavy_check_mark:  | Works Well |
| Delete Product| Yes | :heavy_check_mark:(partially) | Works Well |
| Update Product| Yes | :heavy_check_mark:(partially) | Works Well |
| Document APIs with OpenApi| Yes | :heavy_check_mark:(partially) | Works Well |
| Add Docker and Docker Compose | Yes  |:heavy_check_mark: | Docker is well configured.
| Add Authentication and Authorization | No  |:heavy_check_mark: | Added role based authentication with JWT and authorization

## Project Dependencies and Justification

This section outlines the key frameworks and libraries that I used in this Spring Boot backend project along with the reasons for their inclusion.

---

### Core Frameworks

#### Spring Boot Starters

- **spring-boot-starter-web**  
  This provided me with some essential components for building RESTful web services such as embedded Tomcat, and JSON serialization support.

- **spring-boot-starter-data-jpa**  
  I used data JPA to Simplify database interaction using Java Persistence API (JPA) with Hibernate as the implementation. It allowed me to map Java to the postgres database tables.

- **spring-boot-starter-data-jdbc**  
  I chose to use JDBC for database connections management. I like it's simplicity and how easy to use it is.

- **spring-boot-starter-security**  
  Just wanted to secure this product management. This is why I prefered to add this security project from spring to offer me that help.

---

## Database Connectivity

- **postgresql**  
  I had to sue PostgreSQL JDBC driver to enable database connectivity with PostgreSQL as requested in the task description.

- **h2** (test scope)  
  I used this in-memory database exclusively for testing in isolated environment like docker so that I be sure my application is error free. I didn't add more tests, but used it to make sure tests can successfully run in my docker containers.

---

## Security and Authentication

- **io.jsonwebtoken:jjwt**  
  Because I wanted to implement Role Based Authentication ( RBA ), I used JWT for generating and validting authentication tokens, ensurinng stateless and secure authentication mechanisms.

## Utility Libraries

- **org.modelmapper:modelmapper**  
  I like this library because it simplifies object mapping between DTOs and entities, reducing boilerplate code and improving maintainability.

- **lombok** (optional)  
  I just used Lombok for generateing boilerplate code such as getters, setters, constructors, and builders at compile-time, making code more concise and readable.


## Documentation

- **org.springdoc:springdoc-openapi-ui**  
  To Automatically generate Swagger documentation for REST APIs, I used OpenAPI so that I make a visual presentation and documentation of application's APIs.
<h1  id = "acknowledgements">Acknowledgements</h1>

<p align="justify">

I am genuinely excited about the opportunity to join and grow within the supportive and innovative community at Centrika. I look forward to receiving feedback on my technical assignment and hope to contribute positively to the team.

Participating in this interview process over the past days has been a rewarding experience and a valuable journey of growth both professionally and personally. The experience was more exciting and insightful than I initially expected.
I sincerely appreciate the opportunity and would like to thank everyone involved in the process for their time and support.

In conclusion, this interview journey at Centrika marks not just a milestone but the beginning of a new chapter where I hope to contribute actively and continue learning in a thriving environment.


<h1 align = "center" id = "connections">🌐 Let's connect! </h1>



- [LinkedIn](https://www.linkedin.com/in/valens-niyonsenga-947440228/)

- [GitHub](https://github.com/valens200)

- [Twitter](https://x.com/200Valens)
