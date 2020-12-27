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
    public boolean saveMedicalRecord(MedicalRecord medicalRecord) {
        return medicalRecordRepositoryImpl.save(medicalRecord);
    }

    /**
     * Update an existing medical record.
     * @param medicalRecord - Medical Record Object updated
     */
    public boolean updateMedicalRecord(MedicalRecord medicalRecord) {
        return medicalRecordRepositoryImpl.update(medicalRecord);
    }

    /**
     * Delete an existing MedicalRecord.
     * @param firstName to delete
     * @param lastName to delete
     */
    public boolean deleteMedicalRecord(String firstName, String lastName) {
        MedicalRecord medicalRecord = medicalRecordRepositoryImpl.findByFirstNameAndLastName(firstName, lastName);
        if (medicalRecord != null) {
            return medicalRecordRepositoryImpl.delete(medicalRecord);
        } else {
            return false;
        }
    }
}
