package com.model;

import lombok.Data;

/**
 * @author Viktor Makarov
 */
@Data
public class Storage {
    private Book book;
    private int quantity;
    private double price;

    public Storage(Book book, int quantity, double price) {
        this.book = book;
        this.quantity = quantity;
        this.price = price;
    }
}
