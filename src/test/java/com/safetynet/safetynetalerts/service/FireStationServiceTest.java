package com.safetynet.safetynetalerts.service;

import com.safetynet.safetynetalerts.model.FireStation;
import com.safetynet.safetynetalerts.repository.impl.json.FireStationRepositoryImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class FireStationServiceTest {

    private static FireStationService fireStationService;
    private FireStation fireStation;

    @Mock
    private FireStationRepositoryImpl fireStationRepositoryImpl;

    @BeforeAll
    void beforeAll() {
        fireStationService = new FireStationService(fireStationRepositoryImpl);
        fireStation = new FireStation("address", 1);
    }

    @Test
    void getFireStationsTest() {
        doNothing().when(fireStationRepositoryImpl.findAll());
        fireStationService.getFireStations();
        verify(fireStationRepositoryImpl, times(1)).findAll();
    }

    @Test
    void saveFireStationNewTest() {
        when(fireStationRepositoryImpl.save(fireStation)).thenReturn(fireStation);
        fireStationService.saveFireStation(fireStation);
        verify(fireStationRepositoryImpl, times(1)).save(fireStation);
    }

    @Test
    void saveFireStationAlreadyExistingTest() {
        when(fireStationRepositoryImpl.save(fireStation)).thenThrow(Exception.class);
        assertThrows(Exception.class, () ->  fireStationService.saveFireStation(fireStation));
    }

    @Test
    void saveFireStationWithInvalidArgumentsTest() {
        when(fireStationRepositoryImpl.save(fireStation)).thenThrow(Exception.class);
        assertThrows(Exception.class, () ->  fireStationService.saveFireStation(fireStation));
    }

    @Test
    void updateFireStationExistingTest() {
        when(fireStationRepositoryImpl.update(fireStation)).thenReturn(true);
        verify(fireStationRepositoryImpl, times(1)).update(fireStation);
    }

    @Test
    void updateFireStationUnknownTest() {
        when(fireStationRepositoryImpl.update(fireStation)).thenThrow(Exception.class);
        assertThrows(Exception.class, () ->  fireStationService.saveFireStation(fireStation));
    }

    @Test
    void updateFireStationWithInvalidArgumentsTest() {
        when(fireStationRepositoryImpl.update(fireStation)).thenThrow(Exception.class);;
        assertThrows(Exception.class, () ->  fireStationService.saveFireStation(fireStation));
    }

    @Test
    void deleteFireStationByAddressExistingTest() {
        when(fireStationRepositoryImpl.delete(fireStation)).thenReturn(true);
        fireStationService.deleteFireStationbyAddress(fireStation.getAddress());
        verify(fireStationRepositoryImpl, times(1)).delete(fireStation);
    }

    @Test
    void deleteFireStationByAddressUnknownTest() {
        when(fireStationRepositoryImpl.delete(fireStation)).thenThrow(Exception.class);
        assertThrows(Exception.class, () -> fireStationService.deleteFireStationbyAddress(fireStation.getAddress()));
    }

    @Test
    void deleteFireStationByNumberExistingTest() {
        when(fireStationRepositoryImpl.delete(fireStation)).thenReturn(true);
        fireStationService.deleteFireStationbyNumber(fireStation.getStation());
        verify(fireStationRepositoryImpl, times(1)).delete(fireStation);
    }

    @Test
    void deleteFireStationByNumberUnknownTest() {
        when(fireStationRepositoryImpl.delete(fireStation)).thenThrow(Exception.class);
        assertThrows(Exception.class, () -> fireStationService.deleteFireStationbyNumber(fireStation.getStation()));
    }

}
