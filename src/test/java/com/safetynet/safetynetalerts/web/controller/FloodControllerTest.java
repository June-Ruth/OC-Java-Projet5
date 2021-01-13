package com.safetynet.safetynetalerts.web.controller;

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
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = FloodController.class)
class FloodControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FireStationService fireStationService;

    @MockBean
    private PersonService personService;

    @MockBean
    private MedicalRecordService medicalRecordService;

    @Test
    void getAllFloodsByStationNumberExisitngTest() throws Exception {
        int stationNumber = 1;
        Set<String> addressByStation = new HashSet<>();
        addressByStation.add("address");
        Set<Person> personsAtAddress = new HashSet<>();
        personsAtAddress.add(new Person("test", "test", "address", "test" , 123, "test", "mail"));
        personsAtAddress.add(new Person("test1", "test", "address", "test" , 123, "test", "mail2"));
        Set<MedicalRecord> medicalRecords = new HashSet<>();
        medicalRecords.add(new MedicalRecord("test", "test", LocalDate.of(1990, 1, 1),null, null ));
        medicalRecords.add(new MedicalRecord("test1", "test", LocalDate.of(2000, 1, 1),null, null ));

        when(fireStationService.getAllAddressByStationNumber(stationNumber)).thenReturn(addressByStation);
        when(personService.getAllByAddress(anyString())).thenReturn(personsAtAddress);
        when(medicalRecordService.getAllMedicalRecordsByPersonList(personsAtAddress)).thenReturn(medicalRecords);

        int expectedAge = LocalDate.now().getYear() - 2000;

        mockMvc.perform(get("/flood/stations?station=" + stationNumber))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(String.valueOf(expectedAge))));
    }

    @Test
    void getAllFloodsByStationNumberUnknownTest() throws Exception {
        int stationNumber = 5;
        when(fireStationService.getAllAddressByStationNumber(stationNumber)).thenReturn(null);
        mockMvc.perform(get("/flood/stations?station=" + stationNumber))
                .andExpect(status().isNotFound());
    }
}
