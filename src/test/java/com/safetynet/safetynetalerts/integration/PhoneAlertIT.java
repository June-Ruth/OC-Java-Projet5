package com.safetynet.safetynetalerts.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PhoneAlertIT {

    @Autowired
    public MockMvc mockMvc;

    @Test
    public void getAllPhoneByStationNumberIT() throws Exception {
        mockMvc.perform(get("/phoneAlert?firestation=1"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("123-456-7890")));
    }
}
