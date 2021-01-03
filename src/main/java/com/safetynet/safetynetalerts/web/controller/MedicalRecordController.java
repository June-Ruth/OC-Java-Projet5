package com.safetynet.safetynetalerts.web.controller;

import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.service.MedicalRecordService;
import com.safetynet.safetynetalerts.web.exceptions.AlreadyExistingException;
import com.safetynet.safetynetalerts.web.exceptions.InvalidArgumentsException;
import com.safetynet.safetynetalerts.web.exceptions.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Objects;
import java.util.Set;

@RestController
public class MedicalRecordController {

    private MedicalRecordService medicalRecordService;

    public MedicalRecordController(MedicalRecordService pMedicalRecordService) {
        Objects.requireNonNull(pMedicalRecordService);
        medicalRecordService = pMedicalRecordService;
    }

    /**
     * Get all Entities for Medical Record.
     * Http Status will be 200 - OK if we can access to all entities, even if list is empty.
     * @return Set of all the medical Record
     */
    @GetMapping(value = "/medicalrecord")
    public Set<MedicalRecord> getMedicalRecords(){
        return medicalRecordService.getMedicalsRecords();
    }

    /**
     * Save a new Medical Record.
     * Duplicate are not allowed.
     * If the arguments fields of the medical record to add are not correct, then throw InvalidArgumentsException and HTTP Status will be 400 - Bad Request.
     * If Medical Record first name and last name are already existing (= duplicate), then throw AlreadyExistingException and HTTP Status will be 409 - Conflict.
     * @param medicalRecord full filled to save
     * @return 201 - Created if the new medical record is correctly saved
     */
    @PostMapping(value = "/medicalrecord")
    public ResponseEntity<Void> createMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
        //TODO : voir comment compléter pour que vérifie tous les champs et pas juste les champs servant à l'identification (sans Hibernate Validator)
        if(medicalRecord.getLastName() == null || medicalRecord.getFirstName() == null) {
            throw new InvalidArgumentsException("First name or/and last name is null. Cannot add it.");
        }

        if (medicalRecordService.saveMedicalRecord(medicalRecord)) {
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{firstName}{lastName}")
                    .buildAndExpand(medicalRecord.getFirstName(), medicalRecord.getLastName())
                    .toUri();
            return ResponseEntity.created(location).build();
        } else {
            throw new AlreadyExistingException("The medical record for "
                    + medicalRecord.getFirstName() + " " + medicalRecord.getLastName()
                    + " is already existing. Cannot add it.");

        }
    }

    /**
     * Update an existing Medical Record depending on the first name and last name of the person concerned.
     * It's not possible to update first name and last name.
     * If the arguments fields of the medical record to update are not correct, then throw InvalidArgumentsException and HTTP Status will be 400 - Bad Request.
     * If Medical Record first name and last name is not existing, then throw NotFoundException and HTTP Status will be 404 - Not Found.
     * @param medicalRecord to update full filled
     * @return 200 - OK if the medical record is correctly updated
     */
    @PutMapping(value = "/medicalrecord")
    public ResponseEntity<Void> updateMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
        //TODO : voir comment compléter pour que vérifie tous les champs et pas juste les champs servant à l'identification (sans Hibernate Validator)
        if(medicalRecord.getLastName() == null || medicalRecord.getFirstName() == null) {
            throw new InvalidArgumentsException("First name or/and last name is null. Cannot update it.");
        }

        if (medicalRecordService.updateMedicalRecord(medicalRecord)) {
            return ResponseEntity.ok().build();
        } else {
            throw new NotFoundException("The medical record of "
                    + medicalRecord.getFirstName() + " " + medicalRecord.getLastName()
                    + ", is not existing. Cannot update it.");
        }
    }

    /**
     * Delete an existing Medical Record.
     * If Medical Record first name and last name is not existing, then throw NotFoundException and HTTP Status will be 404 - Not Found.
     * @param firstName of the person's medical record to delete
     * @param lastName of the person's medical record to delete
     * @return 200 - OK if the medical record is correctly deleted
     */
    @DeleteMapping(value = "/medicalrecord/{firstName}{lastName}")
    public ResponseEntity<Void> deleteMedicalRecord(@PathVariable String firstName, @PathVariable String lastName) {
        if (medicalRecordService.deleteMedicalRecord(firstName, lastName)) {
            return ResponseEntity.ok().build();
        } else {
            throw new NotFoundException("The medical record of "
                    + firstName + " " + lastName
                    + ", is not existing. Cannot delete it.");
        }
    }
}
