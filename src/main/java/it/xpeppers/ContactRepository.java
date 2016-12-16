package it.xpeppers;

import java.util.List;

public interface ContactRepository {

    List<Contact> all();

    Contact withId(Integer id);

    Contact save(Contact contact);
}
