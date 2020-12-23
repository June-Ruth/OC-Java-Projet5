package com.safetynet.safetynetalerts.service;

import com.safetynet.safetynetalerts.model.FireStation;
import com.safetynet.safetynetalerts.repository.impl.json.FireStationRepositoryImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class FireStationService {

    private FireStationRepositoryImpl fireStationRepositoryImpl;

    FireStationService(FireStationRepositoryImpl pFireStationRepositoryImpl) {
        Objects.requireNonNull( pFireStationRepositoryImpl);
        fireStationRepositoryImpl =  pFireStationRepositoryImpl;
    }

    /**
     * Get all entities for FireStation
     * @return //TODO
     */
    //TODO
    public List<FireStation> getFireStations() {
        return fireStationRepositoryImpl.findAll();
    }

    /**
     * Save a new FireStation mapping
     * @param fireStation to create
     * @return //TODO
     */
    //TODO
    public FireStation saveFireStation(FireStation fireStation) {
        fireStationRepositoryImpl.save(fireStation);
        return fireStation;
    }

    /**
     * Update an existing fire station.
     * @param fireStation - FireStation Object updated
     * @return //TODO
     */
    //TODO
    public boolean updateFireStation(FireStation fireStation) {
        return fireStationRepositoryImpl.update(fireStation);
    }

    /**
     * Delete an existing FireStation by its address
     * @param address to delete
     */
    //TODO
    public boolean deleteFireStationbyAddress(String address) {
        FireStation fireStation = null;
        return fireStationRepositoryImpl.delete(fireStation);
    }

    /**
     * Delete an existing FireStation by its number and all address associated.
     * @param stationNumber to delete
     */
    //TODO
    public boolean deleteFireStationbyNumber(int stationNumber) {
        FireStation fireStation = null;
        return  fireStationRepositoryImpl.delete(fireStation);
    }

}
