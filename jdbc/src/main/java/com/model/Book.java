package com.model;

import lombok.Data;
import lombok.ToString;

/**
 * @author Viktor Makarov
 */
@Data
public class Book {
    private long id;
    private String name;
    @ToString.Exclude
    private Author author;

    public Book() {
    }

    public Book(long id, String name, Author author) {
        this.id = id;
        this.name = name;
        this.author = author;
    }
}
