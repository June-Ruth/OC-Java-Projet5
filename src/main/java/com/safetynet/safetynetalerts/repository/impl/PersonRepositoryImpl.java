package com.safetynet.safetynetalerts.repository.impl;

import com.safetynet.safetynetalerts.datasource.DataBase;
import com.safetynet.safetynetalerts.datasource.DataBaseManager;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.repository.PersonRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

@Repository
public class PersonRepositoryImpl implements PersonRepository {
    /**
     * @see Logger
     */
    private static final Logger LOGGER = LogManager.getLogger(PersonRepositoryImpl.class);
    /**
     * @see DataBase
     */
    private DataBase dataBase = DataBaseManager.INSTANCE.getDataBase();
    /**
     * List of person presents in DataBase.
     */
    private Set<Person> persons = dataBase.getPersons();

    /**
     * Find all persons.
     * @return set of all persons.
     */
    @Override
    public Set<Person> findAll() {
        LOGGER.debug("Process to get all.");
        return persons;
    }

    /**
     * Find a person by its name.
     * @param firstName searched
     * @param lastName searched
     * @return person finded
     */
    @Override
    public Person findByFirstNameAndLastName(
            final String firstName, final String lastName) {
        Set<Person> result = new HashSet<>();
        persons.iterator().forEachRemaining((person) -> {
            if (person.getFirstName().equals(firstName)
                    && person.getLastName().equals(lastName)) {
                result.add(person);
            }
        });
        if (result.iterator().hasNext()) {
            LOGGER.debug("Person find for "
                    + firstName + lastName);
            return result.iterator().next();
        } else {
            LOGGER.debug("No person find for "
                    + firstName + lastName);
            return null;
        }
    }

    /**
     * Save a person.
     * Return false if the person already exists.
     * @param person to save full filled
     * @return true if it's correctly saved
     */
    @Override
    public boolean save(final Person person) {
        LOGGER.debug("Process to save person.");
        return persons.add(person);
    }

    /**
     * Update a person.
     * Return false if the person doesn't exist.
     * @param person to update full filled
     * @return true if it's correctly updated
     */
    @Override
    public boolean update(final Person person) {
        if (persons.remove(person)) {
            LOGGER.debug("Person to update exists."
                    + "Process to update.");
            return persons.add(person);
        } else {
            LOGGER.debug("Person to update doesn't exist.");
            return false;
        }
    }

    /**
     * Delete a person.
     * Return false if the person doesn't exist.
     * @param person to delete
     * @return true if it's correctly deleted
     */
    @Override
    public boolean delete(final Person person) {
        LOGGER.debug("Process to delete.");
        return persons.remove(person);
    }

    /**
     * Delete all the persons in a set.
     * Return false if one person doesn't exist.
     * @param personsToDelete set of persons to delete
     * @return true if it's correctly deleted
     */
    @Override
    public boolean deleteAll(final Set<Person> personsToDelete) {
        LOGGER.debug("Process to delete all the defined set.");
        return persons.removeAll(personsToDelete);
    }
}
