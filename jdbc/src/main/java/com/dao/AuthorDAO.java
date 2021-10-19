package com.dao;

import com.model.Author;

import java.util.List;

/**
 * @author Viktor Makarov
 */
public interface AuthorDAO {
    List<Author> getAllAuthors();

    Author getById(long id);

    Author getByName(String name);

    boolean update(Author author);

    boolean addNew(String authorName);

    boolean delete(Author author);
}
