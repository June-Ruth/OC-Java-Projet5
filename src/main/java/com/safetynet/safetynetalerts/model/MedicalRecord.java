package com.safetynet.safetynetalerts.model;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import org.springframework.lang.Nullable;

import java.time.LocalDate;
import java.util.List;

@JsonTypeName("medicalrecords")
@JsonIgnoreProperties(ignoreUnknown = true)
public class MedicalRecord {
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
    // @JsonFormat pour définir le format lors de la sérialisation.
    private LocalDate birthdate;
    /**
     * List of medications with value of dosage.
     * Medication is the key, dosage is the value in mg.
     */
    @Nullable
    private List<String> medications;
    /**
     * List of known allergies.
     */
    @Nullable
    private List<String> allergies;

    @JsonCreator
    public MedicalRecord(@JsonProperty("firstName") String firstName,
                         @JsonProperty("lastName") String lastName,
                         @JsonDeserialize(using = LocalDateDeserializer.class)
                         @JsonSerialize(using = LocalDateSerializer.class)
                         @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy")
                         @JsonProperty("birthdate") LocalDate birthdate,
                         @JsonProperty("medications") @Nullable List<String> medications,
                         @JsonProperty("allergies") @Nullable List<String> allergies) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.medications = medications;
        this.allergies = allergies;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    @Nullable
    public List<String> getMedications() {
        return medications;
    }

    public void setMedications(@Nullable List<String> medications) {
        this.medications = medications;
    }

    @Nullable
    public List<String> getAllergies() {
        return allergies;
    }

    public void setAllergies(@Nullable List<String> allergies) {
        this.allergies = allergies;
    }

    /*
     * Set the list of medications and the dosage.
     * @param medicationName concerned.
     * @param value for the medication concerned.
     *
    @JsonSetter
    public void setMedications (String medicationName, String value) {
        if (medications == null) medications = new HashMap<>();
        medications.put(medicationName, value);
    }

    /**
     * Set the list of allergies.
     * @param allergieName concerned.
     *
    @JsonSetter
    public void setAllergies (String allergieName) {
        if(allergies == null) allergies = new ArrayList<>();
        allergies.add(allergieName);
    }*/
}
