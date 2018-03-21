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
            System.out.println("\nChoose action and press enter:");
            System.out.println("[1] Add book");
            System.out.println("[2] Remove book");
            System.out.println("[3] Search book by title");
            System.out.println("[4] Search books by author");
            System.out.println("[5] Searching run out books");
            System.out.println("[x] End program\n");
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
        System.out.println("\n#### ADD NEW BOOK ###\n");
        System.out.println("Give title: ");
        String title = getUserInput();
        System.out.println("Give author name: ");
        String authorName = getUserInput();
        System.out.println("Give author surname: ");
        String authorSurname = getUserInput();

        double price;
        while (true) {
            System.out.println("Add book price? Y/N");
            String userInput = getUserInput();
            if (userInput.equalsIgnoreCase("y")) {
                System.out.println("Give book price: ");
                price = nextDouble(sc);
                break;
            } else if (userInput.equalsIgnoreCase("n")) {
                price = 0;
                break;
            } else {
                System.out.println("\nIncorrect input. Try one more time");
            }
        }

        BookAuthor author = new BookAuthor(authorName, authorSurname);
        Book book = new Book(price, title, author);

        bookstoreService.addBook(book);

        System.out.println("\nBook added to the library");
    }

    private static void removeBook() {
        System.out.println("\n#### REMOVE BOOK ####\n");
        System.out.println("Give title: ");
        String title = getUserInput();
        System.out.println("Give author name: ");
        String authorName = getUserInput();
        System.out.println("Give author surname: ");
        String authorSurname = getUserInput();
        System.out.println("Give book price");
        double price = nextDouble(sc);
        try {
            bookstoreService.removeBook(new Book(price, title, new BookAuthor(authorName, authorSurname)));
            System.out.println("\nBook has been removed");
        } catch (NullPointerException e) {
            System.out.println();
            System.out.println(e.getMessage());
        }
    }


    private static void searchByTitle() {
        System.out.println("\n#### SEARCH BOOK BY TITLE ####\n");
        System.out.println("Give title of the book you want to find: ");
        String title = getUserInput();

        Map<Book, Integer> searchingResult = bookstoreService.searchByTitle(title);

        System.out.println("\nThere are " + searchingResult.size() + " book in store with title: " + title + "\n");

        searchingResult.forEach((book, value) -> System.out.println(book + " number in stock: " + value));
    }

    private static void searchByAuthor() {
        System.out.println("\n#### SEARCH BOOKS BY AUTHOR ####\n");
        System.out.println("Give name of author: ");
        String name = getUserInput();
        System.out.println("Give surname of author: ");
        String surname = getUserInput();

        BookAuthor bookAuthor = new BookAuthor(name, surname);
        Map<Book, Integer> searchingResult = bookstoreService.searchByAuthor(bookAuthor);

        System.out.println("\nThere are " + searchingResult.size() + " book in store with author: " + bookAuthor + "\n");

        searchingResult.forEach((book, value) -> System.out.println(book + " number in stock: " + value));
    }

    private static void searchRunningOutBooks() {
        Map<Book, Integer> searchingResult = bookstoreService.searchingRunningOutBooks();

        System.out.println("\nThere are " + searchingResult.size() + " book in store with quantity less than 3: \n");

        searchingResult.forEach((book, value) -> System.out.println(book + " number in stock: " + value));
    }

    private static double nextDouble(Scanner sc) {
        double value = -1;
        while (value < 0) {
            try {
                value = sc.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("\nIllegal argument. Try one more time");
            } finally {
                sc.nextLine();
            }
        }
        return value;
    }
}


