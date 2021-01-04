package com.safetynet.safetynetalerts.repository;

import com.safetynet.safetynetalerts.model.FireStation;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface FireStationRepository extends Dao<FireStation> {

    /**
     * Find a fireStation mapping by the address.
     * @param address
     * @return the fire station corresponding to the address
     */
    FireStation findByAddress(String address);

    /**
     * Find all the fire station mapping associated with a station number.
     * @param stationNumber
     * @return Set of all firestation mapping concerned
     */
    Set<FireStation> findAllByStationNumber(int stationNumber);
}
