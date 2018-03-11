package com.akucieb.book;

import java.util.Objects;

public class BookAuthor {
    private String name;
    private String surname;

    public BookAuthor(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }

        BookAuthor bookAuthor = (BookAuthor) obj;
        return (name == bookAuthor.name || (name != null && name.equalsIgnoreCase(bookAuthor.name))) &&
                (surname == bookAuthor.surname || (surname != null && surname.equalsIgnoreCase(bookAuthor.surname)));
    }

    @Override
    public int hashCode() {
        return Objects.hash(name.toLowerCase(), surname.toLowerCase());
    }

    @Override
    public String toString() {
        return "Author: "
                + name
                + " " + surname;
    }
}
