package com.akucieb.book;

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
            System.out.println("[x] End program");
            System.out.println();
            usersChoice = getUserInput();
            if (usersChoice.equals("1")) {
                addBook();
            } else if (usersChoice.equals("2")) {
                removeBook();
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

    private static double nextDouble(Scanner sc) {
        double value = 0;
        if (sc.hasNextDouble()) {
            value = sc.nextDouble();
        }
        sc.nextLine();
        return value;
    }
}


