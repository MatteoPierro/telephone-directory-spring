package it.xpeppers.infrastructure.repository;

import it.xpeppers.model.Contact;
import it.xpeppers.repository.ContactRepository;

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
        matteo.setPhoneNumber("+39 329 654321");

        Contact joe = new Contact();
        joe.setId(2);
        joe.setFirstName("Joe");
        joe.setLastName("Bew");
        joe.setPhoneNumber("+39 329 123456");

        CONTACTS.put(1, matteo);
        CONTACTS.put(2, joe);
    }

    @Override
    public List<Contact> all() {
        return new ArrayList<>(CONTACTS.values());
    }

    @Override
    public Contact withId(Integer id) {
        return CONTACTS.get(id);
    }

    @Override
    public synchronized Contact save(Contact contact) {
        Integer id = idFor(contact);

        contact.setId(id);
        CONTACTS.put(id, contact);

        return contact;
    }

    @Override
    public void delete(Contact contact) {
        CONTACTS.remove(contact.getId());
    }

    private Integer idFor(Contact contact) {
        Integer id = contact.getId();

        if(id == null) {
            id = CONTACTS.size() + 1;
        }

        return id;
    }
}
