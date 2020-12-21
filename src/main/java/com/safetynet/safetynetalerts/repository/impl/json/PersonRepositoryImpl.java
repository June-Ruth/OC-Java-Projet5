package com.safetynet.safetynetalerts.repository.impl.json;

import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.repository.Dao;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Repository
public class PersonRepositoryImpl implements Dao<Person> {

    //TODO
    @Override
    public List<Person> findAll() {
        return null;
    }

    //TODO
    @Override
    public void save(Person person) {

    }

    //TODO
    @Override
    public void update(Person person) {

    }

    //TODO
    @Override
    public void delete(Person person) {

    }
}
