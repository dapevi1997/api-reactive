package com.sofka.book.controllers;

import com.sofka.book.models.Book;
import com.sofka.book.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("reactive/api/books")
public class BookController {
    private final BookService bookService;
    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
    @PostMapping(path = "/")
    public Mono<Book> postBook(@RequestBody Book book){
        return bookService.postBook(book).log();
    }
}
