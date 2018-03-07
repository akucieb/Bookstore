package com.akucieb.book;

import org.junit.BeforeClass;
import org.junit.Test;

public class BookAuthorEqualsTest {
    private static EqualsTester equalsTester;

    @BeforeClass
    public static void setUp() {
        BookAuthor bookAuthor = new BookAuthor("Jan", "Kowalski");
        BookAuthor bookAuthorEquals = new BookAuthor("Jan", "Kowalski");
        BookAuthor bookAuthorSecondEqual = new BookAuthor("Jan", "Kowalski");
        BookAuthor bookAuthorNotEqual = new BookAuthor("", "");
        equalsTester = new EqualsTester(bookAuthor, bookAuthorEquals, bookAuthorSecondEqual, bookAuthorNotEqual);
    }

    @Test
    public void testEquality() {
        equalsTester.verify();
    }
}
