package com.safetynet.safetynetalerts.controller;

import com.safetynet.safetynetalerts.model.FireStation;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FireStationController {

    /**
     * Read - Get all Entities for Fire Station.
     * @return //TODO
     */
    //TODO : visualiser toutes les données de FireStation List
    public List<FireStation> getFireStations() {
        //GET mapping, HTTP status si réussit : 200 OK
        return null;
    }

    /**
     * Create - Save a new FireStation mapping.
     * @param fireStation full filled
     * @return //TODO
     */
    //TODO : ajout d'un mapping caserne / adresse
    public FireStation createFireStation(FireStation fireStation) {
        //POST mapping, HTTP status si réussi : 201 created
        //409 Conflict si crée un mapping déjà existant
        //400 Bad Resquest si param invalide
        return null;
    }

    /**
     * Update an existing fireStation depending of its address.
     * @param address - the address of the station to update
     * @param fireStation - the FireStation object Updated
     * @return //TODO
     */

    //TODO : mettre à jour le numéro de la caserne des pompiers d'une adresse
    public FireStation updateFireStation(String address, FireStation fireStation) {
        //PUT mapping, HTTP status si réussi : 200 OK
        //404 Not Found si n'existe pas
        //400 Bad Resquest si param invalide
        return null;
    }

    /**
     * Delete an existing fire station and all the address mapped with the station number.
     * @param fireStationNumber - number of the station to delete
     */
    //TODO ; supprimer le mapping d'une caserne
    public void deleteFireStationNumberMapping(int fireStationNumber) {
        //DELETE mapping, HTTP status si réussi : 200 OK si action confirmée et que le message de réponse inclut une représentation décrivant le status
        //404 Not Found si n'existe pas
    }

    /**
     * Delete an existing address and its mapping with station number.
     * @param address to delete
     */
    //TODO ; supprimer le mapping d'une adresse
    public void deleteFireStationAddressMapping(String address) {
        //DELETE mapping, HTTP status si réussi : 200 OK si action confirmée et que le message de réponse inclut une représentation décrivant le status
        //404 Not Found si n'existe pas
    }

}
