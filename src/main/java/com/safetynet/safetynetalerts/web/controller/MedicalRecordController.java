package com.safetynet.safetynetalerts.web.controller;

import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.service.MedicalRecordService;
import com.safetynet.safetynetalerts.web.exceptions.AlreadyExistingException;
import com.safetynet.safetynetalerts.web.exceptions.NotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Objects;
import java.util.Set;

@RestController
public class MedicalRecordController {
    /**
     * @see Logger
     */
    private static final Logger LOGGER = LogManager.getLogger(MedicalRecordController.class);
    /**
     * @see MedicalRecordService
     */
    private MedicalRecordService medicalRecordService;

    /**
     * Public constructor for MedicalRecordController.
     * Requires non null MedicalRecordService.
     * @param pMedicalRecordService not null
     */
    public MedicalRecordController(
            final MedicalRecordService pMedicalRecordService) {
        Objects.requireNonNull(pMedicalRecordService);
        medicalRecordService = pMedicalRecordService;
    }

    /**
     * Get all Entities for Medical Record.
     * Http Status will be 200 - OK if we can access to all entities,
     * even if list is empty.
     * @return Set of all the medical Record
     */
    @GetMapping(value = "/medicalrecords")
    public Set<MedicalRecord> getMedicalRecords() {
        Set<MedicalRecord> result = medicalRecordService.getAllMedicalRecords();
        LOGGER.info("Get all medical records : {}", result);
        return result;
    }

    /**
     * Save a new Medical Record.
     * Duplicate are not allowed.
     * If the arguments fields of the medical record to add are not correct,
     * HTTP Status will be 400 - Bad Request.
     * If Medical Record first name and last name are already existing
     * (= duplicate), then throw AlreadyExistingException
     * and HTTP Status will be 409 - Conflict.
     * @param medicalRecord full filled to save
     * @return 201 - Created if the new medical record is correctly saved
     */
    @PostMapping(value = "/medicalrecord")
    public ResponseEntity<Void> createMedicalRecord(@Valid
            @RequestBody final MedicalRecord medicalRecord) {
        if (medicalRecordService.saveMedicalRecord(medicalRecord)) {
            LOGGER.info("Medical Record was saved.");
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{firstName}{lastName}")
                    .buildAndExpand(medicalRecord.getFirstName(),
                            medicalRecord.getLastName())
                    .toUri();
            return ResponseEntity.created(location).build();
        } else {
            RuntimeException e = new AlreadyExistingException("The medical record for "
                    + medicalRecord.getFirstName()
                    + " " + medicalRecord.getLastName()
                    + " is already existing. Cannot add it.");
            LOGGER.error(e);
            throw e;

        }
    }

    /**
     * Update an existing Medical Record depending on
     * the first name and last name of the person concerned.
     * It's not possible to update first name and last name.
     * If the arguments fields of the medical record to update are not correct,
     * HTTP Status will be 400 - Bad Request.
     * If Medical Record first name and last name is not existing,
     * then throw NotFoundException
     * and HTTP Status will be 404 - Not Found.
     * @param medicalRecord to update full filled
     * @return 200 - OK if the medical record is correctly updated
     */
    @PutMapping(value = "/medicalrecord")
    public ResponseEntity<Void> updateMedicalRecord(@Valid
            @RequestBody final MedicalRecord medicalRecord) {
        if (medicalRecordService.updateMedicalRecord(medicalRecord)) {
            LOGGER.info("Medical Record was updated.");
            return ResponseEntity.ok().build();
        } else {
            RuntimeException e = new NotFoundException("The medical record of "
                    + medicalRecord.getFirstName()
                    + " " + medicalRecord.getLastName()
                    + ", is not existing. Cannot update it.");
            LOGGER.error(e);
            throw e;
        }
    }

    /**
     * Delete an existing Medical Record.
     * If Medical Record first name and last name is not existing,
     * then throw NotFoundException and HTTP Status will be 404 - Not Found.
     * @param firstName of the person's medical record to delete
     * @param lastName of the person's medical record to delete
     * @return 200 - OK if the medical record is correctly deleted
     */
    @DeleteMapping(value = "/medicalrecord/{firstName}{lastName}")
    public ResponseEntity<Void> deleteMedicalRecord(
            @PathVariable final String firstName,
            @PathVariable final String lastName) {
        if (medicalRecordService.deleteMedicalRecord(firstName, lastName)) {
            LOGGER.info("Medical Record was deleted.");
            return ResponseEntity.ok().build();
        } else {
            RuntimeException e = new NotFoundException("The medical record of "
                    + firstName + " " + lastName
                    + ", is not existing. Cannot delete it.");
            LOGGER.error(e);
            throw e;
        }
    }
}
