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

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@JsonTypeName("medicalrecords")
@JsonIgnoreProperties(ignoreUnknown = true)
public class MedicalRecord {
    /**
     * First name.
     */
    @NotBlank(message = "First name is mandatory")
    private String firstName;
    /**
     * Last name.
     */
    @NotBlank(message = "Last name is mandatory")
    private String lastName;
    /**
     * BirthDate.
     */
    @Past
    private LocalDate birthdate;
    /**
     * List of medications with value of dosage.
     * Medication is the key, dosage is the value in mg.
     */
    private List<String> medications;
    /**
     * List of known allergies.
     */
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
                             final List<String> pMedications,
                         @JsonProperty("allergies")
                             final List<String> pAllergies) {
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
        firstName = pFirstName;
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
        lastName = pLastName;
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
        birthdate = pBirthdate;
    }

    /**
     * Getter Nullable.
     * @return medications as List.
     */
    public List<String> getMedications() {
        return medications;
    }

    /**
     * Setter Nullable.
     * @param pMedications as List.
     */
    public void setMedications(final List<String> pMedications) {
        medications = pMedications;
    }

    /**
     * Getter Nullable.
     * @return allergies as List.
     */
    public List<String> getAllergies() {
        return allergies;
    }

    /**
     * Setter Nullable.
     * @param pAllergies .
     */
    public void setAllergies(final List<String> pAllergies) {
        allergies = pAllergies;
    }

    /**
     * Equality is checked with firstName and lastName.
     * @param o as Medical record
     * @return true if it's equal
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MedicalRecord that = (MedicalRecord) o;
        return firstName.equals(that.firstName)
                && lastName.equals(that.lastName);
    }

    /**
     * hash by firstName and lastName.
     * @return hash
     */
    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }
}
