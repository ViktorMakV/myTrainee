package com.model;

import lombok.Data;

import java.util.List;

/**
 * @author Viktor Makarov
 */
@Data
public class Author {
    private long id;
    private String name;
    private List<Book> books;

    public Author() {
    }

    public Author(long id, String name) {
        this.id = id;
        this.name = name;
    }
}
