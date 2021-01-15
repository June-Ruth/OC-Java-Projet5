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
public class PersonInfoIT {

    @Autowired
    public MockMvc mockMvc;

    @Test
    public void getAllInfoByFirstNameAndLastNameIT() throws Exception {
        mockMvc.perform(get("/personInfo?firstName=firstName&lastName=lastName"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("allergie1")));
    }
}
