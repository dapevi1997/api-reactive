package com.sofka.book.services;

import com.sofka.book.models.Book;
import com.sofka.book.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
public class BookService {
    private final BookRepository bookRepository;
    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Flux<Book> getAllBook(){
        return bookRepository.findAll().delayElements(Duration.ofSeconds(1)).log();
    }
    public Mono<Book> postBook(Book book){
        return bookRepository.save(book).log();
    }
}
