package com.safetynet.safetynetalerts.web.controller;

import com.safetynet.safetynetalerts.service.PersonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CommunityEmailController.class)
class CommunityEmailControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonService personService;

    @Test
    void getAllEmailInCityExistingTest() throws Exception {
        String city = "city";
        Set<String> result = new HashSet<>();
        result.add("test@test.com");
        when(personService.getAllEmailInCity(any(String.class))).thenReturn(result);
        mockMvc.perform(get("/communityEmail?city=" + city))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("test@test")));
    }

    @Test
    void getAllEmailInCityUnknownTest() throws Exception {
        String city = "test";
        when(personService.getAllEmailInCity(any(String.class))).thenReturn(null);
        mockMvc.perform(get("/communityEmail?city=" + city))
                .andExpect(status().isNotFound());
    }
}
