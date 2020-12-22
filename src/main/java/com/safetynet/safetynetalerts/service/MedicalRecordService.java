package com.safetynet.safetynetalerts.service;

import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.repository.impl.json.MedicalRecordRepositoryImpl;

import java.util.List;
import java.util.Objects;

public class MedicalRecordService {

    private MedicalRecordRepositoryImpl medicalRecordRepositoryImpl;

    MedicalRecordService(MedicalRecordRepositoryImpl pMedicalRecordRepositoryImpl) {
        Objects.requireNonNull(pMedicalRecordRepositoryImpl);
        medicalRecordRepositoryImpl = pMedicalRecordRepositoryImpl;
    }

    /**
     * Get all entities for Medical Record
     * @return //TODO
     */
    //TODO
    public List<MedicalRecord> getMedicalsRecords() {
        return null;
    }

    /**
     * Save a new MedicalRecord
     * @return //TODO
     */
    //TODO
    public MedicalRecord saveMedicalRecord(MedicalRecord medicalRecord) {
        return null;
    }

    /**
     * Update an existing medical record.
     * @param medicalRecord - Medical Record Object updated
     * @return //TODO
     */
    //TODO
    public MedicalRecord updateMedicalRecord(MedicalRecord medicalRecord) {
        return null;
    }

    /**
     * Delete an existing MedicalRecord.
     * @param medicalRecord to delete
     */
    //TODO
    public void deleteMedicalRecord(MedicalRecord medicalRecord) {}
}
