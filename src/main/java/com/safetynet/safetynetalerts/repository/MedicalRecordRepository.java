package com.safetynet.safetynetalerts.repository;

import com.safetynet.safetynetalerts.model.MedicalRecord;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalRecordRepository extends Dao<MedicalRecord> {

    MedicalRecord findByFirstNameAndLastName(String firstName, String lastName);

}
