package com.dao;

import com.exceprions.AuthorNotFoundException;
import com.model.Author;
import com.model.Book;
import com.service.AuthorService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

/**
 * @author Viktor Makarov
 */
@Slf4j
public class BookDAOImpl implements BookDAO {
    private static SessionFactory sessionFactory;
    private final AuthorService authorService;

    public BookDAOImpl(AuthorService authorService) {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        this.authorService = authorService;
    }

    @Override
    public Book getById(long id) {
        Book book;
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        book = session.get(Book.class, id);
        session.close();
        return book;
    }

    @Override
    public Book getByName(String name) {
        Book book;
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM Book WHERE name = :name");
        query.setParameter("name", name);

        book = (Book) query.uniqueResult();
        session.close();
        return book;
    }

    @Override
    public boolean update(Book book) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            String hql = "UPDATE Book SET name = :name, author = :author " +
                    "WHERE id = :id";
            Query query = session.createQuery(hql);
            query.setParameter("name", book.getName());
            query.setParameter("author", book.getAuthor());
            query.setParameter("id", book.getId());

            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            log.debug("Exception during book update");
            return false;
        }
        return true;
    }

    @Override
    public boolean addNew(String bookName, long authorId) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Author author = authorService.findAuthor(authorId);
            Book book = new Book();
            book.setAuthor(author);
            book.setName(bookName);
            if (author == null) {
                throw new AuthorNotFoundException();
            }
            session.save(book);

            transaction.commit();
        } catch (AuthorNotFoundException e) {
            log.debug("Author not found during adding new book.");
            return false;
        } catch (Exception e) {
            log.debug("Exception during adding new book.");
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(Book book) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(book);

            transaction.commit();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public void fillData(Author author) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String hql = "FROM Book WHERE author = :author";
        Query query = session.createQuery(hql);
        query.setParameter("author", author);
        List<Book> results = query.list();
        author.setBooks(results);

        session.close();
    }
}
