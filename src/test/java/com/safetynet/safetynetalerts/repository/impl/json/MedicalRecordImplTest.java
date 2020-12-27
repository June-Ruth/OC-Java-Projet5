package com.safetynet.safetynetalerts.repository.impl.json;

import com.safetynet.safetynetalerts.datasource.DataBase;
import com.safetynet.safetynetalerts.datasource.DataBaseManager;
import com.safetynet.safetynetalerts.datasource.DataBaseTestService;
import com.safetynet.safetynetalerts.model.MedicalRecord;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MedicalRecordImplTest {

    private static DataBase dataBase;
    private static DataBaseTestService dataBaseTestService = new DataBaseTestService();
    private static Set<MedicalRecord> medicalRecords;

    private MedicalRecordRepositoryImpl medicalRecordRepositoryImpl;

    @BeforeAll
    static void setUpBeforeAll() {
        dataBase =  DataBaseManager.INSTANCE.getDataBase();
        medicalRecords = dataBase.getMedicalRecords();
    }

    @BeforeEach
    void setUpBeforeEach() {
        medicalRecordRepositoryImpl = new MedicalRecordRepositoryImpl();
    }

    @AfterEach
    void tearDown() {
        dataBaseTestService.clearDBTest();
        dataBaseTestService.restoreDBTest();
    }

    @Test
    void findAllMedicalRecordsTest() {
        assertEquals(2, medicalRecordRepositoryImpl.findAll().size());
    }

    @Test
    void findByFirstNameAndLastNameExistingTest() {
        LocalDate birthdate = LocalDate.of(2012, 6, 15);
        MedicalRecord medicalRecord = new MedicalRecord("firstName2", "lastName2", birthdate, null, null);
        MedicalRecord toTest = medicalRecordRepositoryImpl.findByFirstNameAndLastName(medicalRecord.getFirstName(), medicalRecord.getLastName());
        assertEquals(medicalRecord.getAllergies(), toTest.getAllergies());
        assertEquals(medicalRecord.getBirthdate(), toTest.getBirthdate());
        assertEquals(medicalRecord.getMedications(), toTest.getMedications());
    }

    @Test
    void findByFirstNameAndLastNameUnknownTest() {
        LocalDate birthdate = LocalDate.of(2012, 6, 15);
        MedicalRecord medicalRecord = new MedicalRecord("test", "test", birthdate, null, null);
        MedicalRecord toTest = medicalRecordRepositoryImpl.findByFirstNameAndLastName(medicalRecord.getFirstName(), medicalRecord.getLastName());
        assertNull(toTest);
    }

    @Test
    void saveMedicalRecordNewTest() {
        LocalDate birthdate = LocalDate.of(2010, 5, 12);
        MedicalRecord medicalRecord = new MedicalRecord("test", "test", birthdate, null, null);
        assertTrue(medicalRecordRepositoryImpl.save(medicalRecord));
        assertEquals(3, medicalRecords.size());
    }

    @Test
    void saveMedicalRecordAlreadyExisitingTest() {
        LocalDate birthdate = LocalDate.of(2012, 6, 15);
        MedicalRecord medicalRecord = new MedicalRecord("firstName2", "lastName2", birthdate, null, null);
        assertFalse(medicalRecordRepositoryImpl.save(medicalRecord));
    }

    @Test
    void updateMedicalRecordWithExistingFirstNameAndLastNameTest() {
        LocalDate newBirthdate = LocalDate.of(2012, 7, 15);
        MedicalRecord medicalRecord = new MedicalRecord("firstName2", "lastName2", newBirthdate, null, null);
        assertTrue(medicalRecordRepositoryImpl.update(medicalRecord));
    }

    @Test
    void updateMedicalRecordWithUnknownFirstNameAndLastNameTest() {
        LocalDate newBirthdate = LocalDate.of(2012, 7, 15);
        MedicalRecord medicalRecord = new MedicalRecord("test", "test", newBirthdate, null, null);
        assertFalse(medicalRecordRepositoryImpl.update(medicalRecord));
    }


    @Test
    void deleteMedicalRecordWithExistingFirstNameAndLastNameTest() {
        LocalDate birthdate = LocalDate.of(2012, 6, 15);
        MedicalRecord medicalRecord = new MedicalRecord("firstName2", "lastName2", birthdate, null, null);
        assertTrue(medicalRecordRepositoryImpl.delete(medicalRecord));
        assertEquals(1, dataBase.getMedicalRecords().size());
    }

    @Test
    void deleteMedicalRecordWithUnknownFirstNameAndLastNameTest() {
        LocalDate birthdate = LocalDate.of(2010, 5, 12);
        MedicalRecord medicalRecord = new MedicalRecord("test", "test", birthdate, null, null);
        assertFalse(medicalRecordRepositoryImpl.delete(medicalRecord));
    }

    @Test
    void deleteAllExistingTest() {
        Set<MedicalRecord> medicalRecordsToDelete = new HashSet<>();
        LocalDate birthdate = LocalDate.of(2012, 6, 15);
        MedicalRecord medicalRecord = new MedicalRecord("firstName2", "lastName2", birthdate, null, null);
        medicalRecordsToDelete.add(medicalRecord);
        assertTrue(medicalRecordRepositoryImpl.deleteAll(medicalRecordsToDelete));
        assertEquals(1, dataBase.getMedicalRecords().size());
    }

    @Test
    void deleteAllWithUnknownTest() {
        Set<MedicalRecord> medicalRecordsToDelete = new HashSet<>();
        LocalDate newBirthdate = LocalDate.of(2012, 7, 15);
        MedicalRecord medicalRecord = new MedicalRecord("test", "test", newBirthdate, null, null);
        medicalRecordsToDelete.add(medicalRecord);
        assertFalse(medicalRecordRepositoryImpl.deleteAll(medicalRecordsToDelete));
    }

}
