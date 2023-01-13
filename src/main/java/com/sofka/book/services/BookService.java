package com.sofka.book.services;

import com.sofka.book.models.Book;
import com.sofka.book.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

/**
 * Class Service.
 *
 * @version 1.0.0 2022-01-12
 *
 * @author DANIEL PEREZ VITOLA - dapevi97@gmail.com
 *
 * @since 1.0.0
 *
 */
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
    public Mono<Book> findById(String id){
        return bookRepository.findById(id);
    }
    public Mono<Book> postBook(Book book){
        return bookRepository.save(book).log();
    }
    public Mono<ResponseEntity<Book>> updateBook(String id, Book book){
        return bookRepository.findById(id).flatMap(oldBook -> {
            oldBook.setTitle(book.getTitle());
            oldBook.setAuthor(book.getAuthor());
            return bookRepository.save(oldBook);
        }).map(updateBook -> new ResponseEntity<>(updateBook, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.OK));
    }
    public Mono<Book> deleteBook(String id){
        return bookRepository.findById(id)
                .flatMap(deletedBook -> bookRepository.delete(deletedBook)
                        .then(Mono.just(deletedBook)));
    }

}
