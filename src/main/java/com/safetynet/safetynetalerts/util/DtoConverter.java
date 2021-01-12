package com.safetynet.safetynetalerts.util;

import com.safetynet.safetynetalerts.constant.BusinessConstants;
import com.safetynet.safetynetalerts.model.FireStation;
import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.model.dto.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class DtoConverter {

    // TODO : test and Javadoc and Logger
    public static Set<PersonNameDTO> convertToPersonNameDTOSet(final Set<Person> persons) {
        Set<PersonNameDTO> personNameDTOSet = new HashSet<>();
        persons.iterator().forEachRemaining(person -> {
            PersonNameDTO personNameDTO = new PersonNameDTO();
            personNameDTO.setFirstName(person.getFirstName());
            personNameDTO.setLastName(person.getLastName());
            personNameDTOSet.add(personNameDTO);
        });
        return personNameDTOSet;
    }

    // TODO : test and Javadoc and Logger
    public static ChildAlertDTO convertToChildInfoDTO(final Person person,
                                                      final int age,
                                                      final Set<PersonNameDTO> otherPersonsAtAddress) {
        ChildAlertDTO childAlertDTO = new ChildAlertDTO();
        childAlertDTO.setFirstName(person.getFirstName());
        childAlertDTO.setLastName(person.getLastName());
        childAlertDTO.setAge(age);
        childAlertDTO.setOtherPersons(otherPersonsAtAddress);
        return childAlertDTO;
    }

    //TODO : test, javadoc, loggers
    public static Set<PersonHealthInfoDTO> convertToPersonHealthInfoDTOSet(final Set<MedicalRecord> medicalRecordSet) {
        Set<PersonHealthInfoDTO> personHealthInfoDTOSet = new HashSet<>();
        medicalRecordSet.iterator().forEachRemaining(medicalRecord -> {
            PersonHealthInfoDTO personHealthInfoDTO = new PersonHealthInfoDTO();
            personHealthInfoDTO.setFirstName(medicalRecord.getFirstName());
            personHealthInfoDTO.setLastName(medicalRecord.getLastName());
            personHealthInfoDTO.setAge(LocalDate.now().compareTo(medicalRecord.getBirthdate()));
            personHealthInfoDTO.setAllergies(medicalRecord.getAllergies());
            personHealthInfoDTO.setMedications(medicalRecord.getMedications());
            personHealthInfoDTOSet.add(personHealthInfoDTO);
        });
        return personHealthInfoDTOSet;
    }

    //TODO : test, javaDoc et Logger
    public static FloodDTO convertToFloodDTO(final String address,
                                             final Set<PersonHealthInfoDTO> personsListByAddress) {
        FloodDTO floodDTO = new FloodDTO();
        floodDTO.setAddress(address);
        floodDTO.setPersonsListByAddress(personsListByAddress);
        return floodDTO;
    }

    public static FireDTO convertToFireAddressDTO(final FireStation fireStation,
                                                  final Set<PersonHealthInfoDTO> personHealthInfoDTOSet) {
        FireDTO fireDTO = new FireDTO();
        fireDTO.setStationNumber(fireStation.getStation());
        fireDTO.setPersonHealthInfoDTOSet(personHealthInfoDTOSet);
        return fireDTO;
    }

    public static PersonInfoDTO convertToPersonFullInfoSTO(final Person person,
                                                           final MedicalRecord medicalRecord) {
        PersonInfoDTO personInfoDTO = new PersonInfoDTO();
        personInfoDTO.setFirstName(person.getFirstName());
        personInfoDTO.setLastName(person.getLastName());
        personInfoDTO.setAddress(person.getAddress());
        personInfoDTO.setEmail(person.getEmail());
        personInfoDTO.setAge(LocalDate.now().compareTo(medicalRecord.getBirthdate()));
        personInfoDTO.setMedications(medicalRecord.getMedications());
        personInfoDTO.setAllergies(medicalRecord.getAllergies());
        return personInfoDTO;
    }

    public static Set<PersonContactInfoDTO> convertToPersonContactInfoDTOSet(final Set<Person> persons) {
        Set<PersonContactInfoDTO> personContactInfoDTOSet = new HashSet<>();
        persons.iterator().forEachRemaining(person -> {
            PersonContactInfoDTO personContactInfoDTO = new PersonContactInfoDTO();
            personContactInfoDTO.setFirstName(person.getFirstName());
            personContactInfoDTO.setLastName(person.getLastName());
            personContactInfoDTO.setAddress(person.getAddress());
            personContactInfoDTO.setPhone(person.getPhone());
            personContactInfoDTO.setEmail(person.getEmail());
            personContactInfoDTOSet.add(personContactInfoDTO);
        });
        return personContactInfoDTOSet;
    }

    public static CountdownDTO convertToCountdownDTO(Set<MedicalRecord> medicalRecords) {
        AtomicInteger adultCountdownInteger = new AtomicInteger(0);
        AtomicInteger childrenCountdownInteger = new AtomicInteger(0);
        medicalRecords.iterator().forEachRemaining(medicalRecord -> {
            if (LocalDate.now().compareTo(medicalRecord.getBirthdate()) <= BusinessConstants.LIMIT_CHILD_AGE) {
                childrenCountdownInteger.getAndIncrement();
            } else {
                adultCountdownInteger.getAndIncrement();
            }
        });
        CountdownDTO countdownDTO = new CountdownDTO();
        countdownDTO.setAdultCountdown(adultCountdownInteger.get());
        countdownDTO.setChildrenCountdown(childrenCountdownInteger.get());
        return countdownDTO;
    }

    public static PersonsListByStationDTO convertToPersonsListByStationDTO(final Set<PersonContactInfoDTO> persons,
                                                                           final CountdownDTO countdownDTO) {
        PersonsListByStationDTO personsListByStationDTO = new PersonsListByStationDTO();
        personsListByStationDTO.setPersonsList(persons);
        personsListByStationDTO.setCountdownDTO(countdownDTO);
        return personsListByStationDTO;

    }

}
