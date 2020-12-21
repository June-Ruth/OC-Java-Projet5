package com.safetynet.safetynetalerts.repository.impl.json;

import com.safetynet.safetynetalerts.datasource.DataBase;
import com.safetynet.safetynetalerts.datasource.DataBaseManager;
import com.safetynet.safetynetalerts.model.MedicalRecord;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class MedicalRecordImplTest {
    private static DataBase dataBase;
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

    @Test
    void findAllMedicalRecordsTest() {
        assertEquals(2, medicalRecordRepositoryImpl.findAll().size());
    }

    @Test
    void saveNewMedicalRecordTest() {
        LocalDate birthdate = LocalDate.now();
        MedicalRecord medicalRecord = new MedicalRecord("test", "test", birthdate, null, null);
        medicalRecordRepositoryImpl.save(medicalRecord);
        assertEquals(3, medicalRecords.size());
        medicalRecords.remove(2);
    }

    @Test
    void saveInvalidMedicalRecordTest() {
        //TODO ; voir en cas d'entrée inconrrecte
    }

    @Test
    void updateMedicalRecordWithExistingFirstNameAndLastNameTest() {
        //TODO : si update une personne existante dans la base, alors fonctionne
    }

    @Test
    void updateMedicalRecordWithUnknownFirstNameAndLastNameTest() {
        //TODO : si update une personne qui n'est pas dans la base de donnée : alors cheminement
    }

    @Test
    void deleteMedicalRecordWithExistingFirstNameAndLastNameTest() {
        //TODO : si delete une personne qui est dans la BDD, alors fonctionne
    }

    @Test
    void deleteMedicalRecordWithUnknownFirstNameAndLastNameTest() {
        //TODO : si delete avec une personne qui n'existe pas, alors cheminement
    }

}
