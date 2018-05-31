package it.xpeppers.infrastructure.repository;

import it.xpeppers.model.Contact;
import it.xpeppers.model.ContactBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static it.xpeppers.model.ContactBuilder.aContact;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JPAContactRepositoryAdapterIT {

    @Test
    public void
    save_a_contact() {
        Contact contact = aContact().build();

        repositoryAdapter.save(contact);
        List<Contact> contacts = repositoryAdapter.all();

        assertThat(contacts, hasSize(1));
    }

    @Test
    public void
    delete_a_contact() {
        Contact contact = aContact().build();

        repositoryAdapter.save(contact);
        List<Contact> contacts = repositoryAdapter.all();
        assertThat(contacts, hasSize(1));

        repositoryAdapter.delete(contact);
        assertThat(repositoryAdapter.all(), is(empty()));
    }

    @Autowired
    private JPAContactRepositoryAdapter repositoryAdapter;

    @Autowired
    private JPAContactRepository repository;

    @Before
    public void setup() {
        repository.deleteAll();
    }
}