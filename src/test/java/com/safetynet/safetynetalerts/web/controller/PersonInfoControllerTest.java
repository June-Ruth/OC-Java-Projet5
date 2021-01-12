package com.safetynet.safetynetalerts.web.controller;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = PersonInfoController.class)
public class PersonInfoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    //@MockBean Service

    @Disabled
    @Test
    void getAllInfoByFirstNameAndLastNameExistingTest() throws Exception {
        //TODO
        String firstName = "firstName";
        String lastName = "lastName";
        //when().thenReturn();
        mockMvc.perform(get("personInfo?firstName=" + firstName + "&lastName=" + lastName))
                .andExpect(status().isOk());
    }

    @Disabled
    @Test
    void getAllInfoByFirstNameAndLastNameUnknownTest() throws Exception {
        //TODO
        String firstName = "test";
        String lastName = "test";
        //when().thenReturn();
        mockMvc.perform(get("personInfo?firstName=" + firstName + "&lastName=" + lastName))
                .andExpect(status().isNotFound());
    }
}
