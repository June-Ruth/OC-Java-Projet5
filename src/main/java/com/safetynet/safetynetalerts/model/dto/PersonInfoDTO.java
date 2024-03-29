package com.safetynet.safetynetalerts.model.dto;

import java.util.List;

public class PersonInfoDTO {
    /**
     * FirstName.
     */
    private String firstName;
    /**
     * Last Name.
     */
    private String lastName;
    /**
     * Address.
     */
    private String address;
    /**
     * Email.
     */
    private String email;
    /**
     * Age.
     */
    private int age;
    /**
     * List of medications with value of dosage.
     * Medication is the key, dosage is the value in mg.
     */
    private List<String> medications;
    /**
     * List of known allergies.
     */
    private List<String> allergies;

    /**
     * Getter.
     * @return firstName
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
     * @return address.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Setter.
     * @param pAddress .
     */
    public void setAddress(final String pAddress) {
        address = pAddress;
    }

    /**
     * Getter.
     * @return email.
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
     * Getter.
     * @return age
     */
    public int getAge() {
        return age;
    }

    /**
     * Setter Age.
     * @param pAge .
     */
    public void setAge(final int pAge) {
        age = pAge;
    }

    /**
     * Getter Nullable.
     * @return medications as List.
     */
    public List<String> getMedications() {
        return medications;
    }

    /**
     * Setter Nullable.
     * @param pMedications as List.
     */
    public void setMedications(final List<String> pMedications) {
        medications = pMedications;
    }

    /**
     * Getter Nullable.
     * @return allergies as List.
     */
    public List<String> getAllergies() {
        return allergies;
    }

    /**
     * Setter Nullable.
     * @param pAllergies .
     */
    public void setAllergies(final List<String> pAllergies) {
        allergies = pAllergies;
    }
}
