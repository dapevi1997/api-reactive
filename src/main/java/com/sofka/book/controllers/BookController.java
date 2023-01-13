package com.sofka.book.controllers;

import com.sofka.book.models.Book;
import com.sofka.book.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/reactive/api/books")
public class BookController {
    private final BookService bookService;

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
    public Mono<Book> postBook(@RequestBody Book book){
        return bookService.postBook(book).log();
    }
    @PutMapping(path = "/{id}")
    public Mono<ResponseEntity<Book>> updateBook(@PathVariable String id, @RequestBody Book book ){
        return bookService.updateBook(id,book);
    }
    @DeleteMapping(path = "/{id}")
    public Mono<ResponseEntity<Void>> deleteBookById(@PathVariable String id){
        return bookService.deleteBook(id)
                .map(r -> ResponseEntity.ok().<Void>build())
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
