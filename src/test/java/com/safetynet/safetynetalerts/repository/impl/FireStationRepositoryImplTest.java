package com.safetynet.safetynetalerts.repository.impl;

import com.safetynet.safetynetalerts.datasource.DataBase;
import com.safetynet.safetynetalerts.datasource.DataBaseManager;
import com.safetynet.safetynetalerts.datasource.DataBaseTestService;
import com.safetynet.safetynetalerts.model.FireStation;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class FireStationRepositoryImplTest {

    private static DataBase dataBase;
    private static DataBaseTestService dataBaseTestService = new DataBaseTestService();
    private static Set<FireStation> fireStations;

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
    void findByAddressExistingTest() {
        FireStation fireStation = new FireStation("address", 1);
        FireStation toTest = fireStationRepositoryImpl.findByAddress(fireStation.getAddress());
        assertEquals(fireStation.getStation(), toTest.getStation());
        assertEquals(fireStation.getAddress(), toTest.getAddress());
    }

    @Test
    void findByAddressUnknownTest() {
        FireStation fireStation = new FireStation("address3", 3);
        FireStation toTest = fireStationRepositoryImpl.findByAddress(fireStation.getAddress());
        assertNull(toTest);
    }

    @Test
    void findAllByStationNumberExistingTest() {
        FireStation fireStation = new FireStation("address", 1);
        Set<FireStation> result = fireStationRepositoryImpl.findAllByStationNumber(1);
        assertEquals(1, result.size());
        assertEquals(fireStation.getAddress(), result.iterator().next().getAddress());
    }

    @Test
    void findAllByStationNumberUnknownTest() {
        assertNull(fireStationRepositoryImpl.findAllByStationNumber(3));
    }

    @Test
    void saveFireStationMappingNewTest() {
        FireStation fireStation = new FireStation("address3", 3);
        assertTrue(fireStationRepositoryImpl.save(fireStation));
        assertEquals(3, fireStations.size());
    }

    @Test
    void saveFireStationMappingAlreadyExistingTest() {
        FireStation fireStation = new FireStation("address", 1);
        assertFalse(fireStationRepositoryImpl.save(fireStation));
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
        assertFalse(fireStationRepositoryImpl.update(fireStation));
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
        assertFalse(fireStationRepositoryImpl.delete(fireStation));
    }

    @Test
    void deleteAllExistingTest() {
        Set<FireStation> fireStationsToDelete = new HashSet<>();
        FireStation fireStation = new FireStation("address", 1);
        fireStationsToDelete.add(fireStation);
        assertTrue(fireStationRepositoryImpl.deleteAll(fireStationsToDelete));
        assertEquals(1, dataBase.getFireStations().size());
    }

    @Test
    void deleteAllWithUnknownTest() {
        Set<FireStation> fireStationsToDelete = new HashSet<>();
        FireStation fireStation = new FireStation("address3", 3);
        fireStationsToDelete.add(fireStation);
        assertFalse(fireStationRepositoryImpl.deleteAll(fireStationsToDelete));
    }

    @Test
    void findAllAddressByStationNumberExistingTest() {
        int stationNumber = 1;
        assertEquals(1, fireStationRepositoryImpl.findAllAddressByStationNumber(stationNumber).size());
    }

    @Test
    void findAllAddressByStationNumberUnknownTest() {
        int stationNumber = 6;
        assertNull(fireStationRepositoryImpl.findAllAddressByStationNumber(stationNumber));
    }

}
