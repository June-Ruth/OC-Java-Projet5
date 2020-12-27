package com.safetynet.safetynetalerts.controller;

import com.safetynet.safetynetalerts.model.FireStation;
import com.safetynet.safetynetalerts.service.FireStationService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Objects;
import java.util.Set;

@RestController
public class FireStationController {

    private FireStationService fireStationService;

    public  FireStationController(FireStationService pFireStationService) {
        Objects.requireNonNull(pFireStationService);
        fireStationService = pFireStationService;
    }

    /**
     * Read - Get all Entities for Fire Station.
     * @return List of all entities of FireStation
     */
    @GetMapping(value = "/firestation")
    public Set<FireStation> getFireStations() {
        //GET mapping, HTTP status si réussit : 200 OK
        return fireStationService.getFireStations();
    }

    /**
     * Create - Save a new FireStation mapping.
     * @param fireStation full filled
     */
    @PostMapping(value = "/firestation")
    public ResponseEntity<String> createFireStation(@RequestBody FireStation fireStation) {
        //POST mapping, HTTP status si réussi : 201 created
        //409 Conflict si crée un mapping déjà existant
        //400 Bad Resquest si param invalide
        if (fireStation.getAddress() == null) {
            return ResponseEntity.badRequest().build();
        }

        boolean isCreated = fireStationService.saveFireStation(fireStation);

        if (!isCreated) {
            return ResponseEntity.status(409).body("Fire Station Address Already Exists");
        } else {
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{address}")
                    .buildAndExpand(fireStation.getAddress())
                    .toUri();
            return ResponseEntity.created(location).build();
        }
    }

    /**
     * Update an existing fireStation depending of its address.
     * @param fireStation - the FireStation object Updated
     * @return //TODO
     */

    //TODO : mettre à jour le numéro de la caserne des pompiers d'une adresse
    public FireStation updateFireStation(@RequestBody FireStation fireStation) {
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
