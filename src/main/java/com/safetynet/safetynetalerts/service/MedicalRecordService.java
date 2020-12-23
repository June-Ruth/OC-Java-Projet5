package com.safetynet.safetynetalerts.service;

import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.repository.impl.json.MedicalRecordRepositoryImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
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
        return medicalRecordRepositoryImpl.findAll();
    }

    /**
     * Save a new MedicalRecord
     * @return //TODO
     */
    //TODO
    public MedicalRecord saveMedicalRecord(MedicalRecord medicalRecord) {
        return medicalRecordRepositoryImpl.save(medicalRecord);
    }

    /**
     * Update an existing medical record.
     * @param medicalRecord - Medical Record Object updated
     * @return //TODO
     */
    //TODO
    public boolean updateMedicalRecord(MedicalRecord medicalRecord) {
        return medicalRecordRepositoryImpl.update(medicalRecord);
    }

    /**
     * Delete an existing MedicalRecord.
     * @param medicalRecord to delete
     */
    //TODO
    public boolean deleteMedicalRecord(MedicalRecord medicalRecord) {

        return medicalRecordRepositoryImpl.delete(medicalRecord);
    }
}
