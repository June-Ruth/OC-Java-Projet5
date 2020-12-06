package com.safetynet.safetynetalerts.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.safetynetalerts.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
    /* ex : find by name
    String url = "";
    ObjectMapper objectMapper = new ObjectMapper();

    /*
     * Test pour jouer
     * @param url
     * @return
     *
    static Person testGetOnePerson(final String url) {
        try {
            Person person = objectMapper.readValue(url, Person.class);
            return person;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }*/

}
