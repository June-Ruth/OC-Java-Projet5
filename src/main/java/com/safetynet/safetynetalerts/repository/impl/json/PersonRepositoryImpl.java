package com.safetynet.safetynetalerts.repository.impl.json;

import com.safetynet.safetynetalerts.datasource.DataBase;
import com.safetynet.safetynetalerts.datasource.DataBaseManager;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.repository.Dao;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PersonRepositoryImpl implements Dao<Person> {

    private DataBase dataBase = DataBaseManager.INSTANCE.getDataBase();
    private List<Person> persons = dataBase.getPersons();

    @Override
    public List<Person> findAll() {
        return persons;
    }

    @Override
    public boolean save(Person person) {
        if (persons.contains(person)) {
            return false;
        } else {
            persons.add(person);
            return true;
        }
    }

    @Override
    public boolean update(Person person) {
        int idToUpdate = persons.indexOf(person);
        if (idToUpdate != -1) {
            persons.set(idToUpdate, person);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean delete(Person person) {
        int idToDelete = persons.indexOf(person);
        if (idToDelete != -1) {
            persons.remove(idToDelete);
            return true;
        } else {
            return false;
        }
    }
}
