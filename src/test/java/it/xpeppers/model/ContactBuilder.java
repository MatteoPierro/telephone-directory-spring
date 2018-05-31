package it.xpeppers.model;

public class ContactBuilder {

    private static final String A_VALID_FIRST_NAME = "A FIRST NAME";
    private static final String A_VALID_LAST_NAME = "A LAST NAME";
    private static final String A_VALID_NUMBER = "+39 329 123456";

    private String firstName = A_VALID_FIRST_NAME;
    private String lastName = A_VALID_LAST_NAME;
    private String phoneNumber = A_VALID_NUMBER;

    public static ContactBuilder aContact() {
        return new ContactBuilder();
    }

    private ContactBuilder() {
    }

    public ContactBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public ContactBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public ContactBuilder withPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public Contact build() {
        Contact contact = new Contact();
        contact.setFirstName(firstName);
        contact.setLastName(lastName);
        contact.setPhoneNumber(phoneNumber);
        return contact;
    }
}
