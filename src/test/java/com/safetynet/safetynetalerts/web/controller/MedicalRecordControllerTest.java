package com.safetynet.safetynetalerts.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.service.MedicalRecordService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = MedicalRecordController.class)
class MedicalRecordControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MedicalRecordService medicalRecordService;

    @Test
    void getMedicalRecordsTest() throws Exception {
        mockMvc.perform(get("/medicalrecord")).
                andExpect(status().isOk());
    }

    @Test
    void createMedicalRecordNewTest() throws Exception {
        MedicalRecord medicalRecord = new MedicalRecord("test", "test", LocalDate.of(1994, 1, 1), null, null);
        when(medicalRecordService.saveMedicalRecord(any(MedicalRecord.class))).thenReturn(true);
        mockMvc.perform(post("/medicalrecord")
                .content(new ObjectMapper().writeValueAsString(medicalRecord))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void createMedicalRecordAlreadyExistingTest() throws Exception {
        MedicalRecord medicalRecord = new MedicalRecord("firstName", "lastName", LocalDate.of(1194, 6, 15), null, null);
        when(medicalRecordService.saveMedicalRecord(any(MedicalRecord.class))).thenReturn(false);
        mockMvc.perform(post("/medicalrecord")
                .content(new ObjectMapper().writeValueAsString(medicalRecord))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict());
    }

    @Test
    void createMedicalRecordWithInvalidArgumentsTest() throws Exception {
        MedicalRecord medicalRecord = new MedicalRecord(null, null, LocalDate.now(), null, null);
        mockMvc.perform(post("/medicalrecord")
                .content(new ObjectMapper().writeValueAsString(medicalRecord))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void updateMedicalRecordExistingTest() throws Exception {
        MedicalRecord medicalRecord = new MedicalRecord("firstName", "lastName", LocalDate.of(1194, 6, 15), null, null);
        when(medicalRecordService.updateMedicalRecord(any(MedicalRecord.class))).thenReturn(true);
        mockMvc.perform(put("/medicalrecord")
                .content(new ObjectMapper().writeValueAsString(medicalRecord))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void updateMedicalRecordUnknownTest() throws Exception {
        MedicalRecord medicalRecord= new MedicalRecord("test", "test", LocalDate.now(), null, null);
        when(medicalRecordService.updateMedicalRecord(any(MedicalRecord.class))).thenReturn(false);
        mockMvc.perform(put("/medicalrecord")
                .content(new ObjectMapper().writeValueAsString(medicalRecord))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void updateMedicalRecordWithInvalidArgumentsTest() throws Exception {
        MedicalRecord medicalRecord = new MedicalRecord(null, null, LocalDate.now(), null, null);
        mockMvc.perform(put("/medicalrecord")
                .content(new ObjectMapper().writeValueAsString(medicalRecord))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deleteMedicalRecordExistingTest() throws Exception {
        String firstName = "firstName";
        String lastName = "lastName";
        when(medicalRecordService.deleteMedicalRecord(any(String.class), any(String.class))).thenReturn(true);
        mockMvc.perform(delete("/medicalrecord/{firstName}{lastName}", firstName, lastName))
                .andExpect(status().isOk());
    }

    @Test
    void deleteMedicalRecordUnknownTest() throws Exception {
        String firstName = "test";
        String lastName = "test";
        when(medicalRecordService.deleteMedicalRecord(any(String.class), any(String.class))).thenReturn(false);
        mockMvc.perform(delete("/medicalrecord/{firstName}{lastName}", firstName, lastName))
                .andExpect(status().isNotFound());
    }
}
