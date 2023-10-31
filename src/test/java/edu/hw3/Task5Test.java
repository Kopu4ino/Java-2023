package edu.hw3;

import edu.hw3.Task5.Contact;
import edu.hw3.Task5.ContactProvider;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class Task5Test {
    @Test
    void parseContactsCorrectInput() {
        String[] input1 = new String[] {"John Locke", "Thomas Aquinas", "David Hume", "Rene Descartes"};
        String order1 = "ASC";
        List<Contact> expect1 = List.of(
            new Contact("Thomas", "Aquinas"),
            new Contact("Rene", "Descartes"),
            new Contact("David", "Hume"),
            new Contact("John", "Locke")
        );

        assertThat(ContactProvider.parseContacts(input1, order1)).isEqualTo(expect1);

        String[] input2 = new String[] {"Paul Erdos", "Leonhard Euler", "Carl Gauss"};
        String order2 = "DESC";
        List<Contact> expect2 = List.of(
            new Contact("Paul", "Erdos"),
            new Contact("Leonhard", "Euler"),
            new Contact("Carl", "Gauss")
        );

        assertThat(ContactProvider.parseContacts(input2, order1)).isEqualTo(expect2);
    }

    @Test
    void parseContactsIncorrectInput() {
        String[] input = new String[] {"Paul Erdos", "Leonhard Euler", "Carl Gauss"};
        String IncorrectOrder = "ops";

        assertThatThrownBy(() -> ContactProvider.parseContacts(input, IncorrectOrder)).isInstanceOf(
            IllegalArgumentException.class);
    }

    @Test
    void parseContactsPersonWithoutSurName() {
        String[] input1 = new String[] {"a c", "a", "b"};
        String order1 = "DESC";
        List<Contact> expect1 = List.of(
            new Contact("a", "a"),
            new Contact("b", "b"),
            new Contact("a", "c")
        );
    }
}
