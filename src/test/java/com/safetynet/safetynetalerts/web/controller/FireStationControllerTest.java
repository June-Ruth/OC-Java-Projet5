package com.safetynet.safetynetalerts.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.safetynetalerts.model.FireStation;
import com.safetynet.safetynetalerts.service.FireStationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = FireStationController.class)
class FireStationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FireStationService fireStationService;

    @Test
    void getFireStationsTest() throws Exception {
        mockMvc.perform(get("/firestation"))
                .andExpect(status().isOk());
    }

    @Test
    void createFireStationNewTest() throws Exception {
        FireStation fireStation = new FireStation("test", 6);
        when(fireStationService.saveFireStation(any(FireStation.class))).thenReturn(true);
        mockMvc.perform(post("/firestation")
                .content(new ObjectMapper().writeValueAsString(fireStation))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void createFireStationAlreadyExistingTest() throws Exception {
        FireStation fireStation = new FireStation("address", 1);
        when(fireStationService.saveFireStation(any(FireStation.class))).thenReturn(false);
        mockMvc.perform(post("/firestation")
                .content(new ObjectMapper().writeValueAsString(fireStation))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict());
    }

    @Test
    void createFireStationWithInvalidArgumentsTest() throws Exception {
        FireStation fireStation = new FireStation(null, 1);
        mockMvc.perform(post("/firestation")
                .content(new ObjectMapper().writeValueAsString(fireStation))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void updateFireStationExistingTest() throws Exception {
        FireStation fireStation = new FireStation("address", 5);
        when(fireStationService.updateFireStation(any(FireStation.class))).thenReturn(true);
        mockMvc.perform(put("/firestation")
                .content(new ObjectMapper().writeValueAsString(fireStation))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void updateFireStationUnknownTest() throws Exception {
        FireStation fireStation = new FireStation("test", 6);
        when(fireStationService.updateFireStation(any(FireStation.class))).thenReturn(false);
        mockMvc.perform(put("/firestation")
                .content(new ObjectMapper().writeValueAsString(fireStation))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void updateFireStationWithInvalidArgumentsTest() throws Exception {
        FireStation fireStation = new FireStation(null, 1);
        mockMvc.perform(put("/firestation")
                .content(new ObjectMapper().writeValueAsString(fireStation))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deleteFireStationNumberMappingExisitngTest() throws Exception {
        int station = 1;
        when(fireStationService.deleteFireStationbyNumber(any(Integer.class))).thenReturn(true);
        mockMvc.perform(delete("/firestation/station/{station}", station))
                .andExpect(status().isOk());
    }

    @Test
    void deleteFireStationNumberMappingUnknownTest() throws Exception {
        int station = 5;
        when(fireStationService.deleteFireStationbyNumber(any(Integer.class))).thenReturn(false);
        mockMvc.perform(delete("/firestation/station/{station}", station))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteFireStationAddressMappingExistingTest() throws Exception {
        String address = "address";
        when(fireStationService.deleteFireStationbyAddress(any(String.class))).thenReturn(true);
        mockMvc.perform(delete("/firestation/address/{address}", address))
                .andExpect(status().isOk());
    }

    @Test
    void deleteFireStationAddressMappingUnknownTest() throws Exception {
        String address = "test";
        when(fireStationService.deleteFireStationbyAddress(any(String.class))).thenReturn(false);
        mockMvc.perform(delete("/firestation/address/{address}", address))
                .andExpect(status().isNotFound());
    }

    @Test
    void getAllPhoneByStationNumberExistingTest() throws Exception {
        //TODO
        int stationNumber = 1;
        //when().thenReturn();
        mockMvc.perform(get("/phoneAlert?firestation={stationNumber}", stationNumber))
                .andExpect(status().isOk());

    }

    @Test
    void getAllPhoneByStationNumberUnknownTest() throws Exception {
        //TODO
        int stationNumber = 6;
        //when().thenReturn();
        mockMvc.perform(get("/phoneAlert?firestation={stationNumber}", stationNumber))
                .andExpect(status().isNotFound());
    }

    @Test
    void getAllPersonsAndCountdownByStationNumberExistingTest() throws Exception {
        //TODO
        int stationNumber = 1;
        //when().thenReturn();
        mockMvc.perform(get("/firestation?stationNumber={stationNumber}", stationNumber))
                .andExpect(status().isOk());
    }

    @Test
    void getAllPersonsAndCountdownByStationNumberUnknownTest() throws Exception {
        //TODO
        int stationNumber = 1;
        //when().thenReturn();
        mockMvc.perform(get("/firestation?stationNumber={stationNumber}", stationNumber))
                .andExpect(status().isNotFound());
    }

    @Test
    void getAllPersonsAndStationByAddressExistingTest() throws Exception {
        //TODO
        String address = "address";
        //when().thenReturn();
        mockMvc.perform(get("/fire?address={address}", address))
                .andExpect(status().isOk());
    }

    @Test
    void getAllPersonsAndStationByAddressUnknownTest() throws Exception {
        //TODO
        String address = "address";
        //when().thenReturn();
        mockMvc.perform(get("/fire?address={address}", address))
                .andExpect(status().isNotFound());
    }

    @Test
    void getAllFloodsByStationNumberExisitngTest() throws Exception {
        //TODO
        int stationNumber = 1;
        //when().thenReturn();
        mockMvc.perform(get("stations?station={stationNumber}", stationNumber))
                .andExpect(status().isOk());
    }

    @Test
    void getAllFloodsByStationNumberUnknownTest() throws Exception {
        //TODO
        int stationNumber = 5;
        //when().thenReturn();
        mockMvc.perform(get("stations?station={stationNumber}", stationNumber))
                .andExpect(status().isNotFound());
    }
}
