package com.safetynet.safetynetalerts.service;

import com.safetynet.safetynetalerts.datasource.DataBaseManager;
import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.repository.impl.MedicalRecordRepositoryImpl;
import com.safetynet.safetynetalerts.web.exceptions.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MedicalRecordServiceTest {

    private static MedicalRecordService medicalRecordService;
    private MedicalRecord medicalRecord;
    private Set<MedicalRecord> medicalRecords;

    @Mock
    private MedicalRecordRepositoryImpl medicalRecordRepositoryImpl;

    @BeforeEach
    void beforeEach() {
        medicalRecordService = new MedicalRecordService(medicalRecordRepositoryImpl);
        medicalRecord = new MedicalRecord("firstName2", "lastName2", LocalDate.of(1995, 1, 1), null, null);
        medicalRecords = DataBaseManager.INSTANCE.getDataBase().getMedicalRecords();
    }

    @Test
    void getMedicalRecordsTest() {
        when(medicalRecordRepositoryImpl.findAll()).thenReturn(medicalRecords);
        medicalRecordService.getAllMedicalRecords();
        verify(medicalRecordRepositoryImpl, times(1)).findAll();
    }

    @Test
    void saveMedicalRecordNewTest() {
        when(medicalRecordRepositoryImpl.save(medicalRecord)).thenReturn(true);
        assertTrue(medicalRecordService.saveMedicalRecord(medicalRecord));
        verify(medicalRecordRepositoryImpl, times(1)).save(medicalRecord);
    }

    @Test
    void saveMedicalRecordAlreadyExistingTest() {
        when(medicalRecordRepositoryImpl.save(medicalRecord)).thenReturn(false);
        assertFalse(medicalRecordService.saveMedicalRecord(medicalRecord));
        verify(medicalRecordRepositoryImpl, times(1)).save(medicalRecord);
    }


    @Test
    void updateMedicalRecordExistingTest() {
        when(medicalRecordRepositoryImpl.update(medicalRecord)).thenReturn(true);
        assertTrue(medicalRecordService.updateMedicalRecord(medicalRecord));
        verify(medicalRecordRepositoryImpl, times(1)).update(medicalRecord);
    }

    @Test
    void updateMedicalRecordUnknownTest() {
        when(medicalRecordRepositoryImpl.update(medicalRecord)).thenReturn(false);
        assertFalse(medicalRecordService.updateMedicalRecord(medicalRecord));
        verify(medicalRecordRepositoryImpl, times(1)).update(medicalRecord);
    }

    @Test
    void deleteMedicalRecordExistingTest() {
        when(medicalRecordRepositoryImpl.findByFirstNameAndLastName(medicalRecord.getFirstName(), medicalRecord.getLastName())).thenReturn(medicalRecord);
        when(medicalRecordRepositoryImpl.delete(medicalRecord)).thenReturn(true);
        assertTrue(medicalRecordService.deleteMedicalRecord(medicalRecord.getFirstName(), medicalRecord.getLastName()));
        verify(medicalRecordRepositoryImpl, times(1)).findByFirstNameAndLastName(medicalRecord.getFirstName(), medicalRecord.getLastName());
        verify(medicalRecordRepositoryImpl, times(1)).delete(medicalRecord);
    }

    @Test
    void deleteMedicalRecordUnknownTest() {
        when(medicalRecordRepositoryImpl.findByFirstNameAndLastName(medicalRecord.getFirstName(), medicalRecord.getLastName())).thenReturn(null);
        assertFalse(medicalRecordService.deleteMedicalRecord(medicalRecord.getFirstName(), medicalRecord.getLastName()));
        verify(medicalRecordRepositoryImpl, times(1)).findByFirstNameAndLastName(medicalRecord.getFirstName(), medicalRecord.getLastName());
        verify(medicalRecordRepositoryImpl, times(0)).delete(medicalRecord);
    }

    @Test
    void getMedicalRecordExistingTest() {
        Person person = new Person("test", "test", "address", "test" , 123, "test", "mail");
        when(medicalRecordRepositoryImpl.findByFirstNameAndLastName(person.getFirstName(), person.getLastName())).thenReturn(medicalRecord);
        assertNull(medicalRecordService.getMedicalRecord(person).getMedications());
        verify(medicalRecordRepositoryImpl, times(1)).findByFirstNameAndLastName(person.getFirstName(), person.getLastName());
    }

    @Test
    void getMedicalRecordUnknownTest() {
        Person person = new Person("test", "test", "address", "test" , 123, "test", "mail");
        when(medicalRecordRepositoryImpl.findByFirstNameAndLastName(person.getFirstName(), person.getLastName())).thenReturn(null);
        assertNull(medicalRecordService.getMedicalRecord(person));
        verify(medicalRecordRepositoryImpl, times(1)).findByFirstNameAndLastName(person.getFirstName(), person.getLastName());
    }

    @Test
    void getAgeExistingTest() {
        Person person = new Person("test", "test", "address", "test" , 123, "test", "mail");
        when(medicalRecordRepositoryImpl.findByFirstNameAndLastName(anyString(), anyString())).thenReturn(medicalRecord);
        int expectedAge = LocalDate.now().getYear() - 1995;
        assertEquals(expectedAge, medicalRecordService.getAge(person));
    }

    @Test
    void getAgeUnknownTest() {
        Person person = new Person("test", "test", "address", "test" , 123, "test", "mail");
        when(medicalRecordRepositoryImpl.findByFirstNameAndLastName(anyString(), anyString())).thenReturn(null);
        assertThrows(NotFoundException.class, () -> medicalRecordService.getAge(person));
    }

    @Test
    void getAllMedicalRecordByPersonListExistingTest() {
        Set<Person> personsAtAddress = new HashSet<>();
        personsAtAddress.add(new Person("test", "test", "address", "test" , 123, "test", "mail"));
        personsAtAddress.add(new Person("test1", "test", "address", "test" , 123, "test", "mail2"));
        MedicalRecord medicalRecord1 = new MedicalRecord("test", "test", LocalDate.of(1990, 1, 1),null, null);
        MedicalRecord medicalRecord2 = new MedicalRecord("test1", "test", LocalDate.of(2000, 1, 1),null, null);
        when(medicalRecordRepositoryImpl.findByFirstNameAndLastName(anyString(), anyString())).thenReturn(medicalRecord1).thenReturn(medicalRecord2);
        assertEquals(2, medicalRecordService.getAllMedicalRecordsByPersonList(personsAtAddress).size());
        verify(medicalRecordRepositoryImpl, times(2)).findByFirstNameAndLastName(anyString(), anyString());
    }

    @Test
    void getAllMedicalRecordByPersonListUnknownTest() {
        when(medicalRecordRepositoryImpl.findByFirstNameAndLastName(anyString(), anyString())).thenReturn(null);
        Set<Person> personsAtAddress = new HashSet<>();
        personsAtAddress.add(new Person("test", "test", "address", "test" , 123, "test", "mail"));
        assertEquals(0, medicalRecordService.getAllMedicalRecordsByPersonList(personsAtAddress).size());
        verify(medicalRecordRepositoryImpl, times(1)).findByFirstNameAndLastName(anyString(), anyString());
    }
}
