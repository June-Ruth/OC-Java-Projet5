package com.safetynet.safetynetalerts.service;

import com.safetynet.safetynetalerts.datasource.DataBaseManager;
import com.safetynet.safetynetalerts.model.FireStation;
import com.safetynet.safetynetalerts.repository.impl.json.FireStationRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Set;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FireStationServiceTest {

    private static FireStationService fireStationService;
    private FireStation fireStation;
    private Set<FireStation> fireStations;

    @Mock
    private static FireStationRepositoryImpl fireStationRepositoryImpl;

    @BeforeEach
    void beforeEach() {
        fireStationService = new FireStationService(fireStationRepositoryImpl);
        fireStation = new FireStation("address", 1);
        fireStations = DataBaseManager.INSTANCE.getDataBase().getFireStations();
    }

    @Test
    void getFireStationsTest() {
        when(fireStationRepositoryImpl.findAll()).thenReturn(fireStations);
        fireStationService.getFireStations();
        verify(fireStationRepositoryImpl, times(1)).findAll();
    }

    @Test
    void saveFireStationNewTest() {
        when(fireStationRepositoryImpl.save(fireStation)).thenReturn(true);
        fireStationService.saveFireStation(fireStation);
        verify(fireStationRepositoryImpl, times(1)).save(fireStation);
    }

    @Test
    void saveFireStationAlreadyExistingTest() {
        when(fireStationRepositoryImpl.save(fireStation)).thenReturn(false);
        fireStationService.saveFireStation(fireStation);
        verify(fireStationRepositoryImpl, times(1)).save(fireStation);
    }

    @Test
    void updateFireStationExistingTest() {
        when(fireStationRepositoryImpl.update(fireStation)).thenReturn(true);
        fireStationService.updateFireStation(fireStation);
        verify(fireStationRepositoryImpl, times(1)).update(fireStation);
    }

    @Test
    void updateFireStationUnknownTest() {
        when(fireStationRepositoryImpl.update(fireStation)).thenReturn(false);
        fireStationService.updateFireStation(fireStation);
        verify(fireStationRepositoryImpl, times(1)).update(fireStation);
    }

    @Test
    void deleteFireStationByAddressExistingTest() {
        when(fireStationRepositoryImpl.findByAddress(fireStation.getAddress())).thenReturn(fireStation);
        when(fireStationRepositoryImpl.delete(fireStation)).thenReturn(true);
        fireStationService.deleteFireStationbyAddress(fireStation.getAddress());
        verify(fireStationRepositoryImpl, times(1)).findByAddress(fireStation.getAddress());
        verify(fireStationRepositoryImpl, times(1)).delete(fireStation);
    }

    @Test
    void deleteFireStationByAddressUnknownTest() {
        when(fireStationRepositoryImpl.findByAddress(fireStation.getAddress())).thenReturn(null);
        fireStationService.deleteFireStationbyAddress(fireStation.getAddress());
        verify(fireStationRepositoryImpl, times(1)).findByAddress(fireStation.getAddress());
        verify(fireStationRepositoryImpl, times(0)).delete(fireStation);
    }

    @Test
    void deleteFireStationByNumberExistingTest() {
        when(fireStationRepositoryImpl.findAllByStationNumber(1)).thenReturn(fireStations);
        when(fireStationRepositoryImpl.deleteAll(fireStations)).thenReturn(true);
        fireStationService.deleteFireStationbyNumber(fireStation.getStation());
        verify(fireStationRepositoryImpl, times(1)).findAllByStationNumber(1);
        verify(fireStationRepositoryImpl, times(1)).deleteAll(fireStations);
    }

    @Test
    void deleteFireStationByNumberUnknownTest() {
        when(fireStationRepositoryImpl.findAllByStationNumber(1)).thenReturn(null);
        fireStationService.deleteFireStationbyNumber(fireStation.getStation());
        verify(fireStationRepositoryImpl, times(1)).findAllByStationNumber(1);
        verify(fireStationRepositoryImpl, times(0)).deleteAll(fireStations);
    }

}
