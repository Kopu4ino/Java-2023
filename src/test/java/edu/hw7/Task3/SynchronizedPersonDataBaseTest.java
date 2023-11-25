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
        db = new SynchronizedPersonDataBase();
        person1 = new Person(1, "Artur Kop", "Chkalova 123", "+77777777");
        person2 = new Person(2, "Bob Odenkirk", "Shevch 456", "+79999999");
    }

    @Test
    void testAddAndFindPerson() {
        db.add(person1);

        assertThat(db.findByName("Artur Kop")).isEqualTo(person1);
        assertThat(db.findByAddress("Chkalova 123")).isEqualTo(person1);
        assertThat(db.findByPhone("+7777777")).isEqualTo(person1);
    }

    @Test
    void testDeletePerson() {
        db.add(person1);
        db.delete(person1.id());

        assertThat(db.findByName("Artur Kop")).isNull();
        assertThat(db.findByAddress("Chkalova 123")).isNull();
        assertThat(db.findByPhone("+7777777")).isNull();
    }

    @Test
    void testFindNonExistingPerson() {
        assertThat(db.findByName("Noname")).isNull();
        assertThat(db.findByAddress("Noaddress")).isNull();
        assertThat(db.findByPhone("000-0000")).isNull();
    }

    @Test
    void testMultipleEntries() {
        db.add(person1);
        db.add(person2);

        assertThat(db.findByName("Artur Kop")).isEqualTo(person1);
        assertThat(db.findByName("Bob Odenkirk")).isEqualTo(person2);
    }
}
