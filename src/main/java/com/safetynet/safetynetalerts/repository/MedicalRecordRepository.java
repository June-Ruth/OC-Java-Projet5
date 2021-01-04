package com.safetynet.safetynetalerts.repository;

import com.safetynet.safetynetalerts.model.MedicalRecord;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalRecordRepository extends Dao<MedicalRecord> {

    /**
     * Find a medical record by the first name
     * and the last name of the person concerned.
     * @param firstName searched
     * @param lastName searched
     * @return the medical record concerned
     */
    MedicalRecord findByFirstNameAndLastName(String firstName, String lastName);

}
