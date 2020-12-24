package com.safetynet.safetynetalerts.service;

import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.repository.impl.json.PersonRepositoryImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@Disabled
@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

    private static PersonService personService;
    private Person person;

    @Mock
    private PersonRepositoryImpl personRepositoryImpl;

    @BeforeEach
    void beforeEach() {
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
        when(personRepositoryImpl.save(person)).thenReturn(false);
        verify(personRepositoryImpl, times(1)).save(person);
    }

    @Disabled
    @Test
    void savePersonWithInvalidArgumentsTest() {
        //TODO
    }

    @Test
    void updatePersonExistingTest() {
        when(personRepositoryImpl.update(person)).thenReturn(true);
        personService.savePerson(person);
        verify(personRepositoryImpl, times(1)).update(person);
    }

    @Test
    void updatePersonUnknownTest() {
        when(personRepositoryImpl.update(person)).thenReturn(false);
        verify(personRepositoryImpl, times(1)).update(person);
    }

    @Disabled
    @Test
    void updatePersonWithInvalidArgumentsTest() {
        //TODO
    }

    @Test
    void deletePersonExistingTest() {
        when(personRepositoryImpl.delete(person)).thenReturn(true);
        personService.deletePerson(person);
        verify(personRepositoryImpl, times(1)).delete(person);
    }

    @Test
    void deletePersonUnknownTest() {
        when(personRepositoryImpl.delete(person)).thenReturn(false);
        verify(personRepositoryImpl, times(1)).delete(person);
    }
}
