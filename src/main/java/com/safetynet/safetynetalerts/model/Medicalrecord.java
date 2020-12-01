package com.safetynet.safetynetalerts.model;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Data
public class Medicalrecord {
    private String firstName;
    private String lastName;
    private LocalDate birthdate;
    private Map<String, Integer> medications;
    private List<String> allergies;
}
