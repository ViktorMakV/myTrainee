package com.service;

import com.model.Author;
import com.model.Book;

/**
 * @author Viktor Makarov
 */
public interface BookService {
    void fillAuthor(Author author);

    Book findBook(long id);

    Book findBook(String name);

    boolean newBook(String bookName, long authorId);

    boolean updateBook(Book book);

    boolean deleteBook(Book book);
}
