package com.safetynet.safetynetalerts.controller;

import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.service.PersonService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@RestController
public class PersonController {

    private PersonService personService;

    public PersonController(PersonService pPersonService) {
        Objects.requireNonNull(pPersonService);
        personService = pPersonService;
    }

    /**
     * Read - Get all entities for Person
     * @return List of all persons
     */
    @GetMapping(value = "/person")
    public Set<Person> getPersons() {
        //GET mapping, HTTP status si réussit : 200 OK
        return null;
    }

    /**
     * Create - save a new Person.
     * @param person full filled
     */
    @PostMapping(value = "/person")
    public void createPerson(Person person) {
        //POST mapping, HTTP status si réussi : 201 created
        //409 Conflict si crée un mapping déjà existant
        //400 Bad Resquest si param invalide
        personService.savePerson(person);
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
        //PUT mapping, HTTP status si réussi : 200 OK
        //404 Not Found si n'existe pas
        //400 Bad Resquest si param invalide
        return null;
    }

    /**
     * Delete an existing Person.
     * @param firstName of Person to delete
     * @param lastName of Person to delete
     */
    //TODO ; supprimer une personne : utiliser le nom et prénom comme identifiant unique
    public void deletePerson(String firstName, String lastName) {
        //DELETE mapping, HTTP status si réussi : 200 OK si action confirmée et que le message de réponse inclut une représentation décrivant le status
        //404 Not Found si n'existe pas
    }

}
