package com.safetynet.safetynetalerts.repository;

import com.safetynet.safetynetalerts.model.Person;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends Dao<Person> {

    Person findByFirstNameAndLastName(String firstName, String lastName);
}
