package edu.hw3.Task5;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ContactProvider {
    private ContactProvider() {
    }

    public static List<Contact> parseContacts(String[] persons, String order) {
        List<Contact> contacts = new ArrayList<>();

        for (String person : persons) {
            String[] parts = person.split(" ");
            String name = parts[0];
            String surName = parts.length > 1 ? parts[1] : name;

            contacts.add(new Contact(name, surName));
        }
        Comparator<Contact> comparator = Comparator.comparing(Contact::getSurName);

        if (order.equalsIgnoreCase("DESC")) {
            comparator.reversed();
        } else if (!order.equalsIgnoreCase("ASC")) {
            throw new IllegalArgumentException(order + " not in [ASC/DESC]");
        }
        contacts.sort(comparator);

        return contacts;
    }
}
