package com.service;

import com.dao.BookDAO;
import com.dao.BookDAOImpl;
import com.model.Author;
import com.model.Book;

/**
 * @author Viktor Makarov
 */
public class BookServiceImpl implements BookService {
    private final static BookDAO BOOK_DAO = new BookDAOImpl(new AuthorServiceImpl());

    @Override
    public Book findBook(long id) {
        return BOOK_DAO.getById(id);
    }

    @Override
    public Book findBook(String name) {
        return BOOK_DAO.getByName(name);
    }

    @Override
    public boolean newBook(String bookName, long authorId) {
        return BOOK_DAO.addNew(bookName, authorId);
    }

    @Override
    public boolean updateBook(Book book) {
        return BOOK_DAO.update(book);
    }

    @Override
    public boolean deleteBook(Book book) {
        return BOOK_DAO.delete(book);
    }

    @Override
    public void fillAuthor(Author author) {
        BOOK_DAO.fillData(author);
    }
}