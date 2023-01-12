package com.sofka.book.services;

import com.sofka.book.models.Book;
import com.sofka.book.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class BookService {
    private final BookRepository bookRepository;
    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    public Mono<Book> postBook(Book book){
        return bookRepository.save(book).log();
    }
}
