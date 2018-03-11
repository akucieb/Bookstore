package com.akucieb.book;

import java.util.Map;
import java.util.Scanner;

public class Bookstore {

    private static Scanner sc = new Scanner(System.in);
    private static BookstoreService bookstoreService = new BookstoreService();

    public static void main(String[] args) {
        String usersChoice = null;
        while (!"x".equalsIgnoreCase(usersChoice)) {
            System.out.println();
            System.out.println("Choose action and press enter:");
            System.out.println("[1] Add book");
            System.out.println("[2] Remove book");
            System.out.println("[3] Search book by title");
            System.out.println("[4] Search books by author");
            System.out.println("[5] Searching run out books");
            System.out.println("[x] End program");
            System.out.println();
            usersChoice = getUserInput();
            if (usersChoice.equals("1")) {
                addBook();
            } else if (usersChoice.equals("2")) {
                removeBook();
            } else if (usersChoice.equals("3")) {
                searchByTitle();
            } else if (usersChoice.equals("4")) {
                searchByAuthor();
            } else if (usersChoice.equals("5")) {
                searchRunningOutBooks();
            }
        }
    }

    private static String getUserInput() {
        return sc.nextLine();
    }

    private static void addBook() {
        System.out.println();
        System.out.println("#### ADD NEW BOOK ###");
        System.out.println();
        System.out.print("Give title: ");
        String title = getUserInput();
        System.out.print("Give author name: ");
        String authorName = getUserInput();
        System.out.print("Give author surname: ");
        String authorSurname = getUserInput();
        System.out.println("Add book price? Y/N");

        double price;
        if (getUserInput().equalsIgnoreCase("y")) {
            System.out.print("Give book price: ");
            price = nextDouble(sc);
        } else price = 0;

        BookAuthor author = new BookAuthor(authorName, authorSurname);
        Book book = new Book(price, title, author);

        bookstoreService.addBook(book);

        System.out.println();
        System.out.println("Book added to the library");
    }

    private static void removeBook() {
        System.out.println();
        System.out.println("#### REMOVE BOOK ####");
        System.out.println();
        System.out.print("Give title: ");
        String title = getUserInput();
        System.out.print("Give author name: ");
        String authorName = getUserInput();
        System.out.print("Give author surname: ");
        String authorSurname = getUserInput();
        System.out.println("Give book price");
        double price = nextDouble(sc);

        bookstoreService.removeBook(new Book(price, title, new BookAuthor(authorName, authorSurname)));

        System.out.println();

        System.out.println("Book has been removed");
    }


    private static void searchByTitle() {
        System.out.println();
        System.out.println("#### SEARCH BOOK BY TITLE ####");
        System.out.println();
        System.out.print("Give title of the book you want to find: ");
        String title = getUserInput();
        System.out.println();

        Map<Book, Integer> searchingResult = bookstoreService.searchByTitle(title);

        System.out.println("There are " + searchingResult.size() + " book in store with title: " + title);

        searchingResult.forEach((book, value) -> System.out.println(book + " number in stock: " + value));
    }

    private static void searchByAuthor() {
        System.out.println();
        System.out.println("#### SEARCH BOOKS BY AUTHOR ####");
        System.out.println();
        System.out.print("Give name of author: ");
        String name = getUserInput();
        System.out.print("Give surname of author: ");
        String surname = getUserInput();
        System.out.println();

        BookAuthor bookAuthor = new BookAuthor(name, surname);
        Map<Book, Integer> searchingResult = bookstoreService.searchByAuthor(bookAuthor);

        System.out.println("There are " + searchingResult.size() + " book in store with author: " + bookAuthor);

        searchingResult.forEach((book, value) -> System.out.println(book + " number in stock: " + value));
    }

    private static void searchRunningOutBooks() {

        System.out.println();

        Map<Book, Integer> searchingResult = bookstoreService.searchingRunningOutBooks();

        System.out.println("There are " + searchingResult.size() + " book in store with quantity less than 3: ");

        searchingResult.forEach((book, value) -> System.out.println(book + " number in stock: " + value));
    }

    private static double nextDouble(Scanner sc) {
        double value = 0;
        if (sc.hasNextDouble()) {
            value = sc.nextDouble();
        }
        sc.nextLine();
        return value;
    }
}


