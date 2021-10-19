package com.dao;

import com.model.Author;
import com.service.ConnectionPool;
import com.service.BookService;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Viktor Makarov
 */
@Slf4j
public class AuthorDAOImpl implements AuthorDAO {
    private final BookService bookService;

    public AuthorDAOImpl(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public List<Author> getAllAuthors() {
        List<Author> authors = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM authors");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                long id = rs.getLong("id");
                String name = rs.getString("name");
                Author author = new Author(id, name);
                bookService.fillAuthor(author);
                authors.add(author);
            }
        } catch (SQLException e) {
            log.debug("SQL exception while getting all authors");
        } catch (NullPointerException e) {
            log.debug("Null pointer exception while getting all authors");
        }
        return authors;
    }

    @Override
    public Author getById(long id) {
        Author author = null;
        try (Connection connection = ConnectionPool.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM authors WHERE id = ?");
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            String name = rs.getString("name");
            author = new Author(id, name);
            bookService.fillAuthor(author);
        } catch (SQLException e) {
            log.debug("SQL exception while getting author by id");
        } catch (NullPointerException e) {
            log.debug("Null pointer exception while getting author by id");
        }
        return author;
    }

    @Override
    public Author getByName(String name) {
        Author author = null;
        try (Connection connection = ConnectionPool.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM authors WHERE name LIKE ?");
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            rs.next();
            long id = rs.getLong("id");
            author = new Author(id, name);
            bookService.fillAuthor(author);
        } catch (SQLException e) {
            log.debug("SQL exception while getting author by name");
        } catch (NullPointerException e) {
            log.debug("Null pointer exception while getting author by name");
        }
        return author;
    }

    @Override
    public boolean update(Author author) {
        try (Connection connection = ConnectionPool.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("UPDATE authors SET name = ? WHERE id = ?");
            ps.setString(1, author.getName());
            ps.setLong(2, author.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            log.debug("SQL exception while updating author");
            return false;
        } catch (NullPointerException e) {
            log.debug("Null pointer exception while updating author");
            return false;
        }
        return true;
    }

    @Override
    public boolean addNew(String authorName) {
        try (Connection connection = ConnectionPool.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO authors (name) VALUES (?)");
            ps.setString(1, authorName);
            ps.executeUpdate();
        } catch (SQLException e) {
            log.debug("SQL exception while adding new author");
            return false;
        } catch (NullPointerException e) {
            log.debug("Null pointer exception while adding new author");
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(Author author) {
        try (Connection connection = ConnectionPool.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM authors WHERE id = ?");
            ps.setLong(1, author.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            log.debug("SQL exception while deleting author");
            return false;
        } catch (NullPointerException e) {
            log.debug("Null pointer exception while deleting author");
            return false;
        }
        return true;
    }
}
