package com.safetynet.safetynetalerts.service;

import com.safetynet.safetynetalerts.datasource.DataBaseManager;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.repository.impl.PersonRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

    private static PersonService personService;
    private Person person;
    private Set<Person> persons;

    @Mock
    private PersonRepositoryImpl personRepositoryImpl;

    @BeforeEach
    void beforeEach() {
        personService = new PersonService(personRepositoryImpl);
        person = new Person("firstName", "lastName", "address", "city", 123, "123-456-7890", "mail@email.com");
        persons = DataBaseManager.INSTANCE.getDataBase().getPersons();
    }

    @Test
    void getPersonsTest() {
        when(personRepositoryImpl.findAll()).thenReturn(persons);
        personService.getPersons();
        verify(personRepositoryImpl, times(1)).findAll();
    }

    @Test
    void savePersonNewTest() {
        when(personRepositoryImpl.save(person)).thenReturn(true);
        assertTrue(personService.savePerson(person));
        verify(personRepositoryImpl, times(1)).save(person);
    }

    @Test
    void savePersonAlreadyExistingTest() {
        when(personRepositoryImpl.save(person)).thenReturn(false);
        assertFalse(personService.savePerson(person));
        verify(personRepositoryImpl, times(1)).save(person);
    }

    @Test
    void updatePersonExistingTest() {
        when(personRepositoryImpl.update(person)).thenReturn(true);
        assertTrue(personService.updatePerson(person));
        verify(personRepositoryImpl, times(1)).update(person);
    }

    @Test
    void updatePersonUnknownTest() {
        when(personRepositoryImpl.update(person)).thenReturn(false);
        assertFalse(personService.updatePerson(person));
        verify(personRepositoryImpl, times(1)).update(person);
    }

    @Test
    void deletePersonExistingTest() {
        when(personRepositoryImpl.findByFirstNameAndLastName(person.getFirstName(), person.getLastName())).thenReturn(person);
        when(personRepositoryImpl.delete(person)).thenReturn(true);
        assertTrue(personService.deletePerson(person.getFirstName(), person.getLastName()));
        verify(personRepositoryImpl, times(1)).findByFirstNameAndLastName(person.getFirstName(), person.getLastName());
        verify(personRepositoryImpl, times(1)).delete(person);
    }

    @Test
    void deletePersonUnknownTest() {
        when(personRepositoryImpl.findByFirstNameAndLastName(person.getFirstName(), person.getLastName())).thenReturn(null);
        assertFalse(personService.deletePerson(person.getFirstName(), person.getLastName()));
        verify(personRepositoryImpl, times(1)).findByFirstNameAndLastName(person.getFirstName(), person.getLastName());
        verify(personRepositoryImpl, times(0)).delete(person);
    }

    @Test
    void getByFirstNameAndLastNameExistingTest() {
        when(personRepositoryImpl.findByFirstNameAndLastName(anyString(), anyString())).thenReturn(person);
        assertEquals(person, personService.getByFirstNameAndLastName(person.getFirstName(), person.getLastName()));
        verify(personRepositoryImpl, times(1)).findByFirstNameAndLastName(anyString(), anyString());
    }

    @Test
    void getByFirstNameAndLastNameUnknownTest() {
        when(personRepositoryImpl.findByFirstNameAndLastName(anyString(), anyString())).thenReturn(null);
        assertNull(personService.getByFirstNameAndLastName(person.getFirstName(), person.getLastName()));
        verify(personRepositoryImpl, times(1)).findByFirstNameAndLastName(anyString(), anyString());
    }

    @Test
    void getAllEmailInCityExistingTest() {
        Set<String> mailList = new HashSet<>();
        mailList.add(person.getEmail());
        when(personRepositoryImpl.findAllEmailByCity("city")).thenReturn(mailList);
        assertEquals(1, personService.getAllEmailInCity("city").size());
        verify(personRepositoryImpl, times(1)).findAllEmailByCity("city");
    }

    @Test
    void getAllEmailInCityUnknownTest() {
        when(personRepositoryImpl.findAllEmailByCity("city")).thenReturn(null);
        assertNull(personService.getAllEmailInCity("city"));
        verify(personRepositoryImpl, times(1)).findAllEmailByCity("city");
    }

    @Test
    void getAllByAddressExistingTest() {
        when(personRepositoryImpl.findAllByAddress("address")).thenReturn(persons);
        assertEquals(2, personService.getAllByAddress("address").size());
        verify(personRepositoryImpl, times(1)).findAllByAddress("address");
    }

    @Test
    void getAllByAddressUnknownTest() {
        when(personRepositoryImpl.findAllByAddress("address")).thenReturn(null);
        assertNull(personService.getAllByAddress("address"));
        verify(personRepositoryImpl, times(1)).findAllByAddress("address");
    }

    @Test
    void findAllPhoneByAddressExistingTest() {
        Set<String> phoneList = new HashSet<>();
        phoneList.add(person.getPhone());
        when(personRepositoryImpl.findAllPhoneByAddress("address")).thenReturn(phoneList);
        assertEquals(1, personService.findAllPhoneByAddress("address").size());
        verify(personRepositoryImpl, times(1)).findAllPhoneByAddress("address");
    }

    @Test
    void findAllPhoneByAddressUnknownTest() {
        when(personRepositoryImpl.findAllPhoneByAddress("address")).thenReturn(null);
        assertNull(personService.findAllPhoneByAddress("address"));
        verify(personRepositoryImpl, times(1)).findAllPhoneByAddress("address");
    }

}
