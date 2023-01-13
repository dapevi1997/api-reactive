package com.sofka.book;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Class that contains the main method.
 *
 * @version 1.0.0 2022-01-12
 *
 * @author DANIEL PEREZ VITOLA - dapevi97@gmail.com
 *
 * @since 1.0.0
 *
 */
@SpringBootApplication
@OpenAPIDefinition(info = @Info(
        title = "Reactive CRUD",
        version = "1.0.0",
        description = "Creating a reactive CRUD with Java 11 and Spring Boot 2.7.7"
))
public class BookApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookApplication.class, args);
    }

}
