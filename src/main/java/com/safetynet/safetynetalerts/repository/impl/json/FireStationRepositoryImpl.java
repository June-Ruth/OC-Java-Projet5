package com.safetynet.safetynetalerts.repository.impl.json;

import com.safetynet.safetynetalerts.datasource.DataBase;
import com.safetynet.safetynetalerts.datasource.DataBaseManager;
import com.safetynet.safetynetalerts.model.FireStation;
import com.safetynet.safetynetalerts.repository.FireStationRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class FireStationRepositoryImpl implements FireStationRepository {

    private DataBase dataBase = DataBaseManager.INSTANCE.getDataBase();
    private Set<FireStation> fireStations = dataBase.getFireStations();

    @Override
    public Set<FireStation> findAll() {
        return fireStations;
    }

    @Override
    public FireStation findByAddress(String address) {
        Set<FireStation> result = new HashSet<>();
        fireStations.iterator().forEachRemaining((fireStation) -> {
            if (fireStation.getAddress().equals(address)) {
                result.add(fireStation);
            }
        });
        if (result.iterator().hasNext()) {
            return result.iterator().next();
        } else {
            return null;
        }
    }

    @Override
    public Set<FireStation> findAllByStationNumber(int stationNumber) {
        Set<FireStation> result = new HashSet<>();
        fireStations.iterator().forEachRemaining((fireStation) -> {
            if (fireStation.getStation() == stationNumber) {
                result.add(fireStation);
            }
        });
        return result;
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

    @Override
    public boolean deleteAll(Set<FireStation> fireStationsToDelete) {
        return fireStations.removeAll(fireStationsToDelete);
    }
}
