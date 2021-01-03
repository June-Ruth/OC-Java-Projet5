package com.safetynet.safetynetalerts.repository.impl;

import com.safetynet.safetynetalerts.datasource.DataBase;
import com.safetynet.safetynetalerts.datasource.DataBaseManager;
import com.safetynet.safetynetalerts.model.FireStation;
import com.safetynet.safetynetalerts.repository.FireStationRepository;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

@Repository
public class FireStationRepositoryImpl implements FireStationRepository {

    /**
     * @see DataBase
     */
    private DataBase dataBase = DataBaseManager.INSTANCE.getDataBase();
    /**
     * Fire stations available in dataBase.
     */
    private Set<FireStation> fireStations = dataBase.getFireStations();

    /**
     * Find all FireStation Mapping in DataBase.
     * @return Set of fireStation available in DataBase
     */
    @Override
    public Set<FireStation> findAll() {
        return fireStations;
    }

    /**
     * Find a fire Station mapping by its address.
     * Return null if there is no fire station corresponding.
     * @param address searched
     * @return the FireStation corresponding.
     */
    @Override
    public FireStation findByAddress(final String address) {
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

    /**
     * Find all Fire Station Mapping associated to a station number.
     * @param stationNumber searched
     * @return set of all Fire Station corresponding
     */
    @Override
    public Set<FireStation> findAllByStationNumber(final int stationNumber) {
        Set<FireStation> result = new HashSet<>();
        fireStations.iterator().forEachRemaining((fireStation) -> {
            if (fireStation.getStation() == stationNumber) {
                result.add(fireStation);
            }
        });
        return result;
    }

    /**
     * Save a fire station mapping.
     * Duplicate are not allowed.
     * Return false if fire station is already existing.
     * @param fireStation to save.
     * @return true if saved.
     */
    @Override
    public boolean save(final FireStation fireStation) {
        return fireStations.add(fireStation);
    }

    /**
     * Update a fire station already existing.
     * Return false if the station is not existing.
     * @param fireStation to update full filled
     * @return true if it's saved correctly
     */
    @Override
    public boolean update(final FireStation fireStation) {
        if (fireStations.remove(fireStation)) {
            return fireStations.add(fireStation);
        } else {
            return false;
        }
    }

    /**
     * Delete a fire station already existing.
     * Return false if fire station is not found.
     * @param fireStation to delete
     * @return true if it's correctly deleted
     */
    @Override
    public boolean delete(final FireStation fireStation) {
        return fireStations.remove(fireStation);
    }

    /**
     * Delete all fireStation defined in a Set.
     * return false if one of the fire station is not found.
     * @param fireStationsToDelete as a set of all the station to delete.
     * @return true if it"s correctly deleted
     */
    @Override
    public boolean deleteAll(final Set<FireStation> fireStationsToDelete) {
        return fireStations.removeAll(fireStationsToDelete);
    }
}
