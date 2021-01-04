package com.safetynet.safetynetalerts.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.service.PersonService;
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
        Person person = new Person("test", "test", "test", "test", 123, "test", "test");
        when(personService.savePerson(any(Person.class))).thenReturn(true);
        mockMvc.perform(post("/person")
                .content(new ObjectMapper().writeValueAsString(person))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void createPersonAlreadyExistingTest() throws Exception {
        Person person = new Person("firstName", "lastName", "address", "city", 123, "test", "test");
        when(personService.savePerson(any(Person.class))).thenReturn(false);
        mockMvc.perform(post("/person")
                .content(new ObjectMapper().writeValueAsString(person))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict());
    }

    @Test
    void createPersonWithInvalidArgumentsTest() throws Exception {
        Person person = new Person(null, null, null, null, 0, null, null);
        mockMvc.perform(post("/person")
                .content(new ObjectMapper().writeValueAsString(person))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void updatePersonExistingTest() throws Exception {
        Person person = new Person("firstName", "lastName", "address", "city", 123, "test", "test");
        when(personService.updatePerson(any(Person.class))).thenReturn(true);
        mockMvc.perform(put("/person")
                .content(new ObjectMapper().writeValueAsString(person))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void updatePersonUnknownTest() throws Exception {
        Person person = new Person("test", "test", "test", "test", 123, "test", "test");
        when(personService.updatePerson(any(Person.class))).thenReturn(false);
        mockMvc.perform(put("/person")
                .content(new ObjectMapper().writeValueAsString(person))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void updatePersonWithInvalidArgumentsTest() throws Exception {
        Person person = new Person(null, null, null, null, 0, null, null);
        mockMvc.perform(put("/person")
                .content(new ObjectMapper().writeValueAsString(person))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deletePersonExistingTest() throws Exception {
        String firstName = "firstName";
        String lastName = "lastName";
        when(personService.deletePerson(any(String.class), any(String.class))).thenReturn(true);
        mockMvc.perform(delete("/person/{firstName}{lastName}", firstName, lastName))
                .andExpect(status().isOk());
    }

    @Test
    void deletePersonUnknownTest() throws Exception {
        String firstName = "test";
        String lastName = "test";
        when(personService.deletePerson(any(String.class), any(String.class))).thenReturn(false);
        mockMvc.perform(delete("/person/{firstName}{lastName}", firstName, lastName))
                .andExpect(status().isNotFound());
    }
}
