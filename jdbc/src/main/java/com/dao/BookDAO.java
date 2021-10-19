package com.dao;

import com.model.Author;
import com.model.Book;

/**
 * @author Viktor Makarov
 */
public interface BookDAO {
    Book getById(long id);

    Book getByName(String name);

    boolean update(Book book);

    boolean addNew(String bookName, long authorId);

    boolean delete(Book book);

    void fillData(Author author);
}
