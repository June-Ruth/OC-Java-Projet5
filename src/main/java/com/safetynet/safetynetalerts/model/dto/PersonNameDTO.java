package com.safetynet.safetynetalerts.model.dto;


public class PersonNameDTO {
    /**
     * FirstName
     */
    private String firstName;
    /**
     * Last Name.
     */
    private String lastName;

    /**
     * Constructor.
     * @param pFirstName .
     * @param pLastName .
     */
    public PersonNameDTO(final String pFirstName,
                        final String pLastName) {
        firstName = pFirstName;
        lastName = pLastName;
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

}
