package it.xpeppers.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.xpeppers.repository.ContactRepository;
import it.xpeppers.model.Contact;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.util.Optional;

import static java.util.Collections.singletonList;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(MockitoJUnitRunner.class)
public class ContactControllerTest {

    @Test
    public void
    returns_all_contacts() throws Exception {

        Contact aContact = aContact();
        when(repository.all()).thenReturn(singletonList(aContact));

        mockMvc.perform(get("/contacts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(aContact.getId())))
                .andExpect(jsonPath("$[0].firstName", is(aContact.getFirstName())))
                .andExpect(jsonPath("$[0].lastName", is(aContact.getLastName())))
                .andExpect(jsonPath("$[0].phoneNumber", is(aContact.getPhoneNumber())));
    }

    @Test
    public void
    returns_a_contact_with_a_given_id() throws Exception {

        Contact aContact = aContact();
        when(repository.withId(ID)).thenReturn(Optional.ofNullable(aContact));

        mockMvc.perform(get("/contacts/" + ID))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(ID)))
                .andExpect(jsonPath("$.firstName", is(aContact.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(aContact.getLastName())))
                .andExpect(jsonPath("$.phoneNumber", is(aContact.getPhoneNumber())));
    }

    @Test
    public void
    no_contact_found_for_ID() throws Exception {
        when(repository.withId(ID)).thenReturn(Optional.empty());

        mockMvc.perform(get("/contacts/" + ID))
                .andExpect(status().isNotFound());
    }

    @Test
    public void
    saves_a_valid_contact() throws Exception {

        Contact aNewContact = aNewContact();
        Contact savedContact = aContact();

        when(repository.save(aNewContact)).thenReturn(savedContact);

        mockMvc.perform(post("/contacts")
                .contentType(APPLICATION_JSON)
                .content(json(aNewContact)))
                .andExpect(header().string("Location", "/contacts/" + savedContact.getId()))
                .andExpect(status().isCreated());
    }

    @Test
    public void
    does_not_saves_an_invalid_contact() throws Exception {
        verify(repository, never()).save(any());

        Contact invalidContact = new Contact();
        mockMvc.perform(post("/contacts")
                .contentType(APPLICATION_JSON)
                .content(json(invalidContact)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void
    updates_a_valid_contact() throws Exception {
        Contact aContact = aContact();
        when(repository.withId(ID)).thenReturn(Optional.ofNullable(aContact));

        Contact update = aContact();
        update.setFirstName(ANOTHER_FIRST_NAME);

        mockMvc.perform(put("/contacts/" + ID)
                .contentType(APPLICATION_JSON)
                .content(json(update)))
                .andExpect(status().isNoContent());

        Contact updatedContact = anotherContactWithID();

        verify(repository).save(eq(updatedContact));
    }

    @Test
    public void
    cannot_update_non_existing_contact() throws Exception {
        when(repository.withId(ID)).thenReturn(Optional.empty());

        mockMvc.perform(put("/contacts/" + ID)
                .contentType(APPLICATION_JSON)
                .content(json(aContact())))
                .andExpect(status().isNotFound());
    }

    @Test
    public void
    removes_a_contact() throws Exception {
        Contact aContact = aContact();

        when(repository.withId(ID)).thenReturn(Optional.ofNullable(aContact));

        mockMvc.perform(delete("/contacts/" + ID))
                .andExpect(status().isNoContent());

        verify(repository).delete(eq(aContact));
    }

    @Test
    public void
    tries_to_removes_a_nonexisting_contact() throws Exception {

        when(repository.withId(ID)).thenReturn(Optional.empty());

        mockMvc.perform(delete("/contacts/" + ID))
                .andExpect(status().isNotFound());
    }


    private static final Integer ID = 1;

    private static final String FIRST_NAME = "A FIRST NAME";

    private static final String LAST_NAME = "A LAST NAME";

    private static final String TELEPHONE_NUMBER = "+39 02 123456";

    private static final String ANOTHER_FIRST_NAME = "ANOTHER FIRST NAME";
    @InjectMocks
    private ContactController controller;
    @Mock
    private ContactRepository repository;
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = standaloneSetup(controller)
                .build();
    }

    private String json(Object o) throws IOException {
        return new ObjectMapper().writeValueAsString(o);
    }

    private Contact aContact() {
        Contact contact = new Contact();
        contact.setId(ID);
        contact.setFirstName(FIRST_NAME);
        contact.setLastName(LAST_NAME);
        contact.setPhoneNumber(TELEPHONE_NUMBER);
        return contact;
    }

    private Contact anotherContactWithID() {
        Contact contact = new Contact();
        contact.setId(ID);
        contact.setFirstName(ANOTHER_FIRST_NAME);
        contact.setLastName(LAST_NAME);
        contact.setPhoneNumber(TELEPHONE_NUMBER);
        return contact;
    }

    private Contact aNewContact() {
        Contact contact = new Contact();
        contact.setFirstName(FIRST_NAME);
        contact.setLastName(LAST_NAME);
        contact.setPhoneNumber(TELEPHONE_NUMBER);
        return contact;
    }
}
