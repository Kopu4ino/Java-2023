package edu.hw3.Task5;

import java.util.Objects;

public class Contact {
    private final String name;
    private final String surName;

    public Contact(String name, String surName) {
        this.name = name;
        this.surName = surName;
    }

    public String getSurName() {
        return this.surName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Contact contact = (Contact) o;
        return Objects.equals(name, contact.name) && Objects.equals(surName, contact.surName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surName);
    }
}
