package com.safetynet.safetynetalerts.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
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

    /**
     * Public constructor for One Medical Record.
     * with JsonCreator for serialization.
     * @param pFirstName of person concerned
     * @param pLastName of person concerned
     * @param pBirthdate of person concerned
     * @param pMedications as list with medication name and dosage
     * @param pAllergies as list
     */
    @JsonCreator
    public MedicalRecord(@JsonProperty("firstName") final String pFirstName,
                         @JsonProperty("lastName") final String pLastName,
                         @JsonDeserialize(using =
                                 LocalDateDeserializer.class)
                         @JsonSerialize(using =
                                 LocalDateSerializer.class)
                         @JsonFormat(shape =
                                 JsonFormat.Shape.STRING,
                                 pattern = "MM/dd/yyyy")
                         @JsonProperty("birthdate")
                             final LocalDate pBirthdate,
                         @JsonProperty("medications")
                             @Nullable final List<String> pMedications,
                         @JsonProperty("allergies")
                             @Nullable final List<String> pAllergies) {
        firstName = pFirstName;
        lastName = pLastName;
        birthdate = pBirthdate;
        medications = pMedications;
        allergies = pAllergies;
    }

    /**
     * Getter.
     * @return firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Setter.
     * @param pFirstName .
     */
    public void setFirstName(final String pFirstName) {
        this.firstName = pFirstName;
    }

    /**
     * Getter.
     * @return lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Setter.
     * @param pLastName .
     */
    public void setLastName(final String pLastName) {
        this.lastName = pLastName;
    }

    /**
     * Getter.
     * @return birthdate
     */
    public LocalDate getBirthdate() {
        return birthdate;
    }

    /**
     * Setter.
     * @param pBirthdate .
     */
    public void setBirthdate(final LocalDate pBirthdate) {
        this.birthdate = pBirthdate;
    }

    /**
     * Getter Nullable.
     * @return medications as List.
     */
    @Nullable
    public List<String> getMedications() {
        return medications;
    }

    /**
     * Setter Nullable.
     * @param pMedications as List.
     */
    public void setMedications(@Nullable final List<String> pMedications) {
        this.medications = pMedications;
    }

    /**
     * Getter Nullable.
     * @return allergies as List.
     */
    @Nullable
    public List<String> getAllergies() {
        return allergies;
    }

    /**
     * Setter Nullable.
     * @param pAllergies .
     */
    public void setAllergies(@Nullable final List<String> pAllergies) {
        this.allergies = pAllergies;
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
