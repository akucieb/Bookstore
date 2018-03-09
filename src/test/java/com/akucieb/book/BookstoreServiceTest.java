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
    public void shouldIncreaseNumberOfBookInStockAfterAddingTheSameBook() {
        //Given:
        Book book = new Book("Wiedźmin", new BookAuthor("Andrzej", "Sapkowski"));
        bookstoreService.books.put(book, 4);

        //When:
        boolean result = bookstoreService.addBook(book);

        //Then:
        Assert.assertEquals(1, bookstoreService.getNumberOfBooks());
        int numberOfBookInStock = bookstoreService.books.get(book);
        Assert.assertEquals(5, numberOfBookInStock);
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

    @Test
    public void shouldRemoveBookWhichQuantityIsBiggerThanOne() {
        //Given:
        Book book = new Book("Wiedźmin", new BookAuthor("Andrzej", "Sapkowski"));
        bookstoreService.books.put(book, 3);

        //When:
        boolean result = bookstoreService.removeBook(book);

        //Then:
        Assert.assertTrue(result);
        Assert.assertEquals(1, bookstoreService.getNumberOfBooks());
        int numberOfBookInStore = bookstoreService.books.get(book);
        Assert.assertEquals(2, numberOfBookInStore);
    }

    @Test
    public void shouldRemoveBookWhichQuantityEqualsOne() {
        //Given:
        Book book = new Book("Wiedźmin", new BookAuthor("Andrzej", "Sapkowski"));
        bookstoreService.books.put(book, 1);

        //When:
        boolean result = bookstoreService.removeBook(book);

        //Then:
        Assert.assertTrue(result);
        Assert.assertEquals(0, bookstoreService.getNumberOfBooks());
    }

    @Test(expected = NullPointerException.class)
    public void shouldReturnExceptionWhenRemovingBookDoesNotExistInStore() {
        //Given:
        Book book = new Book("Wiedźmin", new BookAuthor("Andrzej", "Sapkowski"));
        //When:
        bookstoreService.removeBook(book);
    }
}
