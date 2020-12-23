package com.safetynet.safetynetalerts.service;

import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.repository.impl.json.PersonRepositoryImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class PersonServiceTest {

    private static PersonService personService;
    private Person person;

    @Mock
    private PersonRepositoryImpl personRepositoryImpl;

    @BeforeAll
    void beforeAll() {
        personService = new PersonService(personRepositoryImpl);
        person = new Person("firstName", "lastName", "address", "city", 123, "123-456-7890", "mail@email.com");
    }

    @Test
    void getPersonsTest() {
        doNothing().when(personRepositoryImpl.findAll());
        personService.getPersons();
        verify(personRepositoryImpl, times(1)).findAll();
    }

    @Test
    void savePersonNewTest() {
        when(personRepositoryImpl.save(person)).thenReturn(true);
        personService.savePerson(person);
        verify(personRepositoryImpl, times(1)).save(person);
    }

    @Test
    void savePersonAlreadyExistingTest() {
        when(personRepositoryImpl.save(person)).thenThrow(Exception.class);
        assertThrows(Exception.class, () -> personService.savePerson(person));
    }

    @Test
    void savePersonWithInvalidArgumentsTest() {
        when(personRepositoryImpl.save(person)).thenThrow(Exception.class);
        assertThrows(Exception.class, () -> personService.savePerson(person));
    }

    @Test
    void updatePersonExistingTest() {
        when(personRepositoryImpl.update(person)).thenReturn(true);
        personService.savePerson(person);
        verify(personRepositoryImpl, times(1)).update(person);
    }

    @Test
    void updatePersonUnknownTest() {
        when(personRepositoryImpl.update(person)).thenThrow(Exception.class);
        assertThrows(Exception.class, () -> personService.updatePerson(person));
    }

    @Test
    void updatePersonWithInvalidArgumentsTest() {
        when(personRepositoryImpl.update(person)).thenThrow(Exception.class);
        assertThrows(Exception.class, () -> personService.updatePerson(person));
    }

    @Test
    void deletePersonExistingTest() {
        when(personRepositoryImpl.delete(person)).thenReturn(true);
        personService.deletePerson(person);
        verify(personRepositoryImpl, times(1)).delete(person);
    }

    @Test
    void deletePersonUnknownTest() {
        when(personRepositoryImpl.delete(person)).thenThrow(Exception.class);
        assertThrows(Exception.class, () -> personService.deletePerson(person));
    }
}
