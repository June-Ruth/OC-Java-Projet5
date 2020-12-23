package com.safetynet.safetynetalerts.controller;

import com.safetynet.safetynetalerts.service.MedicalRecordService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

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
        mockMvc.perform(post("/medicalrecord"))
                .andExpect(status().isCreated());
    }

    @Test
    void createMedicalRecordAlreadyExistingTest() throws Exception {
        mockMvc.perform(post("/medicalrecord"))
                .andExpect(status().isConflict());
    }

    @Test
    void createMedicalRecordWithInvalidArgumentsTest() throws Exception {
        mockMvc.perform(post("/medicalrecord"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void updateMedicalRecordExistingTest() throws Exception {
        mockMvc.perform(put("/medicalrecord"))
                .andExpect(status().isOk());
    }

    @Test
    void updateMedicalRecordUnknownTest() throws Exception {
        mockMvc.perform(put("/medicalrecord"))
                .andExpect(status().isNotFound());
    }

    @Test
    void updateMedicalRecordWithInvalidArgumentsTest() throws Exception {
        mockMvc.perform(put("/medicalrecord"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deleteMedicalRecordExistingTest() throws Exception {
        mockMvc.perform(delete("/medicalrecord"))
                .andExpect(status().isOk());
    }

    @Test
    void deleteMedicalRecordUnknownTest() throws Exception {
        mockMvc.perform(delete("/medicalrecord"))
                .andExpect(status().isNotFound());
    }
}
