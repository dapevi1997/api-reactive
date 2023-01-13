package com.sofka.book.router;

import com.sofka.book.handler.BookHandler;
import com.sofka.book.models.Book;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
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
            {
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
            ),
            @RouterOperation(
                    path = "/api/func/books/",
                    produces = {
                            MediaType.APPLICATION_JSON_VALUE
                    },
                    method = RequestMethod.POST,
                    beanClass = BookHandler.class,
                    beanMethod = "createBook",
                    operation = @Operation(
                            operationId = "createBook",
                            responses = {
                                    @ApiResponse(
                                            responseCode = "201",
                                            description = "successful operation",
                                            content = @Content(
                                                    schema = @Schema(
                                                            implementation = Book.class
                                                    )
                                            )
                                    )
                            },
                            requestBody = @RequestBody(
                                    content = @Content(schema = @Schema(
                                            implementation = Book.class
                                    ))
                            )
                    )
            ),
                    @RouterOperation(
                            path = "/api/func/books/{id}",
                            produces = {
                                    MediaType.APPLICATION_JSON_VALUE
                            },
                            method = RequestMethod.PUT,
                            beanClass = BookHandler.class,
                            beanMethod = "updateBook",
                            operation = @Operation(
                                    operationId = "updateBook",
                                    responses = {
                                            @ApiResponse(
                                                    responseCode = "200",
                                                    description = "succesful operation",
                                                    content = @Content(schema = @Schema(
                                                            implementation = Book.class
                                                    ))
                                            ),
                                            @ApiResponse(responseCode = "404", description = "Book not found")
                                    },
                                    parameters = {
                                            @Parameter(in = ParameterIn.PATH, name = "id" )
                                    },
                                    requestBody = @RequestBody(
                                            content = @Content(schema = @Schema(
                                                    implementation = Book.class
                                            ))
                                    )
                            )
                    ),
                    @RouterOperation(
                            path = "/api/func/books/{id}",
                            produces = {
                                    MediaType.APPLICATION_JSON_VALUE
                            },
                            method = RequestMethod.DELETE,
                            beanClass = BookHandler.class,
                            beanMethod = "deleteBook",
                            operation = @Operation(
                                    operationId = "deleteBook",
                                    responses = {
                                            @ApiResponse(
                                                    responseCode = "200",
                                                    description = "succesful operation",
                                                    content = @Content(schema = @Schema(
                                                            implementation = Book.class
                                                    ))
                                            ),
                                            @ApiResponse(responseCode = "404", description = "Book not found")
                                    },
                                    parameters = {
                                            @Parameter(in = ParameterIn.PATH, name = "id" )
                                    }

                            )
                    )

            }
    )
    public RouterFunction<ServerResponse> bookRouterFunc(BookHandler bookHandler){
        return RouterFunctions.route(GET("/api/func/books/").and(accept(MediaType.APPLICATION_JSON))
        ,bookHandler::getAllBooks)
                .andRoute(POST("/api/func/books/").and(accept(MediaType.APPLICATION_JSON))
                ,bookHandler::createBook)
                .andRoute(PUT("/api/func/books/{id}").and(accept(MediaType.APPLICATION_JSON))
                ,bookHandler::updateBook)
                .andRoute(DELETE("/api/func/books/{id}").and(accept(MediaType.APPLICATION_JSON))
                ,bookHandler::deleteBook);
    }
}
