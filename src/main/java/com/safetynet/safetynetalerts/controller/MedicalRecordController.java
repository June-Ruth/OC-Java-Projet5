package com.safetynet.safetynetalerts.controller;

import com.safetynet.safetynetalerts.model.MedicalRecord;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MedicalRecordController {
    /**
     * Read - Get all Entities for Medical Record
     * @return //TODO
     */
    //TODO : visualiser toutes les données de MedicaLRecord List
    public List<MedicalRecord> getMedicalRecords(){
        //GET mapping, HTTP status si réussit : 200 OK

        return null;
    }

    /**
     * Create - save a new Medical Record.
     * @param medicalRecord full filled.
     * @return //TODO
     */
    //TODO : ajouter un dossier médical
    public MedicalRecord createMedicalRecord(MedicalRecord medicalRecord) {
        //POST mapping, HTTP status si réussi : 201 created
        //409 Conflict si crée un mapping déjà existant
        //400 Bad Resquest si param invalide
        return null;
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
