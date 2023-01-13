package com.sofka.book.controllers;

import com.sofka.book.models.Book;
import com.sofka.book.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Class Controller.
 *
 * @version 1.0.0 2022-01-12
 *
 * @author DANIEL PEREZ VITOLA - dapevi97@gmail.com
 *
 * @since 1.0.0
 *
 */
@RestController
@RequestMapping("/reactive/api/books")
public class BookController {
    private final BookService bookService;
    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
    @GetMapping(value = "/", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Book> getAllBook(){
        return bookService.getAllBook();
    }
    @GetMapping(path = "/{id}")
    public Mono<Book> findById(@PathVariable String id){
        return bookService.findById(id);
    }

    @PostMapping(path = "/")
    public Mono<Book> postBook(@Validated @RequestBody Book book){
        return bookService.postBook(book).log();
    }
    @PutMapping(path = "/{id}")
    public Mono<ResponseEntity<Book>> updateBook(@PathVariable String id, @Validated @RequestBody Book book ){
        return bookService.updateBook(id,book);
    }
    @DeleteMapping(path = "/{id}")
    public Mono<ResponseEntity<Void>> deleteBookById(@PathVariable String id){
        return bookService.deleteBook(id)
                .map(r -> ResponseEntity.ok().<Void>build())
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
