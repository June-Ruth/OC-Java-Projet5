package com.safetynet.safetynetalerts.web.controller;

import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.service.PersonService;
import com.safetynet.safetynetalerts.web.exceptions.AlreadyExistingException;
import com.safetynet.safetynetalerts.web.exceptions.InvalidArgumentsException;
import com.safetynet.safetynetalerts.web.exceptions.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Objects;
import java.util.Set;

@RestController
public class PersonController {

    /**
     * @see PersonService
     */
    private PersonService personService;

    /**
     * Public constructor for PersonController.
     * Requires non null PersonService.
     * @param pPersonService not null
     */
    public PersonController(final PersonService pPersonService) {
        Objects.requireNonNull(pPersonService);
        personService = pPersonService;
    }

    /**
     * Get all entities for Person
     * Http Status will be 200 - OK
     * if we can access to all entities, even if list is empty.
     * @return Set of all persons
     */
    @GetMapping(value = "/person")
    public Set<Person> getPersons() {
        return personService.getPersons();
    }

    /**
     * Save a new Person.
     * Duplicate are not allowed.
     * If the arguments fields of the person to add are not correct,
     * then throw InvalidArgumentsException
     * and HTTP Status will be 400 - Bad Request.
     * If Person first name and last name are already existing (= duplicate),
     * then throw AlreadyExistingException
     * and HTTP Status will be 409 - Conflict.
     * @param person full filled to save
     * @return 201 - Created if the new Person is correctly saved
     */
    @PostMapping(value = "/person")
    public ResponseEntity<Void> createPerson(@RequestBody final Person person) {
        //TODO : voir comment compléter pour que vérifie
        // tous les champs et pas juste les champs servant
        // à l'identification (sans Hibernate Validator)
        if (person.getLastName() == null || person.getFirstName() == null) {
            throw new InvalidArgumentsException(
                    "First name or/and last name is null. Cannot add it.");
        }

        if (personService.savePerson(person)) {
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{firstName}{lastName}")
                    .buildAndExpand(person.getFirstName(), person.getLastName())
                    .toUri();
            return ResponseEntity.created(location).build();
        } else {
            throw new AlreadyExistingException("The following person "
                    + person.getFirstName() + " " + person.getLastName()
                    + " is already existing. Cannot add it.");

        }
    }

    /**
     * Update an existing Person depending on its first name and last name.
     * It's not possible to update first name and last name.
     * If the arguments fields of the person to update are not correct,
     * then throw InvalidArgumentsException
     * and HTTP Status will be 400 - Bad Request.
     * If Person first name and last name is not existing,
     * then throw NotFoundException
     * and HTTP Status will be 404 - Not Found.
     * @param person to update full filled
     * @return 200 - OK if the person is correctly updated
     */
    @PutMapping(value = "/person")
    public ResponseEntity<Void> updatePerson(@RequestBody final Person person) {
        //TODO : voir comment compléter pour que vérifie tous les
        // champs et pas juste les champs servant
        // à l'identification (sans Hibernate Validator)
        if (person.getLastName() == null || person.getFirstName() == null) {
            throw new InvalidArgumentsException(
                    "First name or/and last name is null. Cannot update it.");
        }

        if (personService.updatePerson(person)) {
            return ResponseEntity.ok().build();
        } else {
            throw new NotFoundException("The following person "
                    + person.getFirstName() + " " + person.getLastName()
                    + ", is not existing. Cannot update it.");
        }
    }

    /**
     * Delete an existing Person.
     * If person first name and last name is not existing,
     * then throw NotFoundException and HTTP Status will be 404 - Not Found.
     * @param firstName of the person to delete
     * @param lastName of the person to delete
     * @return 200 - OK if the person is correctly deleted
     */
    @DeleteMapping(value = "/person/{firstName}{lastName}")
    public ResponseEntity<Void> deletePerson(
            @PathVariable final String firstName,
            @PathVariable final String lastName) {
        if (personService.deletePerson(firstName, lastName)) {
            return ResponseEntity.ok().build();
        } else {
            throw new NotFoundException("The following person "
                    + firstName + " " + lastName
                    + ", is not existing. Cannot delete it.");
        }
    }

}
