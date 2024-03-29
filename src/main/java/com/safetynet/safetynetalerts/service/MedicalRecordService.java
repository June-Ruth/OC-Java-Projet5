package com.safetynet.safetynetalerts.service;

import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.repository.impl.MedicalRecordRepositoryImpl;
import com.safetynet.safetynetalerts.web.exceptions.NotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Service
public class MedicalRecordService {
    /**
     * @see Logger
     */
    private static final Logger LOGGER =
            LogManager.getLogger(MedicalRecordService.class);
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
    public Set<MedicalRecord> getAllMedicalRecords() {
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

    /**
     * Get Medical Record associated to a person.
     * @param person .
     * @return medical record.
     */
    public MedicalRecord getMedicalRecord(final Person person) {
        LOGGER.debug("Process to find medical record of "
                + person.getFirstName() + " " + person.getLastName());
        return medicalRecordRepositoryImpl.findByFirstNameAndLastName(
                person.getFirstName(), person.getLastName());
    }

    /**
     * Obtain age of the person.
     * @param person concerned.
     * @return age.
     */
    public int getAge(final Person person) {
        MedicalRecord medicalRecord =
                medicalRecordRepositoryImpl.findByFirstNameAndLastName(
                        person.getFirstName(), person.getLastName());
        if (medicalRecord != null) {
            LOGGER.debug("Find medical record for " + person.getFirstName()
                    + " " + person.getLastName() + " . Return age.");
            return LocalDate.now().compareTo(medicalRecord.getBirthdate());
        } else {
            RuntimeException e = new NotFoundException("Medical Record for "
                    + person.getFirstName() + " " + person.getLastName()
                    + " was not found. Cannot calculate age");
            LOGGER.error(e);
            throw e;
        }
    }

    /**
     * Get all medical record depending of a set of person.
     * @param persons as set.
     * @return set of medical record associated.
     */
    public Set<MedicalRecord> getAllMedicalRecordsByPersonList(
            final Set<Person> persons) {
        Set<MedicalRecord> medicalRecordSet = new HashSet<>();
        persons.iterator().forEachRemaining(person -> {
            MedicalRecord medicalRecord =
                    medicalRecordRepositoryImpl.findByFirstNameAndLastName(
                            person.getFirstName(), person.getLastName());
            if (medicalRecord != null) {
                medicalRecordSet.add(medicalRecord);
            }
        });
        LOGGER.debug("End of process to find all medical records");
        return medicalRecordSet;
    }
}
