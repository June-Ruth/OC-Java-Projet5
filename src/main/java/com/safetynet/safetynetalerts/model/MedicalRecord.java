package com.safetynet.safetynetalerts.model;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import org.springframework.lang.Nullable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Data
@Entity
public class MedicalRecord {
    /**
     * Id of medical record file.
     */
    @JacksonInject
    @Id
    @GeneratedValue
    private int id;
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
    //@JsonDeserialize(using = CustomDateDeserializer.class) à créer.
    // @JsonFormat pour définir le format lors de la sérialisation
    private LocalDate birthdate;
    /**
     * List of medications with value of dosage.
     * Medication is the key, dosage is the value in mg.
     */
    @Nullable
    private Map<String, String> medications;
    /**
     * List of known allergies.
     */
    @Nullable
    private List<String> allergies;

    /**
     * Set the list of medications and the dosage.
     * @param medicationName concerned.
     * @param value for the medication concerned.
     */
    @JsonAnySetter
    public void setMedications (String medicationName, String value) {
        medications.put(medicationName, value);
    }

    /**
     * Set the list of allergies.
     * @param allergieName concerned.
     */
    @JsonAnySetter
    public void setAllergies (String allergieName) {
        allergies.add(allergieName);
    }

}
