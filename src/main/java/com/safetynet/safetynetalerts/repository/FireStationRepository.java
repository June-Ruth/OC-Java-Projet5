package com.safetynet.safetynetalerts.repository;

import com.safetynet.safetynetalerts.model.FireStation;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface FireStationRepository extends Dao<FireStation> {

    FireStation findByAddress(String address);

    Set<FireStation> findAllByStationNumber(int stationNumber);
}
