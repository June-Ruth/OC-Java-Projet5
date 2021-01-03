package com.safetynet.safetynetalerts.repository;

import com.safetynet.safetynetalerts.model.Person;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends Dao<Person> {

    /**
     * Find a person by its name.
     * @param firstName searched
     * @param lastName searched
     * @return person searched
     */
    Person findByFirstNameAndLastName(String firstName, String lastName);
}
