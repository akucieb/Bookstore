package com.akucieb.book;

import java.util.InputMismatchException;
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
            switch (usersChoice) {
                case "1":
                    addBook();
                    break;
                case "2":
                    removeBook();
                    break;
                case "3":
                    searchByTitle();
                    break;
                case "4":
                    searchByAuthor();
                    break;
                case "5":
                    searchRunningOutBooks();
                    break;
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

        double price;
        while (true) {
            System.out.println("Add book price? Y/N");
            String userInput = getUserInput();
            if (userInput.equalsIgnoreCase("y")) {
                System.out.print("Give book price: ");
                price = nextDouble(sc);
                break;
            } else if (userInput.equalsIgnoreCase("n")) {
                price = 0;
                break;
            } else {
                System.out.println();
                System.out.println("Incorrect input. Try one more time");
            }
        }

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
        try {
            bookstoreService.removeBook(new Book(price, title, new BookAuthor(authorName, authorSurname)));
            System.out.println();
            System.out.println("Book has been removed");
        } catch (NullPointerException e) {
            System.out.println();
            System.out.println(e.getMessage());
        }
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
        while (value == 0) {
            try {
                value = sc.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println();
                System.out.println("Illegal argument. Try one more time");
            } finally {
                sc.nextLine();
            }
        }
        return value;
    }
}


