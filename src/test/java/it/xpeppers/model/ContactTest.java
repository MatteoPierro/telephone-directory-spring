package it.xpeppers.model;

import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static it.xpeppers.model.ContactBuilder.aContact;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ContactTest {

    @Test
    public void
    fist_name_should_not_be_empty() {
        Contact contact = aContact()
                .withFirstName(AN_EMPTY_STRING)
                .build();

        Set<ConstraintViolation<Contact>> violations = validator.validate(contact);

        assertThat(violations, is(not(empty())));
    }

    @Test
    public void
    last_name_should_not_be_empty() {
        Contact contact = aContact()
                .withLastName(AN_EMPTY_STRING)
                .build();

        Set<ConstraintViolation<Contact>> violations = validator.validate(contact);

        assertThat(violations, is(not(empty())));
    }

    @Test
    public void
    phone_number_should_not_be_empty() {
        Contact contact = aContactWithPhoneNumber(AN_EMPTY_STRING);

        Set<ConstraintViolation<Contact>> violations = validator.validate(contact);

        assertThat(violations, is(not(empty())));
    }

    @Test
    public void
    phone_number_should_start_with_a_plus() {
        Contact contact = aContactWithPhoneNumber("39 329 1234555");

        Set<ConstraintViolation<Contact>> violations = validator.validate(contact);

        assertThat(violations, is(not(empty())));
    }

    @Test
    public void
    phone_number_should_have_an_international_prefix() {
        Contact contact = aContactWithPhoneNumber("+ 329 1234555");

        Set<ConstraintViolation<Contact>> violations = validator.validate(contact);

        assertThat(violations, is(not(empty())));
    }

    @Test
    public void
    phone_number_should_have_a_district_prefix() {
        Contact contact = aContactWithPhoneNumber("+39 1234555");

        Set<ConstraintViolation<Contact>> violations = validator.validate(contact);

        assertThat(violations, is(not(empty())));
    }

    @Test
    public void
    phone_number_should_have_a_customer_number_with_at_least_six_digits() {
        Contact contact = aContactWithPhoneNumber("+39 1234555");

        Set<ConstraintViolation<Contact>> violations = validator.validate(contact);

        assertThat(violations, is(not(empty())));
    }

    @Test
    public void
    a_valid_contact_should_not_have_constraints_violations() {
        Contact contact = aValidContact();

        Set<ConstraintViolation<Contact>> violations = validator.validate(contact);

        assertThat(violations, is(empty()));
    }

    private static final String AN_EMPTY_STRING = "";

    private Validator validator;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    private Contact aContactWithPhoneNumber(String phoneNumber) {
        return aContact()
                .withPhoneNumber(phoneNumber)
                .build();
    }

    private Contact aValidContact() {
        return aContact().build();
    }
}