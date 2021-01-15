package com.safetynet.safetynetalerts.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.safetynetalerts.datasource.DataBaseTestService;
import com.safetynet.safetynetalerts.model.MedicalRecord;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MedicalRecordIT {

    @Autowired
    public MockMvc mockMvc;

    private static DataBaseTestService dataBaseTestService = new DataBaseTestService();

    @AfterEach
    void tearDown() {
        dataBaseTestService.clearDBTest();
        dataBaseTestService.restoreDBTest();
    }

    @Test
    void getMedicalRecordsIT() throws Exception {
        mockMvc.perform(get("/medicalrecords")).
                andExpect(status().isOk());
    }

    @Test
    void postMedicalRecordIT() throws Exception {
        MedicalRecord medicalRecord = new MedicalRecord("test", "test", LocalDate.of(1994, 1, 1), null, null);
        mockMvc.perform(post("/medicalrecord")
                .content(new ObjectMapper().writeValueAsString(medicalRecord))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void putMedicalRecordIT() throws Exception {
        MedicalRecord medicalRecord = new MedicalRecord("firstName", "lastName", LocalDate.of(1194, 6, 15), null, null);
        mockMvc.perform(put("/medicalrecord")
                .content(new ObjectMapper().writeValueAsString(medicalRecord))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void deleteMedicalRecordIT() throws Exception {
        String firstName = "firstName";
        String lastName = "lastName";
        mockMvc.perform(delete("/medicalrecord/{firstName}_{lastName}", firstName, lastName))
                .andExpect(status().isOk());
    }
}
