package com.safetynet.safetynetalerts.repository.impl.json;

import com.safetynet.safetynetalerts.datasource.DataBase;
import com.safetynet.safetynetalerts.datasource.DataBaseManager;
import com.safetynet.safetynetalerts.model.FireStation;
import com.safetynet.safetynetalerts.repository.FireStationRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public class FireStationRepositoryImpl implements FireStationRepository {

    private DataBase dataBase = DataBaseManager.INSTANCE.getDataBase();
    private Set<FireStation> fireStations = dataBase.getFireStations();

    @Override
    public Set<FireStation> findAll() {
        return fireStations;
    }

    @Override
    public FireStation findByAdress(String address) {
        //TODO
    return null;
    }

    @Override
    public Set<FireStation> findByNumber(int stationNumber) {
        //TODO
        return null;
    }

    @Override
    public boolean save(FireStation fireStation) {
        return fireStations.add(fireStation);
    }

    @Override
    public boolean update(FireStation fireStation) {
        if (fireStations.remove(fireStation)) {
            return fireStations.add(fireStation);
        } else {
            return false;
        }
    }

    @Override
    public boolean delete(FireStation fireStation) {
        return fireStations.remove(fireStation);
    }
}
