package it.xpeppers.repository;

import it.xpeppers.model.Contact;

import java.util.List;

public interface ContactRepository {

    List<Contact> all();

    Contact withId(Integer id);

    Contact save(Contact contact);

    void delete(Contact contact);

}
