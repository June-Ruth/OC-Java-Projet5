package com.safetynet.safetynetalerts.repository.impl.json;

import com.safetynet.safetynetalerts.datasource.DataBase;
import com.safetynet.safetynetalerts.datasource.DataBaseManager;
import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.repository.MedicalRecordRepository;
import edu.umd.cs.findbugs.annotations.OverrideMustInvoke;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
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
    public MedicalRecord findByFirstNameAndLastName(String firstName, String lastName) {
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

    @Override
    public boolean deleteAll(Set<MedicalRecord> medicalRecordsToDelete) {
        return medicalRecords.removeAll(medicalRecordsToDelete);
    }
}
