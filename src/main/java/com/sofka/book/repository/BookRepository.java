package com.sofka.book.repository;

import com.sofka.book.models.Book;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface BookRepository extends ReactiveMongoRepository<Book,String> {
}
