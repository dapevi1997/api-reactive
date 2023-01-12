package com.sofka.book.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document("book")
public class Book {
    @Id
    private String id;
    @Field
    private String title;
    @Field
    private String author;
}
