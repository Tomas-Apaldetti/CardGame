package com.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;

@SpringBootApplication
@OpenAPIDefinition
public class Application {
    // To acces Swagger UI http://localhost:8080/swagger-ui/index.html
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
