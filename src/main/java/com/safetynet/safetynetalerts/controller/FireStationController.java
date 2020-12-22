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
        return null;
    }

    /**
     * Create - Save a new FireStation mapping.
     * @param fireStation full filled
     * @return //TODO
     */
    //TODO : ajout d'un mapping caserne / adresse
    public FireStation createFireStation(FireStation fireStation) {
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
        return null;
    }

    /**
     * Delete an existing fire station and all the address mapped with the station number.
     * @param fireStationNumber - number of the station to delete
     */
    //TODO ; supprimer le mapping d'une caserne
    public void deleteFireStationNumberMapping(int fireStationNumber) {

    }

    /**
     * Delete an existing address and its mapping with station number.
     * @param address to delete
     */
    //TODO ; supprimer le mapping d'une adresse
    public void deleteFireStationAddressMapping(String address) {

    }

}
