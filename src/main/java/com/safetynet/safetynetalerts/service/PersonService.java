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

    public Person getByFirstNameAndLastName(final String firstName, final String lastName) {
        LOGGER.debug("Process to find person " + firstName + " " + lastName);
        return personRepositoryImpl.findByFirstNameAndLastName(firstName, lastName);
    }

    /**
     * Get all Email of inhabitant in the city.
     * @param city searched
     * @return set of email in the city
     */
    public Set<String> getAllEmailInCity(final String city) {
        LOGGER.debug("Process to find all email in city " + city);
        return personRepositoryImpl.findAllEmailByCity(city);
    }

    //TODO : logger, test javadoc
    public Set<Person> getAllByAddress(final String address) {
        LOGGER.debug("Process to find all persons at address " + address);
        return personRepositoryImpl.findAllByAddress(address);
    }

    //TODO : logger, test javadoc
    public Set<String> findAllPhoneByAddress (final String address) {
        return personRepositoryImpl.findAllPhoneByAddress(address);
    }
}
