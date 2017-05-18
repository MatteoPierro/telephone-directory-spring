package it.xpeppers.infrastructure.repository;

import it.xpeppers.model.Contact;
import org.springframework.data.repository.CrudRepository;

public interface JPAContactRepository extends CrudRepository<Contact, Integer> {
}
