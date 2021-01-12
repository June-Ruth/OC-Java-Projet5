package com.safetynet.safetynetalerts.service;

import com.safetynet.safetynetalerts.datasource.DataBaseManager;
import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.repository.impl.MedicalRecordRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
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
        medicalRecord = new MedicalRecord("firstName2", "lastName2", LocalDate.of(1995, 5, 14), null, null);
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

}
