package com.safetynet.safetynetalerts.datasource;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.safetynet.safetynetalerts.model.FireStation;
import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.model.Person;

import java.util.List;

public class DataBase {

    List<Person> persons;
    List<MedicalRecord> medicalRecords;
    List<FireStation> fireStations;

    @JsonCreator
    public DataBase(@JsonProperty("persons") List<Person> persons,
                    @JsonProperty("medicalrecords") List<MedicalRecord> medicalRecords,
                    @JsonProperty("firestations") List<FireStation> fireStations) {
        this.persons = persons;
        this.medicalRecords = medicalRecords;
        this.fireStations = fireStations;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    public List<MedicalRecord> getMedicalRecords() {
        return medicalRecords;
    }

    public void setMedicalRecords(List<MedicalRecord> medicalRecords) {
        this.medicalRecords = medicalRecords;
    }

    public List<FireStation> getFireStations() {
        return fireStations;
    }

    public void setFireStations(List<FireStation> fireStations) {
        this.fireStations = fireStations;
    }
}
