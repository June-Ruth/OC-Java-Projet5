package com.safetynet.safetynetalerts.service;

import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.repository.impl.PersonRepositoryImpl;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;

@Service
public class PersonService {

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
        return personRepositoryImpl.findAll();
    }

    /**
     * Save a new Person.
     * Return false if the person already exists
     * @param person to save full filled
     * @return true if it's correctly saved
     */
    public boolean savePerson(final Person person) {
        return personRepositoryImpl.save(person);
    }

    /**
     * Update an existing person.
     * Return false if the person doesn't exist.
     * @param person - Person Object updated
     * @return true is it's correctly updated
     */
    public boolean updatePerson(final Person person) {
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
        Person person = personRepositoryImpl
                .findByFirstNameAndLastName(firstName, lastName);
        return personRepositoryImpl.delete(person);
    }


}
