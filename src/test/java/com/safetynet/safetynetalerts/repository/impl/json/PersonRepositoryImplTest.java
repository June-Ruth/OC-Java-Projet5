package com.safetynet.safetynetalerts.repository.impl.json;

import com.safetynet.safetynetalerts.datasource.DataBase;
import com.safetynet.safetynetalerts.datasource.DataBaseManager;
import com.safetynet.safetynetalerts.model.Person;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class PersonRepositoryImplTest {


    private static DataBase dataBase;
    private static List<Person> persons;

    private PersonRepositoryImpl personRepositoryImpl;

    @BeforeAll
    static void setUpBeforeAll() {
        dataBase =  DataBaseManager.INSTANCE.getDataBase();
        persons = dataBase.getPersons();
    }

    @BeforeEach
    void setUpBeforeEach() {
        personRepositoryImpl = new PersonRepositoryImpl();
    }

    @Test
    void findAllPersonsTest() {
        assertEquals(2, personRepositoryImpl.findAll().size());
    }

    @Test
    void saveNewPersonTest() {
        Person person = new Person("test", "test", "test", "test", 123, "456", "email");
        personRepositoryImpl.save(person);
        assertEquals(3, persons.size());
        persons.remove(2);
    }

    @Test
    void saveInvalidPersonTest() {
        //TODO ; voir en cas d'entrée inconrrecte
    }

    @Test
    void updatePersonWithExistingFirstNameAndLastNameTest() {
        //TODO : si update une personne existante dans la base, alors fonctionne
    }

    @Test
    void updatePersonWithUnknownFirstNameAndLastNameTest() {
        //TODO : si update une personne qui n'est pas dans la base de donnée : alors cheminement
    }

    @Test
    void deletePersonWithExistingFirstNameAndLastNameTest() {
        //TODO : si delete une personne qui est dans la BDD, alors fonctionne
    }

    @Test
    void deletePersonWithUnknownFirstNameAndLastNameTest() {
        //TODO : si delete avec une personne qui n'existe pas, alors cheminement
    }

}
