package it.xpeppers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryContactRepository implements ContactRepository {

    private final static Map<Integer, Contact> CONTACTS = new ConcurrentHashMap<>();

    static {
        Contact matteo = new Contact();
        matteo.setId(1);
        matteo.setFirstName("Matteo");
        matteo.setLastName("Pierro");
        matteo.setNumber("+39 329 654321");

        Contact joe = new Contact();
        joe.setId(2);
        joe.setFirstName("Joe");
        joe.setLastName("Bew");
        joe.setNumber("+39 329 123456");

        CONTACTS.put(1, matteo);
        CONTACTS.put(2, joe);
    }

    @Override
    public List<Contact> all() {
        return new ArrayList<>(CONTACTS.values());
    }

    @Override
    public Contact findBy(Integer id) {
        return CONTACTS.get(id);
    }
}
