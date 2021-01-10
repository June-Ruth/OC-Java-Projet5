package com.safetynet.safetynetalerts.model.dto;

import java.time.LocalDate;
import java.util.Set;

public class ChildInfoDTO {
    /**
     * FirstName
     */
    private String firstName;
    /**
     * Last Name.
     */
    private String lastName;
    /**
     * Birthdate.
     */
    private LocalDate birthdate;
    /**
    * Set of the other inhabitant at the same address.
    */
    private Set<PersonNameDTO> otherPersons;

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
     * @return birthdate
     */
    public LocalDate getBirthdate() {
        return birthdate;
    }

    /**
     * Setter.
     * @param pBirthdate .
     */
    public void setBirthdate(final LocalDate pBirthdate) {
        birthdate = pBirthdate;
    }

    /**
     * Getter.
     * @return set of the other inhabitant at the same address.
     */
    public Set<PersonNameDTO> getOtherPersons() {
        return otherPersons;
    }

    /**
     * Setter.
     * @param pOtherPersons at the same address
     */
    public void setOtherPersons(final Set<PersonNameDTO> pOtherPersons) {
        otherPersons = pOtherPersons;
    }


}
