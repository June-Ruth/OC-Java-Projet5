package com.safetynet.safetynetalerts.repository.impl.json;

import com.safetynet.safetynetalerts.datasource.DataBase;
import com.safetynet.safetynetalerts.datasource.DataBaseManager;
import com.safetynet.safetynetalerts.model.FireStation;
import com.safetynet.safetynetalerts.repository.FireStationRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FireStationRepositoryImpl implements FireStationRepository {

    private DataBase dataBase = DataBaseManager.INSTANCE.getDataBase();
    private List<FireStation> fireStations = dataBase.getFireStations();

    @Override
    public List<FireStation> findAll() {
        return fireStations;
    }

    @Override
    public boolean save(FireStation fireStation) {
        boolean isSaved;
        if (fireStations.contains(fireStation)) {
            isSaved = false;
        } else {
            fireStations.add(fireStation);
            isSaved = true;
        }
        return isSaved;
    }

    @Override
    public boolean update(FireStation fireStation) {
        boolean isUpdated;
        int idToUpdate = fireStations.indexOf(fireStation);
        if (idToUpdate != -1) {
            fireStations.set(idToUpdate, fireStation);
            isUpdated = true;
        } else {
            isUpdated = false;
        }
        return isUpdated;
    }

    @Override
    public boolean delete(FireStation fireStation) {
        boolean isDeleted;
        int idToDelete = fireStations.indexOf(fireStation);
        if (idToDelete != -1) {
            fireStations.remove(idToDelete);
            isDeleted = true;
        } else {
            isDeleted = false;
        }
        return isDeleted;
    }
}
