package edu.hw7.Task3_5;

import edu.hw7.Task3.Person;
import edu.hw7.Task3.PersonDatabase;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockPersonDataBase implements PersonDatabase {
    private final Map<Integer, Person> idPerson = new HashMap<>();
    private final Map<String, Person> namePerson = new HashMap<>();
    private final Map<String, Person> addressPerson = new HashMap<>();
    private final Map<String, Person> phonePerson = new HashMap<>();
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    @Override
    public void add(Person person) {
        lock.writeLock().lock();
        try {
            idPerson.put(person.id(), person);
            namePerson.put(person.name(), person);
            addressPerson.put(person.address(), person);
            phonePerson.put(person.phoneNumber(), person);
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public void delete(int id) {
        lock.writeLock().lock();
        try {
            Person person = idPerson.get(id);
            if (person != null) {
                idPerson.remove(person.id());
                namePerson.remove(person.name());
                addressPerson.remove(person.address());
                phonePerson.remove(person.phoneNumber());
            }
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public Person findByName(String name) {
        lock.readLock().lock();
        try {
            return namePerson.get(name);
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public Person findByAddress(String address) {
        lock.readLock().lock();
        try {
            return addressPerson.get(address);
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public Person findByPhone(String phone) {
        lock.readLock().lock();
        try {
            return phonePerson.get(phone);
        } finally {
            lock.readLock().unlock();
        }
    }
}
