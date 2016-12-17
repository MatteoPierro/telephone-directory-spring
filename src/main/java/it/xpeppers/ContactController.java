package it.xpeppers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    @Autowired
    ContactRepository repository;

    @RequestMapping(method = GET)
    public List<Contact> all() {
        return repository.all();
    }

    @RequestMapping(value = "/{id}", method = GET)
    public Contact withId(@PathVariable("id") Integer id) {
        return repository.withId(id);
    }

    @RequestMapping(method = POST)
    public ResponseEntity<?> save(@RequestBody Contact contact) {
        Contact savedContact = repository.save(contact);
        return ResponseEntity
                .created(uriFor(savedContact))
                .build();
    }

    @RequestMapping(value = "/{id}", method = PUT)
    public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody Contact contact) {
        contact.setId(id);
        repository.save(contact);
        return ResponseEntity
                .noContent()
                .build();
    }

    private URI uriFor(Contact savedContact) {
        return URI.create("/contacts/" + savedContact.getId());
    }

}
