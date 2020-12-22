package com.safetynet.safetynetalerts.repository.impl.json;

import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.repository.MedicalRecordRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MedicalRecordRepositoryImpl implements MedicalRecordRepository {

    //TODO
    @Override
    public List<MedicalRecord> findAll() {
        return null;
    }

    //TODO
    @Override
    public MedicalRecord save(MedicalRecord medicalRecord) {
        return null;
    }

    //TODO
    @Override
    public boolean update(MedicalRecord medicalRecord) {
        return false;
    }

    //TODO
    @Override
    public boolean delete(MedicalRecord medicalRecord) {
        return false;
    }
}
