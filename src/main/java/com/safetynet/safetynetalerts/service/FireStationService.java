package com.safetynet.safetynetalerts.service;

import com.safetynet.safetynetalerts.model.FireStation;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.model.dto.PersonContactInfoDTO;
import com.safetynet.safetynetalerts.model.dto.PersonHealthInfoDTO;
import com.safetynet.safetynetalerts.repository.impl.FireStationRepositoryImpl;
import com.safetynet.safetynetalerts.repository.impl.PersonRepositoryImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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
    private PersonService personService;

    /**
     * Public constructor for FireStationService.
     * Require non null FireStationRepositoryImpl.
     * @param pFireStationRepositoryImpl not null
     */
    public FireStationService(
            final FireStationRepositoryImpl pFireStationRepositoryImpl,
            final PersonService pPersonService) {
        Objects.requireNonNull(pFireStationRepositoryImpl);
        Objects.requireNonNull(pPersonService);
        fireStationRepositoryImpl =  pFireStationRepositoryImpl;
        personService = pPersonService;
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

    /**
     * Get all phone number of inhabitant associated to the given station number.
     * @param stationNumber concerned
     * @return set of all phone number.
     */
    public Set<String> getAllPhoneByStationNumber(final int stationNumber) {
        Set<String> phoneList = new HashSet<>();
        Set<String> addressList = fireStationRepositoryImpl.findAllAddressByStationNumber(stationNumber);
        if (addressList != null) {
            addressList.iterator().forEachRemaining((address) -> {
                phoneList.addAll(personService.findAllPhoneByAddress(address));
            });
            LOGGER.debug("Find all phone corresponding to station number " + stationNumber);
            return phoneList;
        } else {
            LOGGER.debug("No fireStation at station number " + stationNumber);
            return null;
        }
    }

    /**
     * Get all persons inhabitant near a specific station number and the countdown of adult and child.
     * @param stationNumber specific
     * @return Set of person info
     */
    public Set<PersonContactInfoDTO> getAllPersonsAndCountdownByStationNumber(final int stationNumber) {
        //TODO
        return null;
    }

    /**
     * Get list of inhabitant at the specified address and the station number concerned.
     * @param address .
     * @return List of inhabitant
     */
    public Set<PersonHealthInfoDTO> getAllPersonsAndStationByAddress(final String address) {
        //TODO
        return null;
    }

    /**
     * Get all flood by station number.
     * Each flood has a list with Person.
     * @param stationNumber .
     * @return List of inhabitant
     */
    public Set<PersonHealthInfoDTO> getAllFloodsByStationNumber(final int stationNumber) {
        //TODO
        return null;
    }


}
