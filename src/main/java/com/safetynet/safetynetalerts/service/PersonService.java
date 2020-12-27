package com.safetynet.safetynetalerts.service;

import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.repository.impl.json.PersonRepositoryImpl;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;

@Service
public class PersonService {

    private PersonRepositoryImpl personRepositoryImpl;

    PersonService(PersonRepositoryImpl pPersonRepositoryImpl) {
        Objects.requireNonNull(pPersonRepositoryImpl);
        personRepositoryImpl = pPersonRepositoryImpl;
    }

    /**
     * Get all entities for Person
     * @return list of all persons
     */
    public Set<Person> getPersons() {
        return personRepositoryImpl.findAll();
    }

    /**
     * Save a new Person
     */
    public boolean savePerson(Person person) {
        return personRepositoryImpl.save(person);
    }

    /**
     * Update an existing person.
     * @param person - Person Object updated
     */
    public boolean updatePerson(Person person) {
        return personRepositoryImpl.update(person);
    }

    /**
     * Delete an existing person.
     * @param firstName to delete
     * @param lastName to delete
     */
    public boolean deletePerson(String firstName, String lastName) {
        Person person = personRepositoryImpl.findByFirstNameAndLastName(firstName, lastName);
        if (person != null) {
            return personRepositoryImpl.delete(person);
        } else {
            return false;
        }
    }


}
