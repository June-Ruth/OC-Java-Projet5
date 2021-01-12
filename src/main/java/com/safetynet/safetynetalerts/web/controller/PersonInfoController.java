package com.safetynet.safetynetalerts.web.controller;

import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.model.dto.PersonInfoDTO;
import com.safetynet.safetynetalerts.service.MedicalRecordService;
import com.safetynet.safetynetalerts.service.PersonService;
import com.safetynet.safetynetalerts.util.DtoConverter;
import com.safetynet.safetynetalerts.web.exceptions.NotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class PersonInfoController {
    /**
     * @see Logger
     */
    private static final Logger LOGGER = LogManager.getLogger(PersonInfoController.class);
    /**
     * @see PersonService
     */
    private PersonService personService;
    /**
     * @see MedicalRecord
     */
    private MedicalRecordService medicalRecordService;

    /**
     * Public constructor.
     * @param pPersonService not null
     * @param pMedicalRecordService not null
     */
    public PersonInfoController(final PersonService pPersonService,
                                final MedicalRecordService pMedicalRecordService) {
        Objects.requireNonNull(pPersonService);
        Objects.requireNonNull(pMedicalRecordService);
        personService = pPersonService;
        medicalRecordService = pMedicalRecordService;
    }

    /**
     * Get all information about a person.
     * @param firstName .
     * @param lastName .
     * @return person's info
     */
    @GetMapping(value = "/personInfo")
    public PersonInfoDTO getAllInfoByFirstNameAndLastName(
            @RequestParam(value = "firstName") final String firstName,
            @RequestParam(value = "lastName") final String lastName) {
        Person person = personService.getByFirstNameAndLastName(firstName, lastName);
        if(person != null) {
            MedicalRecord medicalRecord = medicalRecordService.getMedicalRecord(person);
            LOGGER.info("Find all info for " + firstName + " " + lastName);
            return DtoConverter.convertToPersonFullInfoSTO(person, medicalRecord);
        } else {
            RuntimeException e = new NotFoundException("Person named " + firstName + " " + lastName + "was not found");
            LOGGER.error(e);
            throw e;
        }

    }

}
