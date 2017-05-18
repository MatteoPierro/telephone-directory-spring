package it.xpeppers.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;

public class Contact {

    private Integer id;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @Pattern(regexp = "\\+[0-9]+\\s[0-9]+\\s[0-9]{6,}")
    private String phoneNumber;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    public void update(Contact update) {
        this.firstName = update.firstName;
        this.lastName = update.lastName;
        this.phoneNumber = update.phoneNumber;
    }
}
