package com.safetynet.safetynetalerts.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.safetynetalerts.datasource.DataBaseTestService;
import com.safetynet.safetynetalerts.model.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PersonIT {

    @Autowired
    public MockMvc mockMvc;

    private static DataBaseTestService dataBaseTestService = new DataBaseTestService();

    @AfterEach
    void tearDown() {
        dataBaseTestService.clearDBTest();
        dataBaseTestService.restoreDBTest();
    }

    @Test
    void getPersonsIT() throws Exception {
        mockMvc.perform(get("/persons"))
                .andExpect(status().isOk());
    }

    @Test
    void postPersonIT() throws Exception {
        Person person = new Person("test", "test", "test", "test", 123, "test", "test");
        mockMvc.perform(post("/person")
                .content(new ObjectMapper().writeValueAsString(person))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void putPersonIT() throws Exception {
        Person person = new Person("firstName", "lastName", "address", "city", 123, "test", "test");
        mockMvc.perform(put("/person")
                .content(new ObjectMapper().writeValueAsString(person))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void deletePersonIT() throws Exception {
        String firstName = "firstName";
        String lastName = "lastName";
        mockMvc.perform(delete("/person/{firstName}_{lastName}", firstName, lastName))
                .andExpect(status().isOk());
    }
}
