package com.safetynet.safetynetalerts.util;

import com.safetynet.safetynetalerts.model.FireStation;
import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.model.dto.*;

import java.time.LocalDate;
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

    //TODO : test, javadoc, loggers
    public static PersonHealthInfoDTO convertToPersonHealthInfoDTO(final MedicalRecord medicalRecord) {
        PersonHealthInfoDTO personHealthInfoDTO = new PersonHealthInfoDTO();
        personHealthInfoDTO.setFirstName(medicalRecord.getFirstName());
        personHealthInfoDTO.setLastName(medicalRecord.getLastName());
        personHealthInfoDTO.setAge(LocalDate.now().compareTo(medicalRecord.getBirthdate()));
        personHealthInfoDTO.setAllergies(medicalRecord.getAllergies());
        personHealthInfoDTO.setMedications(medicalRecord.getMedications());
        return personHealthInfoDTO;
    }

    //TODO : test, javaDoc et Logger
    public static FloodDTO convertToFloodDTO(final String address,
                                             final Set<PersonHealthInfoDTO> personsListByAddress) {
        FloodDTO floodDTO = new FloodDTO();
        floodDTO.setAddress(address);
        floodDTO.setPersonsListByAddress(personsListByAddress);
        return floodDTO;
    }

    public static FireAddressDTO convertToFireAddressDTO(final FireStation fireStation,
                                                         final Set<PersonHealthInfoDTO> personHealthInfoDTOSet) {
        FireAddressDTO fireAddressDTO = new FireAddressDTO();
        fireAddressDTO.setStationNumber(fireStation.getStation());
        fireAddressDTO.setPersonHealthInfoDTOSet(personHealthInfoDTOSet);
        return fireAddressDTO;
    }

    public static PersonFullInfoDTO convertToPersonFullInfoSTO(final Person person,
                                                               final MedicalRecord medicalRecord) {
        PersonFullInfoDTO personFullInfoDTO = new PersonFullInfoDTO();
        personFullInfoDTO.setFirstName(person.getFirstName());
        personFullInfoDTO.setLastName(person.getLastName());
        personFullInfoDTO.setAddress(person.getAddress());
        personFullInfoDTO.setEmail(person.getEmail());
        personFullInfoDTO.setAge(LocalDate.now().compareTo(medicalRecord.getBirthdate()));
        personFullInfoDTO.setMedications(medicalRecord.getMedications());
        personFullInfoDTO.setAllergies(medicalRecord.getAllergies());
        return personFullInfoDTO;
    }


}
