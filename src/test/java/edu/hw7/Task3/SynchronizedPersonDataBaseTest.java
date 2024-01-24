package edu.hw7.Task3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class SynchronizedPersonDataBaseTest {

    private SynchronizedPersonDataBase db;
    private Person person1;
    private Person person2;

    @BeforeEach
    void setUp() {
        //Arrange
        db = new SynchronizedPersonDataBase();
        person1 = new Person(1, "Artur Kop", "Chkalova 123", "+77777777");
        person2 = new Person(2, "Bob Odenkirk", "Shevch 456", "+79999999");
    }

    @Test
    void testAddAndFindPerson() {
        // Arrange
        db.add(person1);

        // Act & Assert
        assertThat(db.findByName("Artur Kop")).isEqualTo(person1);
        assertThat(db.findByAddress("Chkalova 123")).isEqualTo(person1);
        assertThat(db.findByPhone("+77777777")).isEqualTo(person1);
    }

    @Test
    void testDeletePerson() {
        // Arrange
        db.add(person1);

        // Act
        db.delete(person1.id());

        // Assert
        assertThat(db.findByName("Artur Kop")).isNull();
        assertThat(db.findByAddress("Chkalova 123")).isNull();
        assertThat(db.findByPhone("+77777777")).isNull();
    }

    @Test
    void testFindNonExistingPerson() {
        // Act & Assert
        assertThat(db.findByName("Noname")).isNull();
        assertThat(db.findByAddress("Noaddress")).isNull();
        assertThat(db.findByPhone("000-0000")).isNull();
    }

    @Test
    void testMultipleEntries() {
        // Arrange
        db.add(person1);
        db.add(person2);

        // Act & Assert
        assertThat(db.findByName("Artur Kop")).isEqualTo(person1);
        assertThat(db.findByName("Bob Odenkirk")).isEqualTo(person2);
    }
}
