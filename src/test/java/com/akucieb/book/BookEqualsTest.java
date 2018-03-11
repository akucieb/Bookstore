package com.akucieb.book;

import org.junit.BeforeClass;
import org.junit.Test;

public class BookEqualsTest {
    private static EqualsTester equalsTester;

    @BeforeClass
    public static void setUp() {
        BookAuthor bookAuthor = new BookAuthor("Jan", "Kowalski");
        String title = "Ogniem i mieczem";
        double price = 0;
        Book book = new Book(price, title, bookAuthor);
        Book bookEqual = new Book(title.toUpperCase(), bookAuthor);
        Book bookSecondEqual = new Book(price, title, bookAuthor);
        Book bookNotEqual = new Book(0, "", new BookAuthor("", ""));
        equalsTester = new EqualsTester(book, bookEqual, bookSecondEqual, bookNotEqual);
    }

    @Test
    public void bookEqualsTest() {
        equalsTester.verify();
    }
}
