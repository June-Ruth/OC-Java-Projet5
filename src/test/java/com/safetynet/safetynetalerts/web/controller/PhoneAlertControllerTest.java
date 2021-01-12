package com.safetynet.safetynetalerts.web.controller;

import com.safetynet.safetynetalerts.service.FireStationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = PhoneAlertController.class)
public class PhoneAlertControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllPhoneByStationNumberExistingTest() throws Exception {
        int stationNumber = 1;
        Set<String> result = new HashSet<>();
        result.add("123-456-789");
        //when(fireStationService.getAllPhoneByStationNumber(stationNumber)).thenReturn(result);
        mockMvc.perform(get("/phoneAlert?firestation=" + stationNumber))
                .andExpect(status().isOk());
    }

    @Test
    void getAllPhoneByStationNumberUnknownTest() throws Exception {
        int stationNumber = 6;
        //when(fireStationService.getAllPhoneByStationNumber(stationNumber)).thenReturn(null);
        mockMvc.perform(get("/phoneAlert?firestation=" + stationNumber))
                .andExpect(status().isNotFound());
    }
}
