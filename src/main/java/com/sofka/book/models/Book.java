package com.sofka.book.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

/**
 * Class for to build the model of books.
 *
 * @version 1.0.0 2022-01-12
 *
 * @author DANIEL PEREZ VITOLA - dapevi97@gmail.com
 *
 * @since 1.0.0
 *
 */
@Data
@Document("book")
public class Book {
    @Id
    private String id;
    @Field
    @NotEmpty(message = "Title is required")
    @NotBlank(message = "The title must have at least 1 word")
    private String title;
    @Field
    @NotEmpty(message = "Author is required")
    @NotBlank(message = "The author must have at least 1 word")
    private String author;
}
