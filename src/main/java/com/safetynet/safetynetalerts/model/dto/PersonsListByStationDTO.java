package com.safetynet.safetynetalerts.model.dto;

import java.util.Set;

public class PersonsListByStationDTO {

    private Set<PersonContactInfoDTO> personsList;

    private CountdownDTO countdownDTO;

    public Set<PersonContactInfoDTO> getPersonsList() {
        return personsList;
    }

    public void setPersonsList(final Set<PersonContactInfoDTO> pPersonsList) {
        personsList = pPersonsList;
    }

    public CountdownDTO getCountdownDTO() {
        return countdownDTO;
    }

    public void setCountdownDTO(final CountdownDTO pCountdownDTO) {
        countdownDTO = pCountdownDTO;
    }
}
