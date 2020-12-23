package com.safetynet.safetynetalerts.repository.impl.json;

import com.safetynet.safetynetalerts.datasource.DataBase;
import com.safetynet.safetynetalerts.datasource.DataBaseManager;
import com.safetynet.safetynetalerts.datasource.DataBaseTestService;
import com.safetynet.safetynetalerts.model.MedicalRecord;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MedicalRecordImplTest {

    private static DataBase dataBase;
    private static DataBaseTestService dataBaseTestService = new DataBaseTestService();
    private static List<MedicalRecord> medicalRecords;

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
    void saveMedicalRecordNewTest() {
        LocalDate birthdate = LocalDate.of(2010, 5, 12);
        MedicalRecord medicalRecord = new MedicalRecord("test", "test", birthdate, null, null);
        medicalRecordRepositoryImpl.save(medicalRecord);
        assertEquals(3, medicalRecords.size());
    }

    @Test
    void saveMedicalRecordAlreadyExisitingTest() {
        LocalDate birthdate = LocalDate.of(2012, 6, 15);
        MedicalRecord medicalRecord = new MedicalRecord("firstName2", "lastName2", birthdate, null, null);
        assertThrows(Exception.class, () -> medicalRecordRepositoryImpl.save(medicalRecord));
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
        assertThrows(Exception.class, () -> medicalRecordRepositoryImpl.update(medicalRecord));
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
        assertThrows(Exception.class, () -> medicalRecordRepositoryImpl.delete(medicalRecord));
    }

}
