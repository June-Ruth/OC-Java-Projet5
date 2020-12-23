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
        if (fireStations.contains(fireStation)) {
            return false;
        } else {
            fireStations.add(fireStation);
            return true;
        }
    }

    @Override
    public boolean update(FireStation fireStation) {
        int idToUpdate = fireStations.indexOf(fireStation);
        if (idToUpdate != -1) {
            fireStations.set(idToUpdate, fireStation);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean delete(FireStation fireStation) {
        int idToDelete = fireStations.indexOf(fireStation);
        if (idToDelete != -1) {
            fireStations.remove(idToDelete);
            return true;
        } else {
            return false;
        }
    }
}
