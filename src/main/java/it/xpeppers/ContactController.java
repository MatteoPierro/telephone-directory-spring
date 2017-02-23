package it.xpeppers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ContactController {

    @Autowired
    ContactRepository repository;

    @RequestMapping("/contacts")
    public List<Contact> allContacts() {
        return repository.all();
    }

}
