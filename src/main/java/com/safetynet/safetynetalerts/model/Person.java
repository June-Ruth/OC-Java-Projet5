package com.safetynet.safetynetalerts.model;

import com.fasterxml.jackson.annotation.JacksonInject;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Person {
    /**
     * Id of person concerned.
     */
    @JacksonInject
    @Id
    @GeneratedValue
    private int id;
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
    private long phone;
    /**
     * E-mail address.
     */
    private String email;
}
