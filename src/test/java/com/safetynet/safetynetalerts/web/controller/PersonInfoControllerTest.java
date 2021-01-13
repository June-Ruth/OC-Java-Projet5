package com.safetynet.safetynetalerts.web.controller;

import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.service.MedicalRecordService;
import com.safetynet.safetynetalerts.service.PersonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = PersonInfoController.class)
class PersonInfoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonService personService;

    @MockBean
    private MedicalRecordService medicalRecordService;

    @Test
    void getAllInfoByFirstNameAndLastNameExistingTest() throws Exception {
        //TODO
        String firstName = "firstName";
        String lastName = "lastName";
        Person person = new Person("firstName", "lastName", "address", "test" , 123, "test", "test");
        MedicalRecord medicalRecord = new MedicalRecord("firstName", "lastName", LocalDate.of(2000, 1, 1),null, null);

        when(personService.getByFirstNameAndLastName(firstName, lastName)).thenReturn(person);
        when(medicalRecordService.getMedicalRecord(person)).thenReturn(medicalRecord);

        int expectedAge = LocalDate.now().getYear() - 2000;

        mockMvc.perform(get("/personInfo?firstName=" + firstName + "&lastName=" + lastName))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(String.valueOf(expectedAge))));

    }

    @Test
    void getAllInfoByFirstNameAndLastNameUnknownTest() throws Exception {
        String firstName = "test";
        String lastName = "test";
        when(personService.getByFirstNameAndLastName(firstName, lastName)).thenReturn(null);
        mockMvc.perform(get("/personInfo?firstName=" + firstName + "&lastName=" + lastName))
                .andExpect(status().isNotFound());
    }
}
