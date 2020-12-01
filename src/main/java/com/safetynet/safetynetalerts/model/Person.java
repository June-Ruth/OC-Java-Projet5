package com.safetynet.safetynetalerts.model;

import lombok.Data;

@Data
public class Person {
    public String firstName;
    public String lastName;
    public String adress;
    public String city;
    public int zip;
    public int phone;
    public String mail;
}
