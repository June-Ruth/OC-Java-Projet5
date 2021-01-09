package com.safetynet.safetynetalerts.web.controller;

import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.model.dto.ChildInfoDTO;
import com.safetynet.safetynetalerts.model.dto.PersonFullInfoDTO;
import com.safetynet.safetynetalerts.service.PersonService;
import com.safetynet.safetynetalerts.web.exceptions.AlreadyExistingException;
import com.safetynet.safetynetalerts.web.exceptions.NotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Objects;
import java.util.Set;

@RestController
public class PersonController {
    /**
     * @see Logger
     */
    private static final Logger LOGGER = LogManager.getLogger(PersonController.class);
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
    @GetMapping(value = "/persons")
    public Set<Person> getPersons() {
        Set<Person> result = personService.getPersons();
        LOGGER.info("Get all persons : {}", result);
        return result ;
    }

    /**
     * Save a new Person.
     * Duplicate are not allowed.
     * If the arguments fields of the person to add are not correct,
     * HTTP Status will be 400 - Bad Request.
     * If Person first name and last name are already existing (= duplicate),
     * then throw AlreadyExistingException
     * and HTTP Status will be 409 - Conflict.
     * @param person full filled to save
     * @return 201 - Created if the new Person is correctly saved
     */
    @PostMapping(value = "/person")
    public ResponseEntity<Void> createPerson(@Valid
            @RequestBody final Person person) {
        if (personService.savePerson(person)) {
            LOGGER.info("New person was saved.");
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{firstName}{lastName}")
                    .buildAndExpand(person.getFirstName(), person.getLastName())
                    .toUri();
            return ResponseEntity.created(location).build();
        } else {
            RuntimeException e = new AlreadyExistingException("The following person "
                    + person.getFirstName() + " " + person.getLastName()
                    + " is already existing. Cannot add it.");
            LOGGER.error(e);
            throw e;

        }
    }

    /**
     * Update an existing Person depending on its first name and last name.
     * It's not possible to update first name and last name.
     * If the arguments fields of the person to update are not correct,
     * HTTP Status will be 400 - Bad Request.
     * If Person first name and last name is not existing,
     * then throw NotFoundException
     * and HTTP Status will be 404 - Not Found.
     * @param person to update full filled
     * @return 200 - OK if the person is correctly updated
     */
    @PutMapping(value = "/person")
    public ResponseEntity<Void> updatePerson(@Valid
            @RequestBody final Person person) {
        if (personService.updatePerson(person)) {
            LOGGER.info("New person was saved.");
            return ResponseEntity.ok().build();
        } else {
            RuntimeException e = new NotFoundException("The following person "
                    + person.getFirstName() + " " + person.getLastName()
                    + ", is not existing. Cannot update it.");
            LOGGER.error(e);
            throw e;
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
            LOGGER.info(firstName + lastName + " was deleted.");
            return ResponseEntity.ok().build();
        } else {
            RuntimeException e = new NotFoundException("The following person "
                    + firstName + " " + lastName
                    + ", is not existing. Cannot delete it.");
            LOGGER.error(e);
            throw e;
        }
    }

    /**
     * Get all Email of inhabitants in the city.
     * If city is not existing,
     * then throw NotFoundException and HTTP Status will be 404 - Not Found.
     * @param city searched
     * @return set of all email in city
     */
    @GetMapping(value = "/communityEmail")
    public Set<String> getAllEmailInCity(@RequestParam(value = "city") final String city) {
        Set<String> result = personService.getAllEmailInCity(city);
        if (result != null) {
            LOGGER.info("Emails in the city " + city + " were found.");
            return result;
        } else {
            RuntimeException e = new NotFoundException("The following city "
                    + city + " has not referenced inhabitants.");
            LOGGER.error(e);
            throw e;
        }
    }

    /**
     * Get all children at the specified address with other inhabitant.
     * @param address .
     * @return list of children
     */
    @GetMapping(value = "/childAlert")
    public Set<ChildInfoDTO> getAllChildrenByAddress(@RequestParam(value = "address") final String address) {
        //TODO
        personService.getAllChildrenByAddress(address);
        return null;
    }

    /**
     * Get all information about a person.
     * @param firstName .
     * @param lastName .
     * @return person's info
     */
    @GetMapping(value = "personInfo")
    public PersonFullInfoDTO getAllInfoByFirstNameAndLastName(
            @RequestParam(value = "firstName") final String firstName,
            @RequestParam(value = "lastName") final String lastName) {
        //TODO
        personService.getAllInfoByFirstNameAndLastName(firstName, lastName);
        return null;
    }
}
