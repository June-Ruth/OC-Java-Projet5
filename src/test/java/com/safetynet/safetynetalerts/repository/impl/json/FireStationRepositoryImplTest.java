package com.safetynet.safetynetalerts.repository.impl.json;

import com.safetynet.safetynetalerts.datasource.DataBase;
import com.safetynet.safetynetalerts.datasource.DataBaseManager;
import com.safetynet.safetynetalerts.datasource.DataBaseTestService;
import com.safetynet.safetynetalerts.model.FireStation;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FireStationRepositoryImplTest {

    private static DataBase dataBase;
    private static DataBaseTestService dataBaseTestService = new DataBaseTestService();
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

    @AfterEach
    void tearDown() {
        dataBaseTestService.clearDBTest();
        dataBaseTestService.restoreDBTest();
    }

    @Test
    void findAllFireStationsTest() {
        assertEquals(2, fireStationRepositoryImpl.findAll().size());
    }

    @Test
    void saveFireStationMappingNewTest() {
        FireStation fireStation = new FireStation("address3", 3);
        fireStationRepositoryImpl.save(fireStation);
        assertEquals(3, fireStations.size());
    }

    @Test
    void saveFireStationMappingAlreadyExistingTest() {
        FireStation fireStation = new FireStation("address", 1);
        assertThrows(Exception.class, () -> fireStationRepositoryImpl.save(fireStation));

    }

    @Test
    void saveFireStationMappingWithInvalidArgumentsTest() {
        FireStation fireStation = new FireStation(null, 3);
        assertThrows(Exception.class, () -> fireStationRepositoryImpl.save(fireStation));
    }

    @Test
    void updateStationNumberWithExistingAddressTest() {
        int newStationNumber = 3;
        FireStation fireStation = new FireStation("address", newStationNumber);
        assertTrue(fireStationRepositoryImpl.update(fireStation));
    }

    @Test
    void updateStationNumberWithUnknownAddressTest() {
        int newStationNumber = 3;
        FireStation fireStation = new FireStation("test", newStationNumber);
        assertThrows(Exception.class, () -> fireStationRepositoryImpl.update(fireStation));
    }

    @Test
    void updateStationNumberWithInvalidArgumentsTest() {
        int newStationNumber = 3;
        FireStation fireStation = new FireStation(null, newStationNumber);
        assertThrows(Exception.class, () -> fireStationRepositoryImpl.update(fireStation));
    }

    @Test
    void deleteFireStationExistingTest() {
        FireStation fireStation = new FireStation("address", 1);
        assertTrue(fireStationRepositoryImpl.delete(fireStation));
        assertEquals(1, dataBase.getFireStations().size());
    }

    @Test
    void deleteFireStationUnknownTest() {
        FireStation fireStation = new FireStation("test", 1);
        assertThrows(Exception.class, () -> fireStationRepositoryImpl.delete(fireStation));
    }
}
