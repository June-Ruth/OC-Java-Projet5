package com.safetynet.safetynetalerts.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Person {
    /**
     * First name.
     */
    private String firstName;
    /**
     * Last name.
     */
    private String lastName;
    /**
     * Number and Street information.
     */
    private String address;
    /**
     * City information.
     */
    private String city;
    /**
     * Zip information.
     */
    private int zip;
    /**
     * Phone number.
     */
    private String phone;
    /**
     * E-mail address.
     */
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
        this.firstName = pFirstName;
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
        this.lastName = pLastName;
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
        this.address = pAdress;
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
        this.city = pCity;
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
        this.zip = pZip;
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
        this.phone = pPhone;
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
        this.email = pEmail;
    }

    @Override
    public boolean equals(Object o) {
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

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }
}
