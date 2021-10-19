package com.dao;

import com.model.Book;
import com.model.Storage;
import com.service.ConnectionPool;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Viktor Makarov
 */
@Slf4j
public class StorageDAOImpl implements StorageDAO {

    @Override
    public Storage getStorage(Book book) {
        Storage storage = null;
        try (Connection connection = ConnectionPool.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM storage WHERE book_id = ?");
            ps.setLong(1, book.getId());
            ResultSet rs = ps.executeQuery();
            rs.next();
            int quantity = rs.getInt("quantity");
            double price = rs.getDouble("price");
            storage = new Storage(book, quantity, price);
        } catch (SQLException e) {
            log.debug("SQL exception while getting storage");
        } catch (NullPointerException e) {
            log.debug("Null pointer exception while getting storage");
        }
        return storage;
    }
}
