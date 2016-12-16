package it.xpeppers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    @Autowired
    ContactRepository repository;

    @RequestMapping
    public List<Contact> allContacts() {
        return repository.all();
    }

    @RequestMapping("/{id}")
    public Contact contactWith(@PathVariable("id") Integer id) {
        return repository.findBy(id);
    }

}
