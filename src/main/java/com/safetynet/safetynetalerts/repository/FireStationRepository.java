package com.safetynet.safetynetalerts.repository;

import com.safetynet.safetynetalerts.model.FireStation;
import com.safetynet.safetynetalerts.model.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Repository
public interface FireStationRepository extends JpaRepository<FireStation, Integer> {

    static List<FireStation> convertJsonToJava(File file) {
        List<FireStation> fireStations = new ArrayList<>();

        return fireStations;
    }

}
