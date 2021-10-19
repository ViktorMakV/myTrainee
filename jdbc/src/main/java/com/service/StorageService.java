package com.service;

import com.model.Book;
import com.model.Storage;

/**
 * @author Viktor Makarov
 */
public interface StorageService {
    Storage getStorage(Book book);
}
