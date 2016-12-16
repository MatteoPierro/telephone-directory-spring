package it.xpeppers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    @Autowired
    ContactRepository repository;

    @RequestMapping(method = GET)
    public List<Contact> allContacts() {
        return repository.all();
    }

    @RequestMapping("/{id}")
    public Contact contactWith(@PathVariable("id") Integer id) {
        return repository.findBy(id);
    }

    @RequestMapping(method = POST)
    public ResponseEntity<?> saveContact(@RequestBody Contact contact) {
        Contact savedContact = repository.save(contact);
        return ResponseEntity
                .created(URI.create("/contacts/"+savedContact.getId()))
                .build();
    }

}
