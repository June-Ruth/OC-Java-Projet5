package com.safetynet.safetynetalerts.service;

import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.repository.impl.json.MedicalRecordRepositoryImpl;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;

@Service
public class MedicalRecordService {

    private MedicalRecordRepositoryImpl medicalRecordRepositoryImpl;

    MedicalRecordService(MedicalRecordRepositoryImpl pMedicalRecordRepositoryImpl) {
        Objects.requireNonNull(pMedicalRecordRepositoryImpl);
        medicalRecordRepositoryImpl = pMedicalRecordRepositoryImpl;
    }

    /**
     * Get all entities for Medical Record.
     * @return list of all medical records
     */
    public Set<MedicalRecord> getMedicalsRecords() {
        return medicalRecordRepositoryImpl.findAll();
    }

    /**
     * Save a new MedicalRecord
     */
    public void saveMedicalRecord(MedicalRecord medicalRecord) {
        medicalRecordRepositoryImpl.save(medicalRecord);
    }

    /**
     * Update an existing medical record.
     * @param medicalRecord - Medical Record Object updated
     */
    public void updateMedicalRecord(MedicalRecord medicalRecord) {
        medicalRecordRepositoryImpl.update(medicalRecord);
    }

    /**
     * Delete an existing MedicalRecord.
     * @param firstName to delete
     * @param lastName to delete
     */
    public void deleteMedicalRecord(String firstName, String lastName) {
        MedicalRecord medicalRecord = medicalRecordRepositoryImpl.findByFirstNameAndLastName(firstName, lastName);
        medicalRecordRepositoryImpl.delete(medicalRecord);
    }
}
