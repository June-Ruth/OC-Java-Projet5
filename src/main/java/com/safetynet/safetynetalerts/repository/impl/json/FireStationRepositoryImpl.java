package com.safetynet.safetynetalerts.repository.impl.json;

import com.safetynet.safetynetalerts.model.FireStation;
import com.safetynet.safetynetalerts.repository.FireStationRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FireStationRepositoryImpl implements FireStationRepository {

    //TODO
    @Override
    public List<FireStation> findAll() {
        return null;
    }

    //TODO
    @Override
    public FireStation save(FireStation fireStation) {
        return null;
    }

    //TODO
    @Override
    public boolean update(FireStation fireStation) {
        return false;
    }

    //TODO
    @Override
    public boolean delete(FireStation fireStation) {
        return false;
    }
}
