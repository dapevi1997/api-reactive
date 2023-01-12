package com.sofka.book.router;

import com.sofka.book.handler.BookHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class BookRouter {
    @Bean
    public RouterFunction<ServerResponse> bookRouterFunc(BookHandler bookHandler){
        return RouterFunctions.route(GET("/api/func/books/").and(accept(MediaType.TEXT_EVENT_STREAM))
        ,bookHandler::getAllBooks)
                .andRoute(POST("/api/func/books/").and(accept(MediaType.APPLICATION_JSON))
                ,bookHandler::createBook);
    }
}
