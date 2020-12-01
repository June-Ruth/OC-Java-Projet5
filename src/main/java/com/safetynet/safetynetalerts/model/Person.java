package com.safetynet.safetynetalerts.model;

import lombok.Data;

@Data
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
     * Complete address with fire station associated.
     * @see Address
     */
    private Address address;
    /**
     * Phone number.
     */
    private long phone;
    /**
     * E-mail address.
     */
    private String mail;
}
