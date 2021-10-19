package com.service;

import com.dao.AuthorDAO;
import com.dao.AuthorDAOImpl;
import com.model.Author;

import java.util.List;

/**
 * @author Viktor Makarov
 */
public class AuthorServiceImpl implements AuthorService {
    private static final BookService SERVICE_BOOK = new BookServiceImpl();
    private static final AuthorDAO AUTHOR_DAO = new AuthorDAOImpl(SERVICE_BOOK);

    @Override
    public List<Author> getAllAuthors() {
        return AUTHOR_DAO.getAllAuthors();
    }

    @Override
    public Author findAuthor(long id) {
        return AUTHOR_DAO.getById(id);
    }

    @Override
    public Author findAuthor(String name) {
        return AUTHOR_DAO.getByName(name);
    }

    @Override
    public boolean newAuthor(String authorName) {
        return AUTHOR_DAO.addNew(authorName);
    }

    @Override
    public boolean updateAuthor(Author author) {
        return AUTHOR_DAO.update(author);
    }

    @Override
    public boolean deleteAuthor(Author author) {
        return AUTHOR_DAO.delete(author);
    }

    @Override
    public boolean addBookAndAuthor(String authorName, String bookName) {
        Author author = findAuthor(authorName);
        if (author == null) {
            newAuthor(authorName);
            author = findAuthor(authorName);
        }
        return SERVICE_BOOK.newBook(bookName, author.getId());
    }
}
