package edu.hw7.Task3;

import java.util.HashMap;
import java.util.Map;

public class SynchronizedPersonDataBase implements PersonDatabase {
    private final Map<Integer, Person> idPerson = new HashMap<>();
    private final Map<String, Person> namePerson = new HashMap<>();
    private final Map<String, Person> addressPerson = new HashMap<>();
    private final Map<String, Person> phonePerson = new HashMap<>();

    @Override
    public synchronized void add(Person person) {
        idPerson.put(person.id(), person);
        namePerson.put(person.name(), person);
        addressPerson.put(person.address(), person);
        phonePerson.put(person.phoneNumber(), person);
    }

    @Override
    public synchronized void delete(int id) {
        Person person = idPerson.get(id);

        if (person != null) {
            idPerson.remove(person.id());
            namePerson.remove(person.name());
            addressPerson.remove(person.address());
            phonePerson.remove(person.phoneNumber());
        }
    }

    @Override
    public Person findByName(String name) {
        return namePerson.get(name);
    }

    @Override
    public Person findByAddress(String address) {
        return addressPerson.get(address);
    }

    @Override
    public Person findByPhone(String phone) {
        return phonePerson.get(phone);
    }
}
