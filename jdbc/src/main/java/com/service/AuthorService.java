package com.service;

import com.model.Author;

import java.util.List;

/**
 * @author Viktor Makarov
 */
public interface AuthorService {
    List<Author> getAllAuthors();

    Author findAuthor(long id);

    Author findAuthor(String name);

    boolean newAuthor(String authorName);

    boolean updateAuthor(Author author);

    boolean deleteAuthor(Author author);

    boolean addBookAndAuthor(String authorName, String bookName);
}
