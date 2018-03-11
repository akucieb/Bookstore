package com.akucieb.book;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

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

    @Test
    public void shouldReturnMapFiltratedByTitle() {
        //Given:
        Book book1 = new Book("Wiedźmin", new BookAuthor("Andrzej", "Sapkowski"));
        bookstoreService.books.put(book1, 2);
        Book book2 = new Book(15.05, "Ogniem i mieczem", new BookAuthor("Henryk", "Sienkiewicz"));
        bookstoreService.books.put(book2, 3);
        Book book3 = new Book(15, "W pustyni i w puszczy", new BookAuthor("Henryk", "Sienkiewicz"));
        bookstoreService.books.put(book3, 1);
        Book book4 = new Book(18, "Ogniem i mieczem", new BookAuthor("Henryk", "Sienkiewicz"));
        bookstoreService.books.put(book4, 8);
        String title = "Ogniem i mieczem";

        //When:
        Map result = bookstoreService.searchByTitle(title);

        //Then:
        Map<Book, Integer> expectedMap = new HashMap<>();
        expectedMap.put(book2, 3);
        expectedMap.put(book4, 8);
        Assert.assertEquals(expectedMap.size(), result.size());
        Assert.assertTrue(expectedMap.equals(result));

    }

    @Test
    public void shouldReturnEmptyMapWhenTitleDoesntExistInStock() {
        //Given:
        Book book1 = new Book("Wiedźmin", new BookAuthor("Andrzej", "Sapkowski"));
        bookstoreService.books.put(book1, 2);
        Book book2 = new Book(15.05, "Ogniem i mieczem", new BookAuthor("Henryk", "Sienkiewicz"));
        bookstoreService.books.put(book2, 3);
        Book book3 = new Book(15, "W pustyni i w puszczy", new BookAuthor("Henryk", "Sienkiewicz"));
        bookstoreService.books.put(book3, 1);
        Book book4 = new Book(18, "Ogniem i mieczem", new BookAuthor("Henryk", "Sienkiewicz"));
        bookstoreService.books.put(book4, 8);

        String title = "Lalka";

        //When:
        Map result = bookstoreService.searchByTitle(title);

        //Then:
        Assert.assertEquals(0, result.size());
    }

    @Test
    public void shouldReturnEmptyMapWhenTitleEqualsNull() {
        //Given:
        Book book1 = new Book("Wiedźmin", new BookAuthor("Andrzej", "Sapkowski"));
        bookstoreService.books.put(book1, 2);
        Book book2 = new Book(15.05, "Ogniem i mieczem", new BookAuthor("Henryk", "Sienkiewicz"));
        bookstoreService.books.put(book2, 3);
        Book book3 = new Book(15, "W pustyni i w puszczy", new BookAuthor("Henryk", "Sienkiewicz"));
        bookstoreService.books.put(book3, 1);
        Book book4 = new Book(18, "Ogniem i mieczem", new BookAuthor("Henryk", "Sienkiewicz"));
        bookstoreService.books.put(book4, 8);

        String title = null;

        //When:
        Map result = bookstoreService.searchByTitle(title);

        //Then:
        Assert.assertEquals(0, result.size());
    }

    @Test
    public void shouldReturnMapFiltratedByAuthor() {
        //Given:
        Book book1 = new Book("Wiedźmin", new BookAuthor("Andrzej", "Sapkowski"));
        bookstoreService.books.put(book1, 2);
        Book book2 = new Book(15.05, "Ogniem i mieczem", new BookAuthor("Henryk", "Sienkiewicz"));
        bookstoreService.books.put(book2, 3);
        Book book3 = new Book(15, "W pustyni i w puszczy", new BookAuthor("Henryk", "Sienkiewicz"));
        bookstoreService.books.put(book3, 1);
        Book book4 = new Book(18, "Ogniem i mieczem", new BookAuthor("Henryk", "Sienkiewicz"));
        bookstoreService.books.put(book4, 8);

        BookAuthor bookAuthor = new BookAuthor("Henryk", "Sienkiewicz");

        //When:
        Map result = bookstoreService.searchByAuthor(bookAuthor);

        //Then:
        Map<Book, Integer> expectedMap = new HashMap<>();
        expectedMap.put(book2, 3);
        expectedMap.put(book3, 1);
        expectedMap.put(book4, 8);
        Assert.assertEquals(expectedMap.size(), result.size());
        Assert.assertTrue(expectedMap.equals(result));

    }

    @Test
    public void shouldReturnEmptyMapWhenAuthorDoesntExistInStock() {
        //Given:
        Book book1 = new Book("Wiedźmin", new BookAuthor("Andrzej", "Sapkowski"));
        bookstoreService.books.put(book1, 2);
        Book book2 = new Book(15.05, "Ogniem i mieczem", new BookAuthor("Henryk", "Sienkiewicz"));
        bookstoreService.books.put(book2, 3);
        Book book3 = new Book(15, "W pustyni i w puszczy", new BookAuthor("Henryk", "Sienkiewicz"));
        bookstoreService.books.put(book3, 1);
        Book book4 = new Book(18, "Ogniem i mieczem", new BookAuthor("Henryk", "Sienkiewicz"));
        bookstoreService.books.put(book4, 8);

        BookAuthor bookAuthor = new BookAuthor("Boleslaw", "Prus");

        //When:
        Map result = bookstoreService.searchByAuthor(bookAuthor);

        //Then:
        Assert.assertEquals(0, result.size());
    }

    @Test
    public void shouldReturnEmptyMapWhenAuthorEqualsNull() {
        //Given:
        Book book1 = new Book("Wiedźmin", new BookAuthor("Andrzej", "Sapkowski"));
        bookstoreService.books.put(book1, 2);
        Book book2 = new Book(15.05, "Ogniem i mieczem", new BookAuthor("Henryk", "Sienkiewicz"));
        bookstoreService.books.put(book2, 3);
        Book book3 = new Book(15, "W pustyni i w puszczy", new BookAuthor("Henryk", "Sienkiewicz"));
        bookstoreService.books.put(book3, 1);
        Book book4 = new Book(18, "Ogniem i mieczem", new BookAuthor("Henryk", "Sienkiewicz"));
        bookstoreService.books.put(book4, 8);

        BookAuthor bookAuthor = null;

        //When:
        Map result = bookstoreService.searchByAuthor(bookAuthor);

        //Then:
        Assert.assertEquals(0, result.size());
    }
}
