package it.xpeppers.repository;

import it.xpeppers.model.Contact;

import java.util.List;
import java.util.Optional;

public interface ContactRepository {

    List<Contact> all();

    Optional<Contact> withId(Integer id);

    Contact save(Contact contact);

    void delete(Contact contact);

}
