package com.akucieb.book;

import java.util.HashMap;
import java.util.Map;

public class BookstoreService {
    Map<Book, Integer> books;

    public BookstoreService() {
        this.books = new HashMap<>();
    }

    public boolean addBook(Book book) {
        boolean result = false;

        if (!isBookAlreadyInStock(book) && book != null) {
            books.put(book, 1);
            result = true;
        } else if (book != null) {
            int count = books.get(book);
            books.put(book, ++count);
            result = true;
        }
        return result;
    }

    public boolean removeBook(Book book) {
        Integer quantity = books.get(book);

        if (quantity == null) {
            throw new NullPointerException(String.format("Book does not exit"));
        } else if (quantity == 1) {
            books.remove(book);
        } else {
            books.put(book, --quantity);
        }
        return true;
    }

    private boolean isBookAlreadyInStock(Book book) {
        return books.containsKey(book);
    }

    public int getNumberOfBooks() {
        return books.size();
    }
}
