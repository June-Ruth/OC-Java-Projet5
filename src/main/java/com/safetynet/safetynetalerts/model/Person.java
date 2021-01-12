package com.safetynet.safetynetalerts.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Person {
    /**
     * First name.
     */
    @NotBlank(message = "First name is mandatory")
    private String firstName;
    /**
     * Last name.
     */
    @NotBlank(message = "Last name is mandatory")
    private String lastName;
    /**
     * Number and Street information.
     */
    @NotBlank(message = "Address is mandatory")
    private String address;
    /**
     * City information.
     */
    @NotBlank(message = "City is mandatory")
    private String city;
    /**
     * Zip information.
     */
    @Positive(message = "Zip should be positive")
    private int zip;
    /**
     * Phone number.
     */
    @NotBlank(message = "Phone is mandatory")
    private String phone;
    /**
     * E-mail address.
     */
    @NotBlank(message = "Email is mandatory")
    private String email;

    /**
     * Constructor for one Person with JsonCreator for deserialization.
     * @param pFirstName of person concerned
     * @param pLastName of person concerned
     * @param pAddress of person concerned
     * @param pCity of person concerned
     * @param pZip of person concerned
     * @param pPhone of person concerned
     * @param pEmail of person concerned
     */
    @JsonCreator
    public Person(@JsonProperty("firstName") final String pFirstName,
                  @JsonProperty("lastName") final String pLastName,
                  @JsonProperty("address") final String pAddress,
                  @JsonProperty("city") final String pCity,
                  @JsonProperty("zip") final int pZip,
                  @JsonProperty("phone") final String pPhone,
                  @JsonProperty("email") final String pEmail) {
        firstName = pFirstName;
        lastName = pLastName;
        address = pAddress;
        city = pCity;
        zip = pZip;
        phone = pPhone;
        email = pEmail;
    }

    /**
     * Getter.
     * @return firstname
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Setter.
     * @param pFirstName .
     */
    public void setFirstName(final String pFirstName) {
        firstName = pFirstName;
    }

    /**
     * Getter.
     * @return lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Setter.
     * @param pLastName .
     */
    public void setLastName(final String pLastName) {
        lastName = pLastName;
    }

    /**
     * Getter.
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Setter.
     * @param pAdress .
     */
    public void setAddress(final String pAdress) {
        address = pAdress;
    }

    /**
     * Getter.
     * @return city
     */
    public String getCity() {
        return city;
    }

    /**
     * Setter.
     * @param pCity .
     */
    public void setCity(final String pCity) {
        city = pCity;
    }

    /**
     * Getter.
     * @return zip
     */
    public int getZip() {
        return zip;
    }

    /**
     * Setter.
     * @param pZip .
     */
    public void setZip(final int pZip) {
        zip = pZip;
    }

    /**
     * Getter.
     * @return phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Setter.
     * @param pPhone .
     */
    public void setPhone(final String pPhone) {
        phone = pPhone;
    }

    /**
     * Getter.
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter.
     * @param pEmail .
     */
    public void setEmail(final String pEmail) {
        email = pEmail;
    }

    /**
     * Equality depends on firstName and lastName.
     * @param o
     * @return true if it's equal
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Person person = (Person) o;
        return firstName.equals(person.firstName)
                && lastName.equals(person.lastName);
    }

    /**
     * Hash by firstName and lastName.
     * @return hash
     */
    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }
}
