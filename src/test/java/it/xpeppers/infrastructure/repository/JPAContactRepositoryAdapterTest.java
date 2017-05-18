package it.xpeppers.infrastructure.repository;

import it.xpeppers.model.Contact;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ComponentScan
@DataJpaTest
public class JPAContactRepositoryAdapterTest {

    @Autowired
    private JPAContactRepositoryAdapter repository;

    @Test
    public void
    save_a_contact() throws Exception {
        Contact contact = new Contact();
        contact.setFirstName("First Name");
        contact.setLastName("Last Name");
        contact.setPhoneNumber("+39 329 654321");

        repository.save(contact);
        List<Contact> contacts = repository.all();

        assertThat(contacts, hasSize(1));
    }
}