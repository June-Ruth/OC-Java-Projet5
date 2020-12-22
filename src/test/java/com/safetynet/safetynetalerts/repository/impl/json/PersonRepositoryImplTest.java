package com.safetynet.safetynetalerts.repository.impl.json;

import com.safetynet.safetynetalerts.datasource.DataBase;
import com.safetynet.safetynetalerts.datasource.DataBaseManager;
import com.safetynet.safetynetalerts.datasource.DataBaseTestService;
import com.safetynet.safetynetalerts.model.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
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
    void savePersonNewTest() {
        Person person = new Person("test", "test", "test", "test", 123, "456", "email");
        personRepositoryImpl.save(person);
        assertEquals(3, dataBase.getPersons().size());
    }

    @Test
    void savePersonAlreadyExistingTest() {
        Person person = new Person("firstName", "lastName", "address", "city", 123, "123-456-7890", "mail@email.com");
        assertThrows(Exception.class, () -> personRepositoryImpl.save(person));
    }

    @Test
    void savePersonWithInvalidArgumentsTest() {
        Person person = new Person("firstName", "lastName", null, "city", 123, "123-456-7890", "mail@email.com");
        assertThrows(Exception.class, () -> personRepositoryImpl.save(person));
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
        assertThrows(Exception.class, () -> personRepositoryImpl.update(person));
    }

    @Test
    void updatePersonWithInvalidArgumentsTest() {
        String expected = "test@test.com";
        Person person = new Person("firstName", "lastName", null, "city", 123, "123-456-7890", expected);
        assertThrows(Exception.class, () -> personRepositoryImpl.update(person));
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
        assertThrows(Exception.class, () -> personRepositoryImpl.delete(person));
    }

}
