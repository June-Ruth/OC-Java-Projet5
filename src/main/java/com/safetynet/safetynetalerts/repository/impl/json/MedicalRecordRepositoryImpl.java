package com.safetynet.safetynetalerts.repository.impl.json;

import com.safetynet.safetynetalerts.datasource.DataBase;
import com.safetynet.safetynetalerts.datasource.DataBaseManager;
import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.repository.MedicalRecordRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public class MedicalRecordRepositoryImpl implements MedicalRecordRepository {

    private DataBase dataBase = DataBaseManager.INSTANCE.getDataBase();
    private Set<MedicalRecord> medicalRecords = dataBase.getMedicalRecords();

    @Override
    public Set<MedicalRecord> findAll() {
        return medicalRecords;
    }

    @Override
    public boolean save(MedicalRecord medicalRecord) {
        return medicalRecords.add(medicalRecord);
    }

    @Override
    public boolean update(MedicalRecord medicalRecord) {
        if (medicalRecords.remove(medicalRecord)) {
            return medicalRecords.add(medicalRecord);
        } else {
            return false;
        }
    }

    @Override
    public boolean delete(MedicalRecord medicalRecord) {
        return medicalRecords.remove(medicalRecord);
    }
}
