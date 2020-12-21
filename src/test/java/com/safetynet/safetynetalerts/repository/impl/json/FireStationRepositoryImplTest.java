package com.safetynet.safetynetalerts.repository.impl.json;

import com.safetynet.safetynetalerts.datasource.DataBase;
import com.safetynet.safetynetalerts.datasource.DataBaseManager;
import com.safetynet.safetynetalerts.model.FireStation;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class FireStationRepositoryImplTest {

    private static DataBase dataBase;
    private static List<FireStation> fireStations;

    private FireStationRepositoryImpl fireStationRepositoryImpl;

    @BeforeAll
    static void setUpBeforeAll() {
        dataBase =  DataBaseManager.INSTANCE.getDataBase();
        fireStations = dataBase.getFireStations();
    }

    @BeforeEach
    void setUpBeforeEach() {
        fireStationRepositoryImpl = new FireStationRepositoryImpl();
    }

    @Test
    void findAllFireStationsTest() {
        assertEquals(2, fireStationRepositoryImpl.findAll().size());
    }

    @Test
    void saveNewFireStationMappingTest() {
        FireStation fireStation = new FireStation("address3", 3);
        fireStationRepositoryImpl.save(fireStation);
        assertEquals(3, fireStations.size());
        fireStations.remove(2);
    }

    @Test
    void saveInvalidFireStationMappingTest() {
        //TODO : vérifier que si rentre un mapping incorect : voir cheminement et si possible et test cohérent
    }

    @Test
    void updateStationNumberWithAddressSuccessTest() {
        FireStation fireStation = fireStations.get(1);
        fireStation.setStation(3);
        fireStationRepositoryImpl.update(fireStation);
        assertEquals(3, fireStations.get(1).getStation());
        fireStation.setStation(1);
    }

    @Test
    void updateStationNumberWithUnknownAddressTest() {
        //TODO : vérifier qu'en entrant une adresse inconnue dans la base ... Voir cheminement à avoir
    }

    @Test
    void deleteFireStationsWithExistingAddressTest() {
        //TODO : vérifier qu'en entrant une adresse, l'adresse et sa station sont supprimées
    }

    @Test
    void deleteFireStationsWithExistingStationNumberTest() {
        //TODO : vérifier qu'en entrant une station, toutes les adresses associées et la station sont supprimées
    }

    @Test
    void deleteFireStationsWithUnknownAddressTest() {
        //TODO : vérifier qu'en entrant une adresse inconnue dans la base ... Voir cheminement à avoir
    }

    @Test
    void deleteFireStationsWithUnknownStationNumberTest() {
        //TODO : vérifier qu'en entrant une station inconnue dans la base ... Voir cheminement à avoir
    }
}
