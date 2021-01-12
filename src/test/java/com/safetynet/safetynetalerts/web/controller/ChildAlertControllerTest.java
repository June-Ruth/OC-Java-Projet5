package com.safetynet.safetynetalerts.web.controller;

import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.service.MedicalRecordService;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = ChildAlertController.class)
class ChildAlertControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MedicalRecordService medicalRecordService;

    @MockBean
    private PersonService personService;

    @Test
    void getAllChildrenByAddressExistingTest() throws Exception {
        String address = "address";
        Set<Person> persons = new HashSet<>();
        persons.add(new Person("expected", "test", "address", "test" , 123, "test", "test"));
        persons.add(new Person("test1", "test", "address", "test" , 123, "test", "test"));
        when(personService.getAllByAddress(address)).thenReturn(persons);
        when(medicalRecordService.getAge(any(Person.class))).thenReturn(14).thenReturn(22);
        mockMvc.perform(get("/childAlert?address=" + address))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("14")));
    }

    @Test
    void getAllChildrenByAddressUnknownTest() throws Exception {
        String address = "test";
        Set<Person> persons = new HashSet<>();
        persons.add(new Person("expected", "test", "address", "test" , 123, "test", "test"));
        persons.add(new Person("test1", "test", "address", "test" , 123, "test", "test"));
        when(personService.getAllByAddress(address)).thenReturn(null);
        mockMvc.perform(get("/childAlert?address=" + address))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("[]")));
    }
}
