package com.safetynet.safetynetalerts.controller;

import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.service.MedicalRecordService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
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
     * Read - Get all Entities for Medical Record
     * @return list of all the medical Records
     */
    @GetMapping(value = "/medicalrecord")
    public Set<MedicalRecord> getMedicalRecords(){
        //GET mapping, HTTP status si réussit : 200 OK
        return medicalRecordService.getMedicalsRecords();
    }

    /**
     * Create - save a new Medical Record.
     * @param medicalRecord full filled.
     */
    @PostMapping(value = "/medicalrecord")
    public void createMedicalRecord(MedicalRecord medicalRecord) {
        //POST mapping, HTTP status si réussi : 201 created
        //409 Conflict si crée un mapping déjà existant
        //400 Bad Resquest si param invalide
        medicalRecordService.saveMedicalRecord(medicalRecord);
    }

    /**
     * Update an existing Medical Record depending of the first name and last name of the person concerned.
     * It's not possible to update first name and last name.
     * @param firstName of the person's medical record to update
     * @param lastName of the person's medical record to update
     * @param medicalRecord - Medical Record Object updated
     * @return //TODO
     */
    //TODO ; mettre à jour un dossier médical existant : supposer que le nom & prénom ne change pas
    public MedicalRecord updateMedicalRecord(String firstName, String lastName, MedicalRecord medicalRecord) {
        //PUT mapping, HTTP status si réussi : 200 OK
        //404 Not Found si n'existe pas
        //400 Bad Resquest si param invalide
        return null;
    }

    /**
     * Delete an existing Medical Record
     * @param firstName of the person's medical record to delete
     * @param lastName of the person's medical record to delete
     */
    //TODO : supprimer un dossier médical : utiliser le nom / prénom comme identifiant unique
    public void deleteMedicalRecord(String firstName, String lastName) {
        //DELETE mapping, HTTP status si réussi : 200 OK si action confirmée et que le message de réponse inclut une représentation décrivant le status
        //404 Not Found si n'existe pas

    }
}
