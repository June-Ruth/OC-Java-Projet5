package com.safetynet.safetynetalerts.util;

import com.safetynet.safetynetalerts.constant.BusinessConstants;
import com.safetynet.safetynetalerts.model.FireStation;
import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.model.dto.ChildAlertDTO;
import com.safetynet.safetynetalerts.model.dto.CountdownDTO;
import com.safetynet.safetynetalerts.model.dto.FireDTO;
import com.safetynet.safetynetalerts.model.dto.FloodDTO;
import com.safetynet.safetynetalerts.model.dto.PersonContactInfoDTO;
import com.safetynet.safetynetalerts.model.dto.PersonHealthInfoDTO;
import com.safetynet.safetynetalerts.model.dto.PersonInfoDTO;
import com.safetynet.safetynetalerts.model.dto.PersonNameDTO;
import com.safetynet.safetynetalerts.model.dto.PersonsListByStationDTO;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public final class DtoConverter {
    /**
     * Constructor.
     */
    private DtoConverter() { }

    /**
     * Convert to Person Name DTO Set.
     * @param persons as List person to convert.
     * @return list converted.
     */
    public static Set<PersonNameDTO> convertToPersonNameDTOSet(
            final Set<Person> persons) {
        Set<PersonNameDTO> personNameDTOSet = new HashSet<>();
        persons.iterator().forEachRemaining(person -> {
            PersonNameDTO personNameDTO = new PersonNameDTO();
            personNameDTO.setFirstName(person.getFirstName());
            personNameDTO.setLastName(person.getLastName());
            personNameDTOSet.add(personNameDTO);
        });
        return personNameDTOSet;
    }

    /**
     * Convert to Child Info DTO.
     * @param person as child to convert.
     * @param age of the person.
     * @param otherPersonsAtAddress living with the child.
     * @return converted child.
     */
    public static ChildAlertDTO convertToChildInfoDTO(
            final Person person,
            final int age,
            final Set<PersonNameDTO> otherPersonsAtAddress) {
        ChildAlertDTO childAlertDTO = new ChildAlertDTO();
        childAlertDTO.setFirstName(person.getFirstName());
        childAlertDTO.setLastName(person.getLastName());
        childAlertDTO.setAge(age);
        childAlertDTO.setOtherPersons(otherPersonsAtAddress);
        return childAlertDTO;
    }

    /**
     * Convert to PersonHealthInfoDTO Set.
     * @param medicalRecordSet to convert.
     * @return converted persons set.
     */
    public static Set<PersonHealthInfoDTO> convertToPersonHealthInfoDTOSet(
            final Set<MedicalRecord> medicalRecordSet) {
        Set<PersonHealthInfoDTO> personHealthInfoDTOSet = new HashSet<>();
        medicalRecordSet.iterator().forEachRemaining(medicalRecord -> {
            PersonHealthInfoDTO personHealthInfoDTO = new PersonHealthInfoDTO();
            personHealthInfoDTO.setFirstName(medicalRecord.getFirstName());
            personHealthInfoDTO.setLastName(medicalRecord.getLastName());
            personHealthInfoDTO.setAge(
                    LocalDate.now().compareTo(medicalRecord.getBirthdate()));
            personHealthInfoDTO.setAllergies(medicalRecord.getAllergies());
            personHealthInfoDTO.setMedications(medicalRecord.getMedications());
            personHealthInfoDTOSet.add(personHealthInfoDTO);
        });
        return personHealthInfoDTOSet;
    }

    /**
     * Convert to Flood DTO.
     * @param address of the flood.
     * @param personsListByAddress at the address.
     * @return flood.
     */
    public static FloodDTO convertToFloodDTO(
            final String address,
            final Set<PersonHealthInfoDTO> personsListByAddress) {
        FloodDTO floodDTO = new FloodDTO();
        floodDTO.setAddress(address);
        floodDTO.setPersonsListByAddress(personsListByAddress);
        return floodDTO;
    }

    /**
     * Convert to Fire Address DTO.
     * @param fireStation .
     * @param personHealthInfoDTOSet .
     * @return fire DTO
     */
    public static FireDTO convertToFireAddressDTO(
            final FireStation fireStation,
            final Set<PersonHealthInfoDTO> personHealthInfoDTOSet) {
        FireDTO fireDTO = new FireDTO();
        fireDTO.setStationNumber(fireStation.getStation());
        fireDTO.setPersonHealthInfoDTOSet(personHealthInfoDTOSet);
        return fireDTO;
    }

    /**
     * Convert to PeronFull InfoDT0.
     * @param person to convert.
     * @param medicalRecord associated.
     * @return Person Info DTO
     */
    public static PersonInfoDTO convertToPersonFullInfoDTO(
            final Person person,
            final MedicalRecord medicalRecord) {
        PersonInfoDTO personInfoDTO = new PersonInfoDTO();
        personInfoDTO.setFirstName(person.getFirstName());
        personInfoDTO.setLastName(person.getLastName());
        personInfoDTO.setAddress(person.getAddress());
        personInfoDTO.setEmail(person.getEmail());
        personInfoDTO.setAge(
                LocalDate.now().compareTo(medicalRecord.getBirthdate()));
        personInfoDTO.setMedications(medicalRecord.getMedications());
        personInfoDTO.setAllergies(medicalRecord.getAllergies());
        return personInfoDTO;
    }

    /**
     * Convert to Person contact info dto set.
     * @param persons set to convert.
     * @return dto set.
     */
    public static Set<PersonContactInfoDTO> convertToPersonContactInfoDTOSet(
            final Set<Person> persons) {
        Set<PersonContactInfoDTO> personContactInfoDTOSet = new HashSet<>();
        persons.iterator().forEachRemaining(person -> {
            PersonContactInfoDTO personContactInfoDTO =
                    new PersonContactInfoDTO();
            personContactInfoDTO.setFirstName(person.getFirstName());
            personContactInfoDTO.setLastName(person.getLastName());
            personContactInfoDTO.setAddress(person.getAddress());
            personContactInfoDTO.setPhone(person.getPhone());
            personContactInfoDTO.setEmail(person.getEmail());
            personContactInfoDTOSet.add(personContactInfoDTO);
        });
        return personContactInfoDTOSet;
    }

    /**
     * Convert to CountDown.
     * @param medicalRecords list to convert.
     * @return countdown of adult and children in the list.
     */
    public static CountdownDTO convertToCountdownDTO(
            final Set<MedicalRecord> medicalRecords) {
        AtomicInteger adultCountdownInteger = new AtomicInteger(0);
        AtomicInteger childrenCountdownInteger = new AtomicInteger(0);
        medicalRecords.iterator().forEachRemaining(medicalRecord -> {
            if (LocalDate.now().compareTo(medicalRecord.getBirthdate())
                    <= BusinessConstants.LIMIT_CHILD_AGE) {
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

    /**
     * Convert to persons list by station DTO.
     * @param persons to convert.
     * @param countdownDTO associated.
     * @return persons set.
     */
    public static PersonsListByStationDTO convertToPersonsListByStationDTO(
            final Set<PersonContactInfoDTO> persons,
            final CountdownDTO countdownDTO) {
        PersonsListByStationDTO personsListByStationDTO =
                new PersonsListByStationDTO();
        personsListByStationDTO.setPersonsList(persons);
        personsListByStationDTO.setCountdownDTO(countdownDTO);
        return personsListByStationDTO;

    }

}
