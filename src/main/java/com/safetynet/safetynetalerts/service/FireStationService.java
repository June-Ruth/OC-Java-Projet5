package com.safetynet.safetynetalerts.service;

import com.safetynet.safetynetalerts.model.FireStation;
import com.safetynet.safetynetalerts.repository.impl.FireStationRepositoryImpl;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;

@Service
public class FireStationService {

    /**
     * @See FireStationRepositoryImpl
     */
    private FireStationRepositoryImpl fireStationRepositoryImpl;

    /**
     * Public constructor for FireStationService.
     * Require non null FireStationRepositoryImpl.
     * @param pFireStationRepositoryImpl not null
     */
    public FireStationService(
            final FireStationRepositoryImpl pFireStationRepositoryImpl) {
        Objects.requireNonNull(pFireStationRepositoryImpl);
        fireStationRepositoryImpl =  pFireStationRepositoryImpl;
    }

    /**
     * Get all entities for FireStation.
     * @return list of all FireStation
     */
    public Set<FireStation> getFireStations() {
        return fireStationRepositoryImpl.findAll();
    }

    /**
     * Save a new FireStation mapping.
     * @param fireStation to create
     * @return true if it's saved
     */
    public boolean saveFireStation(final FireStation fireStation) {
        return fireStationRepositoryImpl.save(fireStation);
    }

    /**
     * Update an existing fire station.
     * @param fireStation - FireStation Object updated
     * @return true if it's updated
     */
    public boolean updateFireStation(final FireStation fireStation) {
        return fireStationRepositoryImpl.update(fireStation);
    }

    /**
     * Delete an existing FireStation by its address.
     * @param address to delete
     * @return true if it's deleted
     */
    public boolean deleteFireStationbyAddress(final String address) {
        FireStation fireStation =
                fireStationRepositoryImpl.findByAddress(address);
        return fireStationRepositoryImpl.delete(fireStation);
    }

    /**
     * Delete an existing FireStation by its number and all address associated.
     * @param stationNumber to delete
     * @return true if it's deleted
     */
    public boolean deleteFireStationbyNumber(final int stationNumber) {
        Set<FireStation> fireStationsToDelete =
                fireStationRepositoryImpl.findAllByStationNumber(stationNumber);
        return fireStationRepositoryImpl.deleteAll(fireStationsToDelete);
    }

}
