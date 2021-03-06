package it.xpeppers.infrastructure.repository;

import com.google.common.collect.Lists;
import it.xpeppers.model.Contact;
import it.xpeppers.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JPAContactRepositoryAdapter implements ContactRepository {

    @Autowired
    private JPAContactRepository repository;

    @Override
    public List<Contact> all() {
        return Lists.newArrayList(repository.findAll());
    }

    @Override
    public Optional<Contact> withId(Integer id) {
        return Optional.ofNullable(repository.findOne(id));
    }

    @Override
    public Contact save(Contact contact) {
        return repository.save(contact);
    }

    @Override
    public void delete(Contact contact) {
        repository.delete(contact);
    }
}
