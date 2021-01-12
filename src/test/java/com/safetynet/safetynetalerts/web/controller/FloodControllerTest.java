package com.safetynet.safetynetalerts.web.controller;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = FloodController.class)
public class FloodControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Disabled
    @Test
    void getAllFloodsByStationNumberExisitngTest() throws Exception {
        //TODO
        int stationNumber = 1;
        //when().thenReturn();
        mockMvc.perform(get("/flood/stations?station=" + stationNumber))
                .andExpect(status().isOk());
    }

    @Disabled
    @Test
    void getAllFloodsByStationNumberUnknownTest() throws Exception {
        //TODO
        int stationNumber = 5;
        //when().thenReturn();
        mockMvc.perform(get("/flood/stations?station=" +stationNumber))
                .andExpect(status().isNotFound());
    }
}
