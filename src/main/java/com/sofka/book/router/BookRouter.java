package com.sofka.book.router;

import com.sofka.book.handler.BookHandler;
import com.sofka.book.models.Book;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class BookRouter {
    @Bean
    @RouterOperations(
            @RouterOperation(
                    path = "/api/func/books/",
                    produces ={
                            MediaType.APPLICATION_JSON_VALUE
                    },
                    method = RequestMethod.GET,
                    beanClass = BookHandler.class,
                    beanMethod = "getAllBooks",
                    operation = @Operation(
                            operationId = "getAllBooks",
                            responses = {
                                    @ApiResponse(
                                            responseCode = "200",
                                            description = "successful operation",
                                            content = @Content(schema = @Schema(
                                                    implementation = Book.class
                                            ))
                                    )
                            }
                    )
            )
    )
    public RouterFunction<ServerResponse> bookRouterFunc(BookHandler bookHandler){
        return RouterFunctions.route(GET("/api/func/books/").and(accept(MediaType.APPLICATION_JSON))
        ,bookHandler::getAllBooks)
                .andRoute(POST("/api/func/books/").and(accept(MediaType.APPLICATION_JSON))
                ,bookHandler::createBook);
    }
}
