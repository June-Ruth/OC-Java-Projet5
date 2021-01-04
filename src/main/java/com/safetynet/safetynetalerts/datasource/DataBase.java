package com.safetynet.safetynetalerts.datasource;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.safetynet.safetynetalerts.model.FireStation;
import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.model.Person;

import java.util.Set;

public class DataBase {
    /**
     * List to use as a DB Table with all Persons inside.
     * @see Person
     */
    private Set<Person> persons;
    /**
     * List to use as a DB Table with all medical records inside.
     * @see MedicalRecord
     */
    private Set<MedicalRecord> medicalRecords;
    /**
     * List to use as a DB Table with all FireStations inside.
     * @see FireStation
     */
    private Set<FireStation> fireStations;

    /**
     * Constructor with JsonCreator to deserialize Json information.
     * @param pPersons as List
     * @param pMedicalRecords as List
     * @param pFireStations as List
     */
    @JsonCreator
    DataBase(@JsonProperty("persons")
             final Set<Person> pPersons,
             @JsonProperty("medicalrecords")
             final Set<MedicalRecord> pMedicalRecords,
             @JsonProperty("firestations")
             final Set<FireStation> pFireStations) {
        persons = pPersons;
        medicalRecords = pMedicalRecords;
        fireStations = pFireStations;
    }

    /**
     * Getter.
     * @return persons as List
     */
    public Set<Person> getPersons() {
        return persons;
    }

    /**
     * Setter.
     * @param pPersons as List
     */
    public void setPersons(final Set<Person> pPersons) {
        persons = pPersons;
    }

    /**
     * Getter.
     * @return medicalrecords as List.
     */
    public Set<MedicalRecord> getMedicalRecords() {
        return medicalRecords;
    }

    /**
     * Setter.
     * @param pMedicalRecords as List
     */
    public void setMedicalRecords(final Set<MedicalRecord> pMedicalRecords) {
        medicalRecords = pMedicalRecords;
    }

    /**
     * Getter.
     * @return fireStations as List
     */
    public Set<FireStation> getFireStations() {

        return fireStations;
    }

    /**
     * Setter.
     * @param pFireStations as List
     */
    public void setFireStations(final Set<FireStation> pFireStations) {
        fireStations = pFireStations;
    }
}
