<div align="center">

![omnihealth-logo](https://github.com/Douglas-Rafael/omnihealth-api/assets/137113815/8670faac-ef02-460f-a249-2243c507838b)

# OmniHealth API

</div>

[![Java Version](https://img.shields.io/badge/Java-17-blue)](https://www.oracle.com/java/technologies/javase-downloads.html)
[![Spring Boot Version](https://img.shields.io/badge/Spring%20Boot-3.1.4-brightgreen)](https://spring.io/projects/spring-boot)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

This is the RESTful API for the OmniHealth medical clinic application.

## Table of Contents
- [Description](#description)
- [Technologies Used](#technologies-used)
- [Configuration](#configuration)
- [Running the Project](#running-the-project)
- [Getting Started](#getting-started)
- [License](#license)

## Description

This API provides the backend services for the OmniHealth medical clinic application. It is built using Java with the Spring Boot framework and follows RESTful design principles.

## Technologies Used

- Java 17
- Spring Boot 3.1.4
- Spring Boot Starter Web
- Spring Boot DevTools
- Lombok
- Spring Boot Starter Test
- Spring Boot Starter Data JPA
- Spring Boot Starter Validation
- Flyway
- Flyway MySQL
- MySQL Connector/J
- Spring Boot Starter Security
- Spring Security Test
- Auth0 Java JWT
- Springdoc OpenAPI

## Configuration

### Prerequisites

- [JDK 17](https://www.oracle.com/br/java/technologies/downloads/#jdk17-windows) installed
- [Maven](https://maven.apache.org/download.cgi) installed
- [MySQL](https://dev.mysql.com/downloads/installer/) installed and configured

### Database Configuration

Edit the database connection settings in the `application.properties` file.

```
spring.datasource.username=your_username
spring.datasource.password=your_password
```
## Running the Project

Clone the repository and navigate to the project directory. Then, run the following command:

```
mvn spring-boot:run
```
### Getting Started

The documentation of the api will be available at http://localhost:8080/swagger-ui/index.html

![Captura de tela 2023-11-04 000910](https://github.com/Douglas-Rafael/omnihealth-api/assets/137113815/c0c21656-9c54-40c4-b8ca-42e562a2fb9e)

## License

This project is licensed under the MIT License - see the [LICENSE](https://github.com/Douglas-Rafael/omnihealth-api/blob/main/LICENSE) file for details.
