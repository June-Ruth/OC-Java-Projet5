package com.safetynet.safetynetalerts.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class FireIT {

    @Autowired
    public MockMvc mockMvc;

    @Test
    void getAllPersonsAndStationByAddressExistingTest() throws Exception {
        mockMvc.perform(get("/fire?address=address"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("firstName")));
    }
}