package com.akucieb.book;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

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

    private boolean isBookAlreadyInStock(Book book) {
        return books.containsKey(book);
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

    public Map<Book, Integer> searchByTitle(String title) {

        return books.entrySet().stream().filter(map -> map.getKey().getTitle().equals(title))
                .collect(Collectors.toMap(b -> b.getKey(), b -> b.getValue()));
    }

    public Map<Book, Integer> searchByAuthor(BookAuthor bookAuthor) {

        return books.entrySet().stream().filter(map -> map.getKey().getBookAuthor()
                .equals(bookAuthor)).collect(Collectors.toMap(b -> b.getKey(), b -> b.getValue()));
    }

    public Map<Book, Integer> searchingRunningOutBooks() {
        return books.entrySet().stream().filter(map -> map.getValue() <= 3)
                .collect(Collectors.toMap(b -> b.getKey(), b -> b.getValue()));
    }

    public int getNumberOfBooks() {
        return books.size();
    }
}
