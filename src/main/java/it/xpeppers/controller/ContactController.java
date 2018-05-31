package it.xpeppers.controller;

import it.xpeppers.repository.ContactRepository;
import it.xpeppers.model.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.NOT_FOUND;
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
    public ResponseEntity<Contact> withId(@PathVariable("id") Integer id) {
        return repository.withId(id)
                .map(ResponseEntity::ok)
                .orElse(new ResponseEntity<>(NOT_FOUND));
    }

    @RequestMapping(method = POST)
    public ResponseEntity<?> save(@Valid @RequestBody Contact contact) {
        Contact savedContact = repository.save(contact);
        return ResponseEntity
                .created(uriFor(savedContact))
                .build();
    }

    @RequestMapping(value = "/{id}", method = PUT)
    public ResponseEntity<Void> update(@PathVariable("id") Integer id, @Valid @RequestBody Contact update) {
        return repository.withId(id)
                .map(contact -> update(contact, update))
                .orElse(new ResponseEntity<>(NOT_FOUND));
    }

    @RequestMapping(value = "/{id}", method = DELETE)
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        return repository.withId(id)
                .map(this::delete)
                .orElse(new ResponseEntity<>(NOT_FOUND));
    }

    private ResponseEntity<Void> update(Contact contact, Contact update) {
        contact.update(update);
        repository.save(contact);
        return ResponseEntity
                .noContent()
                .build();
    }

    private ResponseEntity<Void> delete(Contact contact) {
        repository.delete(contact);
        return ResponseEntity
                .noContent()
                .build();
    }

    private URI uriFor(Contact savedContact) {
        return URI.create("/contacts/" + savedContact.getId());
    }
}
