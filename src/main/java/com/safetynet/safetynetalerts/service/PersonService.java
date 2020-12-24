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
     * @return //TODO
     */
    //TODO
    public Set<Person> getPersons() {
        return personRepositoryImpl.findAll();
    }

    /**
     * Save a new Person
     * @return //TODO
     */
    //TODO
    public Person savePerson(Person person) {
        personRepositoryImpl.save(person);
        return person;
    }

    /**
     * Update an existing person.
     * @param person - Person Object updated
     * @return //TODO
     */
    //TODO
    public boolean updatePerson(Person person) {
        return personRepositoryImpl.update(person);
    }

    /**
     * Delete an existing person.
     * @param person to delete
     */
    //TODO
    public boolean deletePerson(Person person) {
        return personRepositoryImpl.delete(person);
    }


}
