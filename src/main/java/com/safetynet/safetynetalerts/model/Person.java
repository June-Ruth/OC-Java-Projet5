package com.safetynet.safetynetalerts.model;

import com.fasterxml.jackson.annotation.*;

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

    @JsonCreator
    public Person(@JsonProperty("firstName") String firstName,
                  @JsonProperty("lastName") String lastName,
                  @JsonProperty("address") String address,
                  @JsonProperty("city") String city,
                  @JsonProperty("zip") int zip,
                  @JsonProperty("phone") String phone,
                  @JsonProperty("email") String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.zip = zip;
        this.phone = phone;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
