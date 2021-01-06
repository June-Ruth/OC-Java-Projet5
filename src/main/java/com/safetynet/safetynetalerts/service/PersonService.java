package com.safetynet.safetynetalerts.service;

import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.repository.impl.PersonRepositoryImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

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

    /**
     * constructor for personService.
     * Requires non null personRepositoryImpl.
     * @param pPersonRepositoryImpl not null
     */
    PersonService(final PersonRepositoryImpl pPersonRepositoryImpl) {
        Objects.requireNonNull(pPersonRepositoryImpl);
        personRepositoryImpl = pPersonRepositoryImpl;
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
        //TODO
        return null;
    }

    /**
     * Get all phone number of inhabitant associated to the given station number.
     * @param stationNumber concerned
     * @return set of all phone number.
     */
    public Set<String> getAllPhoneByStationNumber(final int stationNumber) {
        //TODO
        return null;
    }
}
