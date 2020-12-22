package com.safetynet.safetynetalerts.service;

import com.safetynet.safetynetalerts.model.FireStation;
import com.safetynet.safetynetalerts.repository.impl.json.FireStationRepositoryImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

//TODO
class FireStationServiceTest {

    private static FireStationService fireStationService;

    @Mock
    private FireStationRepositoryImpl fireStationRepositoryImpl;

    @BeforeAll
    void beforeAll() {
        fireStationService = new FireStationService(fireStationRepositoryImpl);
    }

    @Test
    void getFireStationsTest() {
        doNothing().when(fireStationRepositoryImpl.findAll());
        fireStationService.getFireStations();
        verify(fireStationRepositoryImpl, times(1)).findAll();
    }

    @Test
    void saveFireStationNewTest() {
        FireStation fireStation = new FireStation("address3", 3);
        doNothing().when(fireStationRepositoryImpl.save(fireStation));
        fireStationService.saveFireStation(fireStation);
        verify(fireStationRepositoryImpl, times(1)).save(fireStation);
    }

    @Test
    void saveFireStationAlreadyExistingTest() {
        FireStation fireStation = new FireStation("address", 1);
        doNothing().when(fireStationRepositoryImpl.save(fireStation));
        assertThrows(Exception.class, () ->  fireStationService.saveFireStation(fireStation));
    }

    @Test
    void saveFireStationWithInvalidArgumentsTest() {
        FireStation fireStation = new FireStation(null, 3);
        doNothing().when(fireStationRepositoryImpl.save(fireStation));
        assertThrows(Exception.class, () ->  fireStationService.saveFireStation(fireStation));
    }

    @Test
    void updateFireStationExistingTest() {
        int newStationNumber = 3;
        FireStation fireStation = new FireStation("address", newStationNumber);
        doNothing().when(fireStationRepositoryImpl.update(fireStation));
        verify(fireStationRepositoryImpl, times(1)).update(fireStation);
    }

    @Test
    void updateFireStationUnknownTest() {
        int newStationNumber = 3;
        FireStation fireStation = new FireStation("test", newStationNumber);
        doNothing().when(fireStationRepositoryImpl.update(fireStation));
        assertThrows(Exception.class, () ->  fireStationService.saveFireStation(fireStation));
    }

    @Test
    void updateFireStationWithInvalidArgumentsTest() {
        int newStationNumber = 3;
        FireStation fireStation = new FireStation(null, newStationNumber);
        assertThrows(Exception.class, () ->  fireStationService.saveFireStation(fireStation));
    }

    @Test
    void deleteFireStationByAddressExistingTest() {
        FireStation fireStation = new FireStation("address", 1);
        doNothing().when(fireStationRepositoryImpl.delete(fireStation));
        fireStationService.deleteFireStationbyAddress(fireStation.getAddress());
        verify(fireStationRepositoryImpl, times(1)).delete(fireStation);
    }

    @Test
    void deleteFireStationByAddressUnknownTest() {
        FireStation fireStation = new FireStation("test", 1);
        doNothing().when(fireStationRepositoryImpl.delete(fireStation));
        assertThrows(Exception.class, () -> fireStationService.deleteFireStationbyAddress(fireStation.getAddress()));
    }

    @Test
    void deleteFireStationByNumberExistingTest() {
        FireStation fireStation = new FireStation("address", 1);
        doNothing().when(fireStationRepositoryImpl.delete(fireStation));
        fireStationService.deleteFireStationbyNumber(fireStation.getStation());
        verify(fireStationRepositoryImpl, times(1)).delete(fireStation);
    }

    @Test
    void deleteFireStationByNumberUnknownTest() {
        FireStation fireStation = new FireStation("address", 3);
        doNothing().when(fireStationRepositoryImpl.delete(fireStation));
        assertThrows(Exception.class, () -> fireStationService.deleteFireStationbyNumber(fireStation.getStation()));
    }

}
