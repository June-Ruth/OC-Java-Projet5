package com.safetynet.safetynetalerts.repository.impl.json;

import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.repository.Dao;
import org.springframework.stereotype.Repository;

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
    public Person save(Person person) {
        return null;
    }

    //TODO
    @Override
    public boolean update(Person person) {
        return false;
    }

    //TODO
    @Override
    public boolean delete(Person person) {
        return false;
    }
}
