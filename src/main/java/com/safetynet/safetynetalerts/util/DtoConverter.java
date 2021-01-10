package com.safetynet.safetynetalerts.util;

import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.model.dto.ChildInfoDTO;
import com.safetynet.safetynetalerts.model.dto.PersonNameDTO;

import java.util.Set;

public class DtoConverter {

    // TODO : test and Javadoc and Logger
    public static PersonNameDTO convertToPersonNameDTO (final Person person) {
        PersonNameDTO personNameDTO = new PersonNameDTO();
        personNameDTO.setFirstName(person.getFirstName());
        personNameDTO.setLastName(person.getLastName());
        return personNameDTO;
    }

    // TODO : test and Javadoc and Logger
    public static ChildInfoDTO convertToChildInfoDTO(final Person person,
                                                     final MedicalRecord medicalRecord,
                                                     final Set<PersonNameDTO> otherPersonsAtAddress) {
        ChildInfoDTO childInfoDTO = new ChildInfoDTO();
        childInfoDTO.setFirstName(person.getFirstName());
        childInfoDTO.setLastName(person.getLastName());
        childInfoDTO.setBirthdate(medicalRecord.getBirthdate());
        childInfoDTO.setOtherPersons(otherPersonsAtAddress);
        return childInfoDTO;
    }

}
