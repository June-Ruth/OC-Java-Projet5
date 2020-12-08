package com.safetynet.safetynetalerts.repository;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.safetynetalerts.model.Person;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Repository
public interface PersonRepository {

    /*static List<Person> convertJsonToJava(File file) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        List<Person> persons = objectMapper.readValue(file,
                objectMapper.getTypeFactory().constructCollectionType(List.class, Person.class));

        return persons;
    }*/
}
