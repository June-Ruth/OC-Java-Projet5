package com.safetynet.safetynetalerts.service;

import com.safetynet.safetynetalerts.model.FireStation;
import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.model.dto.*;
import com.safetynet.safetynetalerts.repository.impl.FireStationRepositoryImpl;
import com.safetynet.safetynetalerts.util.DtoConverter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class FireStationService {
    /**
     * @see Logger
     */
    private static final Logger LOGGER = LogManager.getLogger(FireStationService.class);
    /**
     * @see FireStationRepositoryImpl
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
        LOGGER.debug("Process to get all fire stations.");
        return fireStationRepositoryImpl.findAll();
    }

    /**
     * Save a new FireStation mapping.
     * @param fireStation to create
     * @return true if it's saved
     */
    public boolean saveFireStation(final FireStation fireStation) {
        LOGGER.debug("Process to save fire station.");
        return fireStationRepositoryImpl.save(fireStation);
    }

    /**
     * Update an existing fire station.
     * @param fireStation - FireStation Object updated
     * @return true if it's updated
     */
    public boolean updateFireStation(final FireStation fireStation) {
        LOGGER.debug("Process to  update fire station.");
        return fireStationRepositoryImpl.update(fireStation);
    }

    /**
     * Delete an existing FireStation by its address.
     * @param address to delete
     * @return true if it's deleted
     */
    public boolean deleteFireStationbyAddress(final String address) {
        LOGGER.debug("Process to find and delete fire station at the address : "
                + address);
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
        LOGGER.debug("Process to find addresses for fire station number "
                + stationNumber);
        Set<FireStation> fireStationsToDelete =
                fireStationRepositoryImpl.findAllByStationNumber(stationNumber);
        LOGGER.debug("Addresses for fire station number "
                + stationNumber + " find. Process to delete them.");
        return fireStationRepositoryImpl.deleteAll(fireStationsToDelete);
    }

    public Set<String> getAllAddressByStationNumber(final int stationNumber) {
        return fireStationRepositoryImpl.findAllAddressByStationNumber(stationNumber);
    }

    public FireStation getByAddress(final String address) {
        return fireStationRepositoryImpl.findByAddress(address);
    }





}
