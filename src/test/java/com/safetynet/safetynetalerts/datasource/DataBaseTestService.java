package com.safetynet.safetynetalerts.datasource;

import com.safetynet.safetynetalerts.model.FireStation;
import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.model.Person;

import java.time.LocalDate;
import java.util.*;

public class DataBaseTestService {

    private DataBase dataBase;

    public void clearDBTest() {
        dataBase = DataBaseManager.INSTANCE.getDataBase();
        dataBase.getPersons().clear();
        dataBase.getMedicalRecords().clear();
        dataBase.getFireStations().clear();
    }

    public void restoreDBTest() {
        dataBase = DataBaseManager.INSTANCE.getDataBase();

        Set<Person> persons = dataBase.getPersons();
        Set<FireStation> fireStations = dataBase.getFireStations();
        Set<MedicalRecord> medicalRecords = dataBase.getMedicalRecords();

        Person person1 = new Person("firstName", "lastName", "address", "city", 123, "123-456-7890", "mail@email.com");
        Person person2 = new Person("firstName2", "lastName2", "address2", "city", 123, "123-456-7890", "mail@email.com");

        FireStation fireStation1 = new FireStation("address", 1);
        FireStation fireStation2 = new FireStation("address2", 2);

        LocalDate birthdate1 = LocalDate.of(1994, 6, 15);
        LocalDate birthdate2 = LocalDate.of(2012, 6, 15);
        List<String> medicationList = new ArrayList<>();
        medicationList.add("medication1:300mg");
        medicationList.add("medication2:100mg");
        List<String> allergieList = new ArrayList<>();
        allergieList.add("allergie1");
        allergieList.add("allergie2");
        allergieList.add("allergie3");
        MedicalRecord medicalRecord1 = new MedicalRecord("firstName", "lastName", birthdate1, medicationList, allergieList);
        MedicalRecord medicalRecord2 = new MedicalRecord("firstName2", "lastName2", birthdate2, null, null);

        persons.add(person1);
        persons.add(person2);
        fireStations.add(fireStation1);
        fireStations.add(fireStation2);
        medicalRecords.add(medicalRecord1);
        medicalRecords.add(medicalRecord2);
    }
}
