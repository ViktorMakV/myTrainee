package com.dao;

import com.model.Book;
import com.model.Storage;

/**
 * @author Viktor Makarov
 */
public interface StorageDAO {
    Storage getStorage(Book book);
}
