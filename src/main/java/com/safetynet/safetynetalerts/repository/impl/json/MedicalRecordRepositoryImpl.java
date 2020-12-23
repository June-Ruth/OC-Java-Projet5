package com.safetynet.safetynetalerts.repository.impl.json;

import com.safetynet.safetynetalerts.datasource.DataBase;
import com.safetynet.safetynetalerts.datasource.DataBaseManager;
import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.repository.MedicalRecordRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MedicalRecordRepositoryImpl implements MedicalRecordRepository {

    private DataBase dataBase = DataBaseManager.INSTANCE.getDataBase();
    private List<MedicalRecord> medicalRecords = dataBase.getMedicalRecords();

    @Override
    public List<MedicalRecord> findAll() {
        return medicalRecords;
    }

    @Override
    public boolean save(MedicalRecord medicalRecord) {
        boolean isSaved;
        if (medicalRecords.contains(medicalRecord)) {
            isSaved = false;
        } else {
            medicalRecords.add(medicalRecord);
            isSaved = true;
        }
        return isSaved;
    }

    @Override
    public boolean update(MedicalRecord medicalRecord) {
        boolean isUpdated;
        int idToUpdate = medicalRecords.indexOf(medicalRecord);
        if (idToUpdate != -1) {
            medicalRecords.set(idToUpdate, medicalRecord);
            isUpdated = true;
        } else {
            isUpdated = false;
        }
        return isUpdated;
    }

    @Override
    public boolean delete(MedicalRecord medicalRecord) {
        boolean isDeleted;
        int idToDelete = medicalRecords.indexOf(medicalRecord);
        if (idToDelete != -1) {
            medicalRecords.remove(idToDelete);
            isDeleted = true;
        } else {
            isDeleted = false;
        }
        return isDeleted;
    }
}
