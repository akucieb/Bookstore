package com.akucieb.book;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BookstoreServiceTest {
    private BookstoreService bookstoreService;

    @Before
    public void init() {
        this.bookstoreService = new BookstoreService();
    }

    @Test
    public void shouldAddNewBook() {
        //Given:
        BookAuthor bookAuthor = new BookAuthor("Henryk", "Sienkiewicz");
        Book book = new Book(15.05, "Ogniem i mieczem", bookAuthor);

        //When:
        boolean result = bookstoreService.addBook(book);

        //Then:
        Assert.assertTrue(result);
        Assert.assertEquals(1, bookstoreService.getNumberOfBooks());
    }

    @Test
    public void shouldNotAddNullBook() {
        //Given:
        Book book = null;

        //When:
        boolean result = bookstoreService.addBook(book);

        //Then:
        Assert.assertFalse(result);
        Assert.assertEquals(0, bookstoreService.getNumberOfBooks());
    }
}
