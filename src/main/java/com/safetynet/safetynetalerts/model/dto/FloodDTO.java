package com.safetynet.safetynetalerts.model.dto;

import java.util.Set;

public class FloodDTO {
    /**
     * Address.
     */
    private String address;
    /**
     * Set of persons.
     */
    private Set<PersonHealthInfoDTO> personsListByAddress;

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
     * @return set of person.
     */
    public Set<PersonHealthInfoDTO> getPersonsListByAddress() {
        return personsListByAddress;
    }

    /**
     * Setter.
     * @param pPersonsListByAddress .
     */
    public void setPersonsListByAddress(
            final Set<PersonHealthInfoDTO> pPersonsListByAddress) {
        personsListByAddress = pPersonsListByAddress;
    }
}
