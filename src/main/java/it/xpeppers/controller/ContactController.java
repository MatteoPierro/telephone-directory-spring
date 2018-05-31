package it.xpeppers.controller;

import it.xpeppers.repository.ContactRepository;
import it.xpeppers.model.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    @Autowired
    private ContactRepository repository;

    @RequestMapping(method = GET)
    public List<Contact> all() {
        return repository.all();
    }

    @RequestMapping(value = "/{id}", method = GET)
    public ResponseEntity<?> withId(@PathVariable("id") Integer id) {
        Optional<Contact> contact = repository.withId(id);

        if (contact.isPresent()) {
            return ResponseEntity.ok(contact.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(method = POST)
    public ResponseEntity<?> save(@Valid @RequestBody Contact contact) {
        Contact savedContact = repository.save(contact);
        return ResponseEntity
                .created(uriFor(savedContact))
                .build();
    }

    @RequestMapping(value = "/{id}", method = PUT)
    public ResponseEntity<?> update(@PathVariable("id") Integer id, @Valid @RequestBody Contact update) {
        Optional<Contact> contact = repository.withId(id);

        if (contact.isPresent()) {
            contact.get().update(update);

            repository.save(contact.get());
            return ResponseEntity
                    .noContent()
                    .build();
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @RequestMapping(value = "/{id}", method = DELETE)
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        Optional<Contact> contact = repository.withId(id);

        if (contact.isPresent()) {
            repository.delete(contact.get());

            return ResponseEntity
                    .noContent()
                    .build();
        } else {
            return ResponseEntity
                    .notFound()
                    .build();
        }


    }

    private URI uriFor(Contact savedContact) {
        return URI.create("/contacts/" + savedContact.getId());
    }

}
