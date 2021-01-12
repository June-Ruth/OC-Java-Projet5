package com.safetynet.safetynetalerts.model.dto;

import java.util.Set;

public class FireDTO {

    private int stationNumber;

    private Set<PersonHealthInfoDTO> personHealthInfoDTOSet;

    public int getStationNumber() {
        return stationNumber;
    }

    public void setStationNumber(final int pStationNumber) {
        stationNumber = pStationNumber;
    }

    public Set<PersonHealthInfoDTO> getPersonHealthInfoDTOSet() {
        return personHealthInfoDTOSet;
    }

    public void setPersonHealthInfoDTOSet(final Set<PersonHealthInfoDTO> pPersonHealthInfoDTOSet) {
        personHealthInfoDTOSet = pPersonHealthInfoDTOSet;
    }
}
