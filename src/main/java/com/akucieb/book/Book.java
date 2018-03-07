package com.akucieb.book;

import java.util.Objects;

public class Book {
    private double price;
    private String title;
    private BookAuthor bookAuthor;

    public Book(double price, String title, BookAuthor bookAuthor) {
        this.price = price;
        this.title = title;
        this.bookAuthor = bookAuthor;
    }

    public Book(String title, BookAuthor bookAuthor) {
        this.title = title;
        this.bookAuthor = bookAuthor;
    }

    public double getPrice() {
        return price;
    }

    public String getTitle() {
        return title;
    }

    public BookAuthor getBookAuthor() {
        return bookAuthor;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;

        Book book = (Book) obj;
        return (title == book.title || (title != null && title.equalsIgnoreCase(book.title))) &&
                (bookAuthor == book.bookAuthor || (bookAuthor != null && bookAuthor.equals(book.bookAuthor)));
    }

    @Override
    public int hashCode() {
        if (price == 0)
            return Objects.hash(title, bookAuthor);
        else
            return Objects.hash(title, bookAuthor, price);
    }

    @Override
    public String toString() {
        return "title: " + title +
                ", price: " + price +
                ", " + bookAuthor +
                ';';
    }
}
