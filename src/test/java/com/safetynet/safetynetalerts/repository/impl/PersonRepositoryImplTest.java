package com.safetynet.safetynetalerts.repository.impl;

import com.safetynet.safetynetalerts.datasource.DataBase;
import com.safetynet.safetynetalerts.datasource.DataBaseManager;
import com.safetynet.safetynetalerts.datasource.DataBaseTestService;
import com.safetynet.safetynetalerts.model.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PersonRepositoryImplTest {

    private static DataBaseTestService dataBaseTestService = new DataBaseTestService();
    private static DataBase dataBase;

    private PersonRepositoryImpl personRepositoryImpl;

    @BeforeAll
    static void setUpBeforeAll() {
        dataBase =  DataBaseManager.INSTANCE.getDataBase();
    }

    @BeforeEach
    void setUpBeforeEach() {
        personRepositoryImpl = new PersonRepositoryImpl();
    }

    @AfterEach
    void tearDown() {
        dataBaseTestService.clearDBTest();
        dataBaseTestService.restoreDBTest();
    }

    @Test
    void findAllPersonsTest() {
        assertEquals(2, personRepositoryImpl.findAll().size());
    }

    @Test
    void findByFirstNameAndLastNameExistingTest() {
        Person person = new Person("firstName", "lastName", "address", "city", 123, "123-456-7890", "mail@email.com");
        Person toTest = personRepositoryImpl.findByFirstNameAndLastName(person.getFirstName(), person.getLastName());
        assertEquals(person.getAddress(), toTest.getAddress());
        assertEquals(person.getEmail(), toTest.getEmail());
        assertEquals(person.getPhone(), toTest.getPhone());
    }

    @Test
    void findByFirstNameAndLastNameUnknownTest() {
        Person person = new Person("test", "test", "test", "test", 123, "456", "test@test.com");
        Person toTest = personRepositoryImpl.findByFirstNameAndLastName(person.getFirstName(), person.getLastName());
        assertNull(toTest);
    }

    @Test
    void savePersonNewTest() {
        Person person = new Person("test", "test", "test", "test", 123, "456", "email");
        assertTrue(personRepositoryImpl.save(person));
        assertEquals(3, dataBase.getPersons().size());
    }

    @Test
    void savePersonAlreadyExistingTest() {
        Person person = new Person("firstName", "lastName", "address", "city", 123, "123-456-7890", "mail@email.com");
        assertFalse(personRepositoryImpl.save(person));
    }

    @Test
    void updatePersonWithExistingFirstNameAndLastNameTest() {
        String expected = "test@test.com";
        Person person = new Person("firstName", "lastName", "address", "city", 123, "123-456-7890", expected);
        assertTrue(personRepositoryImpl.update(person));
    }

    @Test
    void updatePersonWithUnknownFirstNameAndLastNameTest() {
        String expected = "test@test.com";
        Person person = new Person("test", "test", "test", "test", 123, "456", expected);
        assertFalse(personRepositoryImpl.update(person));
    }

    @Test
    void deletePersonWithExistingFirstNameAndLastNameTest() {
        Person person = new Person("firstName", "lastName", "address", "city", 123, "123-456-7890", "mail@email.com");
        assertTrue(personRepositoryImpl.delete(person));
        assertEquals(1, dataBase.getPersons().size());
    }

    @Test
    void deletePersonWithUnknownFirstNameAndLastNameTest() {
        Person person = new Person("test", "test", "test", "test", 123, "456", "email");
        assertFalse(personRepositoryImpl.delete(person));
    }

    @Test
    void deleteAllExistingTest() {
        Set<Person> personsToDelete = new HashSet<>();
        Person person = new Person("firstName", "lastName", "address", "city", 123, "123-456-7890", "mail@email.com");
        personsToDelete.add(person);
        assertTrue(personRepositoryImpl.deleteAll(personsToDelete));
        assertEquals(1, dataBase.getPersons().size());
    }

    @Test
    void deleteAllWithUnknownTest() {
        Set<Person> personsToDelete = new HashSet<>();
        Person person = new Person("test", "test", "test", "test", 123, "456", "email");
        personsToDelete.add(person);
        assertFalse(personRepositoryImpl.deleteAll(personsToDelete));
    }

}
