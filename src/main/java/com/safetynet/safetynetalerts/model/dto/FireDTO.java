package com.safetynet.safetynetalerts.model.dto;

import java.util.Set;

public class FireDTO {
    /**
     * Station number.
     */
    private int stationNumber;
    /**
     * Set formatting as PersonHealthInfoDTO.
     */
    private Set<PersonHealthInfoDTO> personHealthInfoDTOSet;

    /**
     * Getter.
     * @return station numnber.
     */
    public int getStationNumber() {
        return stationNumber;
    }

    /**
     * Setter.
     * @param pStationNumber .
     */
    public void setStationNumber(final int pStationNumber) {
        stationNumber = pStationNumber;
    }

    /**
     * Getter.
     * @return Set.
     */
    public Set<PersonHealthInfoDTO> getPersonHealthInfoDTOSet() {
        return personHealthInfoDTOSet;
    }

    /**
     * Setter.
     * @param pPersonHealthInfoDTOSet .
     */
    public void setPersonHealthInfoDTOSet(
            final Set<PersonHealthInfoDTO> pPersonHealthInfoDTOSet) {
        personHealthInfoDTOSet = pPersonHealthInfoDTOSet;
    }
}
