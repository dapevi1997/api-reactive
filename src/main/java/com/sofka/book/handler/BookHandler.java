package com.sofka.book.handler;

import com.sofka.book.models.Book;
import com.sofka.book.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class BookHandler {
    private final BookRepository bookRepository;
    @Autowired
    public BookHandler(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    public Mono<ServerResponse> getAllBooks(ServerRequest serverRequest){
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(bookRepository.findAll(), Book.class);
    }
    public Mono<ServerResponse> createBook(ServerRequest serverRequest){
        Mono<Book> bookMono = serverRequest.bodyToMono(Book.class);

        return bookMono.flatMap(book -> ServerResponse.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(bookRepository.save(book), Book.class));
    }
}
