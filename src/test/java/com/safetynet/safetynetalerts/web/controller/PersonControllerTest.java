package com.safetynet.safetynetalerts.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.service.PersonService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashSet;
import java.util.Set;

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
        mockMvc.perform(get("/persons"))
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

    @Test
    void getAllEmailInCityExistingTest() throws Exception {
        String city = "city";
        Set<String> result = new HashSet<>();
        result.add("test@test.com");
        when(personService.getAllEmailInCity(any(String.class))).thenReturn(result);
        mockMvc.perform(get("/communityEmail?city=" + city))
                .andExpect(status().isOk());
    }

    @Test
    void getAllEmailInCityUnknownTest() throws Exception {
        String city = "test";
        when(personService.getAllEmailInCity(any(String.class))).thenReturn(null);
        mockMvc.perform(get("/communityEmail?city=" + city))
                .andExpect(status().isNotFound());
    }

    @Disabled
    @Test
    void getAllChildrenByAddressExistingTest() throws Exception {
        //TODO
        String address = "address";
        //when().thenReturn();
        mockMvc.perform(get("/childAlert?address=" + address))
                .andExpect(status().isOk());
    }

    @Disabled
    @Test
    void getAllChildrenByAddressUnknownTest() throws Exception {
        //TODO
        String address = "test";
        //when().thenReturn();
        mockMvc.perform(get("/childAlert?address=" + address))
                .andExpect(status().isNotFound());
    }

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
