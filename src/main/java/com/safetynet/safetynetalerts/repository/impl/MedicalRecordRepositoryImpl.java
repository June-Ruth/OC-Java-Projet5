package com.safetynet.safetynetalerts.repository.impl;

import com.safetynet.safetynetalerts.datasource.DataBase;
import com.safetynet.safetynetalerts.datasource.DataBaseManager;
import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.repository.MedicalRecordRepository;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

@Repository
public class MedicalRecordRepositoryImpl implements MedicalRecordRepository {

    /**
     * @see DataBase
     */
    private DataBase dataBase = DataBaseManager.INSTANCE.getDataBase();
    /**
     * Set of medical record present in DataBase.
     */
    private Set<MedicalRecord> medicalRecords = dataBase.getMedicalRecords();

    /**
     * Find all medical record present in DataBase.
     * @return a set of medical record
     */
    @Override
    public Set<MedicalRecord> findAll() {
        return medicalRecords;
    }

    /**
     * Find one medical record by the firstName and the lastName
     * of the person concerned.
     * @param firstName searched
     * @param lastName searched
     * @return medical record concerned
     */
    @Override
    public MedicalRecord findByFirstNameAndLastName(
            final String firstName, final String lastName) {
        Set<MedicalRecord> result = new HashSet<>();
        medicalRecords.iterator().forEachRemaining((medicalRecord) -> {
            if (medicalRecord.getFirstName().equals(firstName)
            && medicalRecord.getLastName().equals(lastName)) {
                result.add(medicalRecord);
            }
        });
        if (result.iterator().hasNext()) {
            return result.iterator().next();
        } else {
            return null;
        }
    }


    /**
     * Save a medical record.
     * Return false if the medical record is already existing.
     * @param medicalRecord
     * @return true if it's correctly saved
     */
    @Override
    public boolean save(final MedicalRecord medicalRecord) {
        return medicalRecords.add(medicalRecord);
    }

    /**
     * Update a medical Record.
     * Return false if the medical record doesn't exist.
     * @param medicalRecord to update full filled
     * @return true if it's correctly updated
     */
    @Override
    public boolean update(final MedicalRecord medicalRecord) {
        if (medicalRecords.remove(medicalRecord)) {
            return medicalRecords.add(medicalRecord);
        } else {
            return false;
        }
    }

    /**
     * Delete one medical record.
     * Return false if the medical record doesn't exist.
     * @param medicalRecord to delete
     * @return true if it's correctly deleted
     */
    @Override
    public boolean delete(final MedicalRecord medicalRecord) {
        return medicalRecords.remove(medicalRecord);
    }

    /**
     * Delete all the medical record contained in a set.
     * Return false if one of the medical record doesn't exist.
     * @param medicalRecordsToDelete set of medical record to delete.
     * @return true if it's correctly deleted.
     */
    @Override
    public boolean deleteAll(final Set<MedicalRecord> medicalRecordsToDelete) {
        return medicalRecords.removeAll(medicalRecordsToDelete);
    }
}
