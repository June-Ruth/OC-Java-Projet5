package com.safetynet.safetynetalerts.repository;

import com.safetynet.safetynetalerts.model.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Repository
public interface MedicalRecordRepository extends JpaRepository <MedicalRecord, Integer> {

    static List<MedicalRecord> convertJsonToJava(File file) {
        List<MedicalRecord> medicalRecords = new ArrayList<>();

        return medicalRecords;
    }

}
