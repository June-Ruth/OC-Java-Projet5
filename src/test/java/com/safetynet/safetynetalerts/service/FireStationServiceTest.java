package com.safetynet.safetynetalerts.service;

import com.safetynet.safetynetalerts.datasource.DataBaseManager;
import com.safetynet.safetynetalerts.model.FireStation;
import com.safetynet.safetynetalerts.repository.impl.FireStationRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FireStationServiceTest {

    private static FireStationService fireStationService;
    private FireStation fireStation;
    private Set<FireStation> fireStations;

    @Mock
    private static FireStationRepositoryImpl fireStationRepositoryImpl;

    @Mock
    private static PersonService personService;

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
        assertTrue(fireStationService.saveFireStation(fireStation));
        verify(fireStationRepositoryImpl, times(1)).save(fireStation);
    }

    @Test
    void saveFireStationAlreadyExistingTest() {
        when(fireStationRepositoryImpl.save(fireStation)).thenReturn(false);
        assertFalse(fireStationService.saveFireStation(fireStation));
        verify(fireStationRepositoryImpl, times(1)).save(fireStation);
    }

    @Test
    void updateFireStationExistingTest() {
        when(fireStationRepositoryImpl.update(fireStation)).thenReturn(true);
        assertTrue(fireStationService.updateFireStation(fireStation));
        verify(fireStationRepositoryImpl, times(1)).update(fireStation);
    }

    @Test
    void updateFireStationUnknownTest() {
        when(fireStationRepositoryImpl.update(fireStation)).thenReturn(false);
        assertFalse(fireStationService.updateFireStation(fireStation));
        verify(fireStationRepositoryImpl, times(1)).update(fireStation);
    }

    @Test
    void deleteFireStationByAddressExistingTest() {
        when(fireStationRepositoryImpl.findByAddress(fireStation.getAddress())).thenReturn(fireStation);
        when(fireStationRepositoryImpl.delete(fireStation)).thenReturn(true);
        assertTrue(fireStationService.deleteFireStationbyAddress(fireStation.getAddress()));
        verify(fireStationRepositoryImpl, times(1)).findByAddress(fireStation.getAddress());
        verify(fireStationRepositoryImpl, times(1)).delete(fireStation);
    }

    @Test
    void deleteFireStationByAddressUnknownTest() {
        when(fireStationRepositoryImpl.findByAddress(fireStation.getAddress())).thenReturn(null);
        assertFalse(fireStationService.deleteFireStationbyAddress(fireStation.getAddress()));
        verify(fireStationRepositoryImpl, times(1)).findByAddress(fireStation.getAddress());
        verify(fireStationRepositoryImpl, times(0)).delete(fireStation);
    }

    @Test
    void deleteFireStationByNumberExistingTest() {
        when(fireStationRepositoryImpl.findAllByStationNumber(1)).thenReturn(fireStations);
        when(fireStationRepositoryImpl.deleteAll(fireStations)).thenReturn(true);
        assertTrue(fireStationService.deleteFireStationbyNumber(fireStation.getStation()));
        verify(fireStationRepositoryImpl, times(1)).findAllByStationNumber(1);
        verify(fireStationRepositoryImpl, times(1)).deleteAll(fireStations);
    }

    @Test
    void deleteFireStationByNumberUnknownTest() {
        when(fireStationRepositoryImpl.findAllByStationNumber(1)).thenReturn(null);
        assertFalse(fireStationService.deleteFireStationbyNumber(fireStation.getStation()));
        verify(fireStationRepositoryImpl, times(1)).findAllByStationNumber(1);
        verify(fireStationRepositoryImpl, times(0)).deleteAll(fireStations);
    }

    @Disabled
    @Test
    void getAllPhoneByStationNumberExistingTest() {
        Set<String> result1 = new HashSet<>();
        result1.add("address1");
        result1.add("address2");
        Set<String> result2 = new HashSet<>();
        Set<String> result3 = new HashSet<>();
        result2.add("phone1");
        result3.add("phone2");
        when(fireStationRepositoryImpl.findAllAddressByStationNumber(any(Integer.class))).thenReturn(result1);
        when(personService.findAllPhoneByAddress("address1")).thenReturn(result2);
        when(personService.findAllPhoneByAddress("address2")).thenReturn(result3);
        //assertEquals(2, fireStationService.getAllPhoneByStationNumber(1).size());
    }

    @Disabled
    @Test
    void getAllPhoneByStationNumberUnknownTest() {
        when(fireStationRepositoryImpl.findAllAddressByStationNumber(any(Integer.class))).thenReturn(null);
        //assertNull(fireStationService.getAllPhoneByStationNumber(1));
    }

    @Test
    void getAllChildrenByAddressExistingTest() {
        //TODO
    }

    @Test
    void getAllChildrenByAddressUnknownTest() {
        //TODO
    }

    @Test
    void getAllPersonsAndCountdownByStationNumberExistingTest()  {
        //TODO
    }

    @Test
    void getAllPersonsAndCountdownByStationNumberUnknownTest()  {
        //TODO
    }

    @Test
    void getAllPersonsAndStationByAddressExistingTest()  {
        //TODO
    }

    @Test
    void getAllPersonsAndStationByAddressUnknownTest()  {
        //TODO
    }

    @Test
    void getAllFloodsByStationNumberExistingTest() {
        //TODO
    }

    @Test
    void getAllFloodsByStationNumberUnknownTest() {
        //TODO
    }
}
