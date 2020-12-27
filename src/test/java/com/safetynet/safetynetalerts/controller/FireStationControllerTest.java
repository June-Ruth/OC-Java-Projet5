package com.safetynet.safetynetalerts.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.safetynetalerts.model.FireStation;
import com.safetynet.safetynetalerts.service.FireStationService;
import org.junit.jupiter.api.Disabled;
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
        mockMvc.perform(put("/firestation"))
                .andExpect(status().isOk());
    }

    @Test
    void updateFireStationUnknownTest() throws Exception {
        mockMvc.perform(put("/firestation"))
                .andExpect(status().isNotFound());
    }

    @Test
    void updateFireStationWithInvalidArgumentsTest() throws Exception {
        mockMvc.perform(put("/firestation"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deleteFireStationNumberMappingExisitngTest() throws Exception {
        mockMvc.perform(delete("/firestation"))
                .andExpect(status().isOk());
    }

    @Test
    void deleteFireStationNumberMappingUnknownTest() throws Exception {
        mockMvc.perform(delete("/firestation"))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteFireStationAddressMappingExistingTest() throws Exception {
        mockMvc.perform(delete("/firestation"))
                .andExpect(status().isOk());
    }

    @Test
    void deleteFireStationAddressMappingUnknownTest() throws Exception {
        mockMvc.perform(delete("/firestation"))
                .andExpect(status().isNotFound());
    }
}
