package com.safetynet.safetynetalerts.service;

import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.repository.impl.json.PersonRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

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
    public List<Person> getPersons() {
        return null;
    }

    /**
     * Save a new Person
     * @return //TODO
     */
    //TODO
    public Person savePerson(Person person) {
        return null;
    }

    /**
     * Update an existing person.
     * @param person - Person Object updated
     * @return //TODO
     */
    //TODO
    public Person updatePerson(Person person) {
        return null;
    }

    /**
     * Delete an existing person.
     * @param person to delete
     */
    //TODO
    public void deletePerson(Person person) {}


}
