package com.safetynet.safetynetalerts.repository.impl.json;

import com.safetynet.safetynetalerts.datasource.DataBase;
import com.safetynet.safetynetalerts.datasource.DataBaseManager;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.repository.Dao;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public class PersonRepositoryImpl implements Dao<Person> {

    private DataBase dataBase = DataBaseManager.INSTANCE.getDataBase();
    private Set<Person> persons = dataBase.getPersons();

    @Override
    public Set<Person> findAll() {
        return persons;
    }

    @Override
    public boolean save(Person person) {
        return persons.add(person);
    }

    @Override
    public boolean update(Person person) {
        if (persons.remove(person)) {
            return persons.add(person);
        } else {
            return false;
        }
    }

    @Override
    public boolean delete(Person person) {
        return persons.remove(person);
    }
}
