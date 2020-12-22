package com.safetynet.safetynetalerts.service;

import com.safetynet.safetynetalerts.model.FireStation;
import com.safetynet.safetynetalerts.repository.impl.json.FireStationRepositoryImpl;

import java.util.List;
import java.util.Objects;

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
        return null;
    }

    /**
     * Save a new FireStation mappinf
     * @return //TODO
     */
    //TODO
    public FireStation saveFireStation(FireStation fireStation) {
        return null;
    }

    /**
     * Update an existing fire station.
     * @param fireStation - FireStation Object updated
     * @return //TODO
     */
    //TODO
    public FireStation updateFireStation(FireStation fireStation) {
        return null;
    }

    /**
     * Delete an existing FireStation.
     * @param fireStation to delete
     */
    //TODO
    public void deleteFireStation(FireStation fireStation) {}

}
