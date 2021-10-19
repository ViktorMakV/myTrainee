package com.service;

import com.dao.StorageDAO;
import com.dao.StorageDAOImpl;
import com.model.Book;
import com.model.Storage;

/**
 * @author Viktor Makarov
 */
public class StorageServiceImpl implements StorageService {
    private final static StorageDAO STORAGE_DAO = new StorageDAOImpl();

    @Override
    public Storage getStorage(Book book) {
        return STORAGE_DAO.getStorage(book);
    }
}
