# Servicio Suggestion

[TOC]

### Reference Documentation
For further reference, please consider the following sections:

* [Spring batch](https://docs.spring.io/spring-batch/docs/current/reference/html/index.html)

## Introduction

Este servicio devolvera un JSON con los datos obtenidos por medio de 3 parámetros pais que es requerido y longitud y latitud que son parametros opcionales. 

## Dependencies

The application was created using Spring Initialzr (https://start.spring.io) with the followind dependencies:


```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-neo4j</artifactId>
</dependency>
```

## Design notes

- Thre controllers are developed: one for static information, one for Customer files and one for other information.



## How to Run it


### Local run using the terminal

```bash
mvn clean package -DskipTests
mvn spring-boot:run
```
```URL Local Host
http://localhost:8080/gendra/suggestion/
```

You can use postman to test the micro services.
