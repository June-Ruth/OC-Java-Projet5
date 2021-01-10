package com.safetynet.safetynetalerts.model.dto;

import java.util.Set;

public class FloodDTO {

    private String address;

    private Set<PersonHealthInfoDTO> personsListByAddress;

    public String getAddress() {
        return address;
    }

    public void setAddress(final String pAddress) {
        address = pAddress;
    }

    public Set<PersonHealthInfoDTO> getPersonsListByAddress() {
        return personsListByAddress;
    }

    public void setPersonsListByAddress(final Set<PersonHealthInfoDTO> pPersonsListByAddress) {
        personsListByAddress = pPersonsListByAddress;
    }
}
