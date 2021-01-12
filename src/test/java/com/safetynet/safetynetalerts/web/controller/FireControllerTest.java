package com.safetynet.safetynetalerts.web.controller;

import com.safetynet.safetynetalerts.model.FireStation;
import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.service.FireStationService;
import com.safetynet.safetynetalerts.service.MedicalRecordService;
import com.safetynet.safetynetalerts.service.PersonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = FireController.class)
public class FireControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonService personService;

    @MockBean
    private MedicalRecordService medicalRecordService;

    @MockBean
    private FireStationService fireStationService;

    @Test
    void getAllPersonsAndStationByAddressExistingTest() throws Exception {
        String address = "address";
        FireStation fireStation = new FireStation("address", 1);
        Set<Person> persons = new HashSet<>();
        persons.add(new Person("test", "test", "address", "test" , 123, "test", "test"));
        persons.add(new Person("test1", "test", "address", "test" , 123, "test", "test"));
        Set<MedicalRecord> medicalRecords = new HashSet<>();
        medicalRecords.add(new MedicalRecord("test", "test", LocalDate.of(1990, 1, 1),null, null ));
        medicalRecords.add(new MedicalRecord("test1", "test", LocalDate.of(2000, 1, 1),null, null ));

        when(fireStationService.getByAddress(address)).thenReturn(fireStation);
        when(personService.getAllByAddress(address)).thenReturn(persons);
        when(medicalRecordService.getAllMedicalRecordsByPersonList(persons)).thenReturn(medicalRecords);
        int expectedAge = LocalDate.now().getYear() - 2000;
        mockMvc.perform(get("/fire?address=" + address))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(String.valueOf(expectedAge))));
    }

    @Test
    void getAllPersonsAndStationByAddressUnknownTest() throws Exception {
        String address = "address";
        when(fireStationService.getByAddress(address)).thenReturn(null);
        mockMvc.perform(get("/fire?address=" + address))
                .andExpect(status().isNotFound());
    }
}
