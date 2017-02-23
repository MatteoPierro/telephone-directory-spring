package it.xpeppers;

import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ContactTest {

    private static final String A_VALID_FIRST_NAME = "A FIRST NAME";
    private static final String A_VALID_LAST_NAME = "A LAST NAME";
    private static final String A_VALID_NUMBER = "+39 329 123456";
    private static final String AN_EMPTY_STRING = "";

    private static Validator validator;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void
    fist_name_should_not_be_empty() throws Exception {
        Contact contact = new Contact();
        contact.setFirstName(AN_EMPTY_STRING);
        contact.setLastName(A_VALID_LAST_NAME);
        contact.setNumber(A_VALID_NUMBER);

        Set<ConstraintViolation<Contact>> violations = validator.validate(contact);

        assertThat(violations, is(not(empty())));
    }

    @Test
    public void
    last_name_should_not_be_empty() throws Exception {
        Contact contact = new Contact();
        contact.setFirstName(A_VALID_FIRST_NAME);
        contact.setLastName(AN_EMPTY_STRING);
        contact.setNumber(A_VALID_NUMBER);

        Set<ConstraintViolation<Contact>> violations = validator.validate(contact);

        assertThat(violations, is(not(empty())));
    }

    @Test
    public void
    telephone_should_not_be_empty() throws Exception {
        Contact contact = new Contact();
        contact.setFirstName(A_VALID_FIRST_NAME);
        contact.setLastName(A_VALID_LAST_NAME);
        contact.setNumber(AN_EMPTY_STRING);

        Set<ConstraintViolation<Contact>> violations = validator.validate(contact);

        assertThat(violations, is(not(empty())));
    }
}