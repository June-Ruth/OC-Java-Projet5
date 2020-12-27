package com.safetynet.safetynetalerts.service;

import com.safetynet.safetynetalerts.model.FireStation;
import com.safetynet.safetynetalerts.repository.impl.json.FireStationRepositoryImpl;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;

@Service
public class FireStationService {

    private FireStationRepositoryImpl fireStationRepositoryImpl;

    public FireStationService(FireStationRepositoryImpl pFireStationRepositoryImpl) {
        Objects.requireNonNull(pFireStationRepositoryImpl);
        fireStationRepositoryImpl =  pFireStationRepositoryImpl;
    }

    /**
     * Get all entities for FireStation
     * @return list of all FireStation
     */
    public Set<FireStation> getFireStations() {
        return fireStationRepositoryImpl.findAll();
    }

    /**
     * Save a new FireStation mapping
     * @param fireStation to create
     */
    public boolean saveFireStation(FireStation fireStation) {
        return fireStationRepositoryImpl.save(fireStation);
    }

    /**
     * Update an existing fire station.
     * @param fireStation - FireStation Object updated
     */
    public boolean updateFireStation(FireStation fireStation) {
        return fireStationRepositoryImpl.update(fireStation);
    }

    /**
     * Delete an existing FireStation by its address
     * @param address to delete
     */
    public boolean deleteFireStationbyAddress(String address) {
        FireStation fireStation = fireStationRepositoryImpl.findByAddress(address);
        if (fireStation != null) {
            return fireStationRepositoryImpl.delete(fireStation);
        } else {
            return false;
        }
    }

    /**
     * Delete an existing FireStation by its number and all address associated.
     * @param stationNumber to delete
     */
    public boolean deleteFireStationbyNumber(int stationNumber) {
        Set<FireStation> fireStationsToDelete = fireStationRepositoryImpl.findAllByStationNumber(stationNumber);
        if (fireStationsToDelete != null) {
            return fireStationRepositoryImpl.deleteAll(fireStationsToDelete);
        } else {
            return false;
        }

    }

}
