package it.xpeppers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;

import static java.util.Collections.singletonList;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(MockitoJUnitRunner.class)
public class ContactControllerTest {

    @Test
    public void
    returns_the_list_of_contacts() throws Exception {
        Contact contact = new Contact();
        contact.setFirstName(FIRST_NAME);
        contact.setLastName(LAST_NAME);
        contact.setNumber(TELEPHONE_NUMBER);
        when(repository.all()).thenReturn(singletonList(contact));

        mockMvc.perform(get("/contacts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].firstName", is(FIRST_NAME)))
                .andExpect(jsonPath("$[0].lastName", is(LAST_NAME)))
                .andExpect(jsonPath("$[0].number", is(TELEPHONE_NUMBER)));
    }


    public static final String FIRST_NAME = "A FIRST NAME";
    public static final String LAST_NAME = "A LAST NAME";
    public static final String TELEPHONE_NUMBER = "A TELEPHONE NUMBER";

    @InjectMocks
    ContactController controller;

    @Mock
    ContactRepository repository;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = standaloneSetup(controller)
                .build();
    }
}
