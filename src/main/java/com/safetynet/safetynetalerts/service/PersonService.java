package com.safetynet.safetynetalerts.service;

import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.model.dto.ChildInfoDTO;
import com.safetynet.safetynetalerts.model.dto.PersonFullInfoDTO;
import com.safetynet.safetynetalerts.model.dto.PersonHealthInfoDTO;
import com.safetynet.safetynetalerts.model.dto.PersonNameDTO;
import com.safetynet.safetynetalerts.repository.impl.MedicalRecordRepositoryImpl;
import com.safetynet.safetynetalerts.repository.impl.PersonRepositoryImpl;
import com.safetynet.safetynetalerts.util.DtoConverter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

@Service
public class PersonService {
    /**
     * @see Logger
     */
    private static final Logger LOGGER = LogManager.getLogger(PersonService.class);
    /**
     * @see PersonRepositoryImpl
     */
    private PersonRepositoryImpl personRepositoryImpl;

    private MedicalRecordService medicalRecordService;

    /**
     * constructor for personService.
     * Requires non null personRepositoryImpl.
     * @param pPersonRepositoryImpl not null
     */
    PersonService(final PersonRepositoryImpl pPersonRepositoryImpl,
                  final MedicalRecordService pMedicalRecordService) {
        Objects.requireNonNull(pPersonRepositoryImpl);
        Objects.requireNonNull(pMedicalRecordService);
        personRepositoryImpl = pPersonRepositoryImpl;
        medicalRecordService = pMedicalRecordService;
    }

    /**
     * Get all entities for Person.
     * @return list of all persons
     */
    public Set<Person> getPersons() {
        LOGGER.debug("Process to get all persons.");
        return personRepositoryImpl.findAll();
    }

    /**
     * Save a new Person.
     * Return false if the person already exists
     * @param person to save full filled
     * @return true if it's correctly saved
     */
    public boolean savePerson(final Person person) {
        LOGGER.debug("Process to save person.");
        return personRepositoryImpl.save(person);
    }

    /**
     * Update an existing person.
     * Return false if the person doesn't exist.
     * @param person - Person Object updated
     * @return true is it's correctly updated
     */
    public boolean updatePerson(final Person person) {
        LOGGER.debug("Process to  update person.");
        return personRepositoryImpl.update(person);
    }

    /**
     * Delete an existing person.
     * Return false if the person doesn't exist.
     * @param firstName to delete
     * @param lastName to delete
     * @return true if it's correctly deleted
     */
    public boolean deletePerson(final String firstName, final String lastName) {
        LOGGER.debug("Process to find and delete person named "
                + firstName + lastName);
        Person person = personRepositoryImpl
                .findByFirstNameAndLastName(firstName, lastName);
        return personRepositoryImpl.delete(person);
    }

    /**
     * Get all Email of inhabitant in the city.
     * @param city searched
     * @return set of email in the city
     */
    public Set<String> getAllEmailInCity(final String city) {
        LOGGER.debug("Process to find all email in city " + city);
        Set<String> emailSet = personRepositoryImpl.findAllEmailByCity(city);
        if (emailSet == null) {
            LOGGER.debug("No Email found in the city " + city);
            return null;
        } else {
            LOGGER.debug("Email found in the city " + city);
            return emailSet;
        }
    }

    /**
     * Get all children at the specified address with other inhabitant.
     * @param address .
     * @return list of children
     */
    public Set<ChildInfoDTO> getAllChildrenByAddress(final String address) {
        Set<ChildInfoDTO> childrenAtAddress = new HashSet<>();
        Set<Person> personsAtAddress = personRepositoryImpl.findAllByAddress(address);
        if (personsAtAddress != null) {
            int limitChildAge = 18;
            personsAtAddress.iterator().forEachRemaining((person -> {
                MedicalRecord medicalRecord = medicalRecordService.getByFirstNameAndLastName(person.getFirstName(), person.getLastName());
                int age = LocalDate.now().compareTo(medicalRecord.getBirthdate());
                if (age <= limitChildAge) {
                    Set<Person> personsAtAddressNew = new HashSet<>(personsAtAddress);
                    personsAtAddressNew.remove(person);
                    Set<PersonNameDTO> otherPersonsAtAddress = new HashSet<>();
                    personsAtAddressNew.iterator().forEachRemaining(personAtAddress -> {
                        PersonNameDTO personNameDTO = DtoConverter.convertToPersonNameDTO(personAtAddress);
                        otherPersonsAtAddress.add(personNameDTO);
                    });
                    ChildInfoDTO childInfoDTO = DtoConverter.convertToChildInfoDTO(person, medicalRecord, otherPersonsAtAddress);
                    childrenAtAddress.add(childInfoDTO);
                }
            }));
            LOGGER.debug("Find children at the address " + address);
            return childrenAtAddress;
        } else {
            LOGGER.debug("No child found at the address " + address);
            return childrenAtAddress;
        }
    }

    /**
     * Get all information about a person.
     * @param firstName .
     * @param lastName .
     * @return person's info
     */
    //TODO : test, loggers
    public PersonFullInfoDTO getAllInfoByFirstNameAndLastName(final String firstName, final String lastName) {
        Person person = personRepositoryImpl.findByFirstNameAndLastName(firstName, lastName);
        MedicalRecord medicalRecord = medicalRecordService.getByFirstNameAndLastName(firstName, lastName);
        PersonFullInfoDTO personFullInfoDTO = DtoConverter.convertToPersonFullInfoSTO(person, medicalRecord);
        return personFullInfoDTO;
    }

    //TODO : logger, test javadoc
    public Set<String> findAllPhoneByAddress (final String address) {
        return personRepositoryImpl.findAllPhoneByAddress(address);
    }

    //TODO : logger, test javadoc
    public Set<Person> findAllByAddress(final String address) {
        return personRepositoryImpl.findAllByAddress(address);
    }

    //TODO : logger, test javadoc
    public Set<PersonHealthInfoDTO> getAllPersonHealthInfoByAddress(final String address) {
        Set<Person> personsListAtAddress = findAllByAddress(address);
        Set<PersonHealthInfoDTO> personHealthInfoDTOSet = new HashSet<>();
        personsListAtAddress.iterator().forEachRemaining(person -> {
            MedicalRecord medicalRecord = medicalRecordService.getByFirstNameAndLastName(person.getFirstName(), person.getLastName());
            PersonHealthInfoDTO personHealthInfoDTO = DtoConverter.convertToPersonHealthInfoDTO(medicalRecord);
            personHealthInfoDTOSet.add(personHealthInfoDTO);
        });
        return personHealthInfoDTOSet;
    }

}
