package com.safetynet.safetynetalerts.controller;

import com.safetynet.safetynetalerts.model.Person;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
//@ResquestMapping("/persons")
public class PersonController {

    /**
     * Read - Get all entities for Person
     * @return //TODO
     */
    //TODO ; visualiser toutes les données
    public List<Person> getPersons() {
        return null;
    }

    /**
     * Create - save a new Person.
     * @param person full filled
     * @return //TODO
     */
    //TODO : ajouter une nouvelle personne
    public Person createPerson(Person person) {
        return null;
    }

    /**
     * Update an existing Person depending of its name.
     * It's not possible to update first name and last name.
     * @param firstName of the Person to update
     * @param lastName of the Person to update
     * @param person - Person object updated
     * @return
     */
    //TODO : mettre à jour une personne : supposer que le nom et prénom ne change pas
    public Person updatePerson(String firstName, String lastName, Person person) {
        return null;
    }

    /**
     * Delete an existing Person.
     * @param firstName of Person to delete
     * @param lastName of Person to delete
     */
    //TODO ; supprimer une personne : utiliser le nom et prénom comme identifiant unique
    public void deletePerson(String firstName, String lastName) {

    }

}
