package com.safetynet.safetynetalerts.controller;

import com.safetynet.safetynetalerts.service.PersonService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Disabled
@WebMvcTest(controllers = PersonController.class)
class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonService personService;

    @Test
    void getPersonsTest() throws Exception {
        mockMvc.perform(get("/person"))
                .andExpect(status().isOk());
    }

    @Test
    void createPersonNewTest() throws Exception {
        mockMvc.perform(post("person"))
                .andExpect(status().isCreated());
    }

    @Test
    void createPersonAlreadyExistingTest() throws Exception {
        mockMvc.perform(post("person"))
                .andExpect(status().isConflict());
    }

    @Test
    void createPersonWithInvalidArgumentsTest() throws Exception {
        mockMvc.perform(post("person"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void updatePersonExistingTest() throws Exception {
        mockMvc.perform(put("person"))
                .andExpect(status().isOk());
    }

    @Test
    void updatePersonUnknownTest() throws Exception {
        mockMvc.perform(put("person"))
                .andExpect(status().isNotFound());
    }

    @Test
    void updatePersonWithInvalidArgumentsTest() throws Exception {
        mockMvc.perform(put("person"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deletePersonExistingTest() throws Exception {
        mockMvc.perform(delete("person"))
                .andExpect(status().isOk());
    }

    @Test
    void deletePersonUnknownTest() throws Exception {
        mockMvc.perform(delete("person"))
                .andExpect(status().isNotFound());
    }
}
