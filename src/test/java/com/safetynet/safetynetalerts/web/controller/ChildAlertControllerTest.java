package com.safetynet.safetynetalerts.web.controller;

import com.safetynet.safetynetalerts.service.MedicalRecordService;
import com.safetynet.safetynetalerts.service.PersonService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ChildAlertController.class)
public class ChildAlertControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    MedicalRecordService medicalRecordService;

    @MockBean
    PersonService personService;


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

}
