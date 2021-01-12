package com.safetynet.safetynetalerts.web.controller;

import com.safetynet.safetynetalerts.service.FireStationService;
import com.safetynet.safetynetalerts.service.PersonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = PhoneAlertController.class)
class PhoneAlertControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FireStationService fireStationService;

    @MockBean
    private PersonService personService;

    @Test
    void getAllPhoneByStationNumberExistingTest() throws Exception {
        int stationNumber = 1;
        Set<String> addressList = new HashSet<>();
        addressList.add("address");
        Set<String> result = new HashSet<>();
        result.add("123");


        when(fireStationService.getAllAddressByStationNumber(stationNumber)).thenReturn(addressList);
        when(personService.findAllPhoneByAddress(anyString())).thenReturn(result);

        mockMvc.perform(get("/phoneAlert?firestation=" + stationNumber))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("123")));
    }

    @Test
    void getAllPhoneByStationNumberUnknownTest() throws Exception {
        int stationNumber = 6;
        when(fireStationService.getAllAddressByStationNumber(stationNumber)).thenReturn(null);
        mockMvc.perform(get("/phoneAlert?firestation=" + stationNumber))
                .andExpect(status().isNotFound());
    }
}
