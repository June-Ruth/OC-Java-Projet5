package com.safetynet.safetynetalerts.repository.impl;

import com.safetynet.safetynetalerts.datasource.DataBase;
import com.safetynet.safetynetalerts.datasource.DataBaseManager;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.repository.PersonRepository;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

@Repository
public class PersonRepositoryImpl implements PersonRepository {
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
            return result.iterator().next();
        } else {
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
            return persons.add(person);
        } else {
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
        return persons.removeAll(personsToDelete);
    }
}
