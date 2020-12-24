package com.safetynet.safetynetalerts.service;

import com.safetynet.safetynetalerts.model.FireStation;
import com.safetynet.safetynetalerts.repository.impl.json.FireStationRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
public class FireStationService {

    private FireStationRepositoryImpl fireStationRepositoryImpl;

    public FireStationService(FireStationRepositoryImpl pFireStationRepositoryImpl) {
        Objects.requireNonNull( pFireStationRepositoryImpl);
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
    public void saveFireStation(FireStation fireStation) {
        fireStationRepositoryImpl.save(fireStation);
    }

    /**
     * Update an existing fire station.
     * @param fireStation - FireStation Object updated
     */
    public void updateFireStation(FireStation fireStation) {
        fireStationRepositoryImpl.update(fireStation);
    }

    /**
     * Delete an existing FireStation by its address
     * @param address to delete
     */
    public void deleteFireStationbyAddress(String address) {
        FireStation fireStation = fireStationRepositoryImpl.findByAddress(address);
        fireStationRepositoryImpl.delete(fireStation);
    }

    /**
     * Delete an existing FireStation by its number and all address associated.
     * @param stationNumber to delete
     */
    public void deleteFireStationbyNumber(int stationNumber) {
        Set<FireStation> fireStationsToDelete = fireStationRepositoryImpl.findAllAddressAssociatedWithStationNumber(stationNumber);
        //TODO : si fireStationsToDelete = null : envoie message
        fireStationRepositoryImpl.deleteAll(fireStationsToDelete);

    }

}
