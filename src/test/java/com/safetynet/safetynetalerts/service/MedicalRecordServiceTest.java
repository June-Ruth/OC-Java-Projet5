package com.safetynet.safetynetalerts.service;

import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.repository.impl.json.MedicalRecordRepositoryImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class MedicalRecordServiceTest {

    private static MedicalRecordService medicalRecordService;
    private MedicalRecord medicalRecord;

    @Mock
    private MedicalRecordRepositoryImpl medicalRecordRepositoryImpl;

    @BeforeAll
    void beforeAll() {
        medicalRecordService = new MedicalRecordService(medicalRecordRepositoryImpl);
        medicalRecord = new MedicalRecord("firstName2", "lastName2", LocalDate.of(1995, 5, 14), null, null);
    }

    @Test
    void getMedicalRecordsTest() {
        doNothing().when(medicalRecordRepositoryImpl.findAll());
        medicalRecordService.getMedicalsRecords();
        verify(medicalRecordRepositoryImpl, times(1)).findAll();
    }

    @Test
    void saveMedicalRecordNewTest() {
        when(medicalRecordRepositoryImpl.save(medicalRecord)).thenReturn(medicalRecord);
        medicalRecordService.saveMedicalRecord(medicalRecord);
        verify(medicalRecordRepositoryImpl, times(1)).save(medicalRecord);
    }

    @Test
    void saveMedicalRecordAlreadyExistingTest() {
        when(medicalRecordRepositoryImpl.save(medicalRecord)).thenThrow(Exception.class);
        assertThrows(Exception.class, () -> medicalRecordService.saveMedicalRecord(medicalRecord));
    }

    @Test
    void saveMedicalRecordWithInvalidArgumentsTest() {
        when(medicalRecordRepositoryImpl.save(medicalRecord)).thenThrow(Exception.class);
        assertThrows(Exception.class, () -> medicalRecordService.saveMedicalRecord(medicalRecord));
    }

    @Test
    void updateMedicalRecordExistingTest() {
        when(medicalRecordRepositoryImpl.update(medicalRecord)).thenReturn(true);
        medicalRecordService.updateMedicalRecord(medicalRecord);
        verify(medicalRecordRepositoryImpl, times(1)).update(medicalRecord);
    }

    @Test
    void updateMedicalRecordUnknownTest() {
        when(medicalRecordRepositoryImpl.update(medicalRecord)).thenThrow(Exception.class);
        assertThrows(Exception.class, () -> medicalRecordService.updateMedicalRecord(medicalRecord));
    }

    @Test
    void updateMedicalRecordWithInvalidArgumentsTest() {
        when(medicalRecordRepositoryImpl.update(medicalRecord)).thenThrow(Exception.class);
        assertThrows(Exception.class, () -> medicalRecordService.updateMedicalRecord(medicalRecord));
    }

    @Test
    void deleteMedicalRecordExistingTest() {
        when(medicalRecordRepositoryImpl.delete(medicalRecord)).thenReturn(false);
        medicalRecordService.deleteMedicalRecord(medicalRecord);
        verify(medicalRecordRepositoryImpl, times(1)).delete(medicalRecord);
    }

    @Test
    void deleteMedicalRecordUnknownTest() {
        when(medicalRecordRepositoryImpl.delete(medicalRecord)).thenThrow(Exception.class);
        assertThrows(Exception.class, () -> medicalRecordService.deleteMedicalRecord(medicalRecord));
    }

}
