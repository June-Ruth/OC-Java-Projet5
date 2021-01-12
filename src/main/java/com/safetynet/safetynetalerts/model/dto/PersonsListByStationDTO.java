package com.safetynet.safetynetalerts.model.dto;

import java.util.Set;

public class PersonsListByStationDTO {
    /**
     * Set of person.
     */
    private Set<PersonContactInfoDTO> personsList;
    /**
     * Countdown.
     */
    private CountdownDTO countdownDTO;

    /**
     * Getter.
     * @return set of persons.
     */
    public Set<PersonContactInfoDTO> getPersonsList() {
        return personsList;
    }

    /**
     * Setter.
     * @param pPersonsList .
     */
    public void setPersonsList(final Set<PersonContactInfoDTO> pPersonsList) {
        personsList = pPersonsList;
    }

    /**
     * Getter.
     * @return countdown.
     */
    public CountdownDTO getCountdownDTO() {
        return countdownDTO;
    }

    /**
     * Setter.
     * @param pCountdownDTO .
     */
    public void setCountdownDTO(final CountdownDTO pCountdownDTO) {
        countdownDTO = pCountdownDTO;
    }
}
