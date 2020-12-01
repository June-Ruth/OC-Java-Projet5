package com.safetynet.safetynetalerts.model;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Data
public class Medicalrecord {
    /**
     * First name.
     */
    private String firstName;
    /**
     * Last name.
     */
    private String lastName;
    /**
     * BirthDate.
     */
    private LocalDate birthdate;
    /**
     * List of medications with value of dosage.
     * Medication is the key, dosage is the value.
     */
    private Map<String, Integer> medications;
    /**
     * List of known allergies.
     */
    private List<String> allergies;
}
