package com.safetynet.safetynetalerts.service;

import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.repository.impl.MedicalRecordRepositoryImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;

@Service
public class MedicalRecordService {
    /**
     * @see Logger
     */
    private static final Logger LOGGER = LogManager.getLogger(MedicalRecordService.class);
    /**
     * @see MedicalRecordRepositoryImpl
     */
    private MedicalRecordRepositoryImpl medicalRecordRepositoryImpl;

    /**
     * Public constructor for MedicalRecordService.
     * Requires non null MedicalRecordRepositoryImpl.
     * @param pMedicalRecordRepositoryImpl not null
     */
    MedicalRecordService(
            final MedicalRecordRepositoryImpl pMedicalRecordRepositoryImpl) {
        Objects.requireNonNull(pMedicalRecordRepositoryImpl);
        medicalRecordRepositoryImpl = pMedicalRecordRepositoryImpl;
    }

    /**
     * Get all entities for Medical Record.
     * @return list of all medical records
     */
    public Set<MedicalRecord> getMedicalsRecords() {
        LOGGER.debug("Process to get all medical records.");
        return medicalRecordRepositoryImpl.findAll();
    }

    /**
     * Save a new MedicalRecord.
     * Return false if the medical record already exists.
     * @param medicalRecord to save full filled
     * @return true if it's correctly saved.
     */
    public boolean saveMedicalRecord(final MedicalRecord medicalRecord) {
        LOGGER.debug("Process to save medical record.");
        return medicalRecordRepositoryImpl.save(medicalRecord);
    }

    /**
     * Update an existing medical record.
     * Return false if medical record doesn't exist.
     * @param medicalRecord - Medical Record Object updated
     * @return true if it's correctly updated
     */
    public boolean updateMedicalRecord(final MedicalRecord medicalRecord) {
        LOGGER.debug("Process to  update medical record.");
        return medicalRecordRepositoryImpl.update(medicalRecord);
    }

    /**
     * Delete an existing MedicalRecord.
     * Return false if the medical record is not found.
     * @param firstName to delete
     * @param lastName to delete
     * @return true if medical record is correctly deleted
     */
    public boolean deleteMedicalRecord(
            final String firstName, final String lastName) {
        LOGGER.debug("Process to find and delete medical record for "
            + firstName + lastName);
        MedicalRecord medicalRecord = medicalRecordRepositoryImpl
                .findByFirstNameAndLastName(firstName, lastName);
        return medicalRecordRepositoryImpl.delete(medicalRecord);
    }
}
