package com.safetynet.safetynetalerts.web.controller;

import com.safetynet.safetynetalerts.model.FireStation;
import com.safetynet.safetynetalerts.service.FireStationService;
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
public class FireStationController {

    private FireStationService fireStationService;

    public  FireStationController(FireStationService pFireStationService) {
        Objects.requireNonNull(pFireStationService);
        fireStationService = pFireStationService;
    }

    /**
     * Get all Entities for Fire Station.
     * Http Status will be 200 - OK if we can access to all entities, even if list is empty.
     * @return Set of all entities of FireStation
     */
    @GetMapping(value = "/firestation")
    public Set<FireStation> getFireStations() {
        return fireStationService.getFireStations();
    }

    /**
     * Save a new FireStation mapping (one address and its station number associated).
     * Duplicate are not allowed.
     * If the arguments fields of the fire station to add are not correct, then throw InvalidArgumentsException and HTTP Status will be 400 - Bad Request.
     * If FireStation address is already existing (= duplicate), then throw AlreadyExistingException and HTTP Status will be 409 - Conflict.
     * @param fireStation full filled to save
     * @return 201 - Created if the new Fire Station is correctly saved
     */
    @PostMapping(value = "/firestation")
    public ResponseEntity<Void> createFireStation(@RequestBody FireStation fireStation) {
        //TODO : voir comment compléter pour que vérifie tous les champs et pas juste les champs servant à l'identification (sans Hibernate Validator)
        if (fireStation.getAddress() == null) {
            throw new InvalidArgumentsException("Address is null.");
        }

        if (fireStationService.saveFireStation(fireStation)) {
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{address}")
                    .buildAndExpand(fireStation.getAddress())
                    .toUri();
            return ResponseEntity.created(location).build();
        } else {
            throw new AlreadyExistingException("The fire station address : " + fireStation.getAddress() + ", is already existing. Cannot add it.");
        }
    }

    /**
     * Update an existing FireStation depending on its address.
     * If the arguments fields of the fire station to update are not correct, then throw InvalidArgumentsException and HTTP Status will be 400 - Bad Request.
     * If FireStation address is not existing, then throw NotFoundException and HTTP Status will be 404 - Not Found.
     * @param fireStation to update full filled
     * @return 200 - OK if the fire station is correctly updated
     */
    @PutMapping(value = "/firestation")
    public ResponseEntity<Void> updateFireStation(@RequestBody FireStation fireStation) {
        //TODO : voir comment compléter pour que vérifie tous les champs et pas juste les champs servant à l'identification (sans Hibernate Validator)
        if (fireStation.getAddress() == null) {
            throw new InvalidArgumentsException("Address is null.");
        }

        if (fireStationService.updateFireStation(fireStation)) {
            return ResponseEntity.ok().build();
        } else {
            throw new NotFoundException("The fire station mapping at the address : " + fireStation.getAddress() + ", is not existing. Cannot update it.");
        }
    }

    /**
     * Delete an existing fire station number and all the addresses mapped with.
     * If the station number is not existing, then throw NotFoundException and HTTP Status will be 404 - Not Found.
     * @param station - number of the station to delete
     * @return 200 - OK if the fire station number and all the addresses mapped with are deleted
     */
    @DeleteMapping(value = "/firestation/station/{station}")
    public ResponseEntity<Void> deleteFireStationNumberMapping(@PathVariable int station) {
        if (fireStationService.deleteFireStationbyNumber(station)) {
            return ResponseEntity.ok().build();
        } else {
            throw new NotFoundException("The fire station number : " + station + ", is not existing. Cannot delete it.");
        }
    }

    /**
     * Delete an existing address and its mapping with station number.
     * If the station address is not existing, then throw NotFoundException and HTTP Status will be 404 - Not Found.
     * @param address to delete
     * @return 200 - OK if the fire station mapping associated to the address is correctly deleted.
     */
    @DeleteMapping(value = "/firestation/address/{address}")
    public ResponseEntity<String> deleteFireStationAddressMapping(@PathVariable String address) {
        if (fireStationService.deleteFireStationbyAddress(address)) {
            return ResponseEntity.ok().build();
        } else {
            throw new NotFoundException("The fire station address : " + address + ", is not existing. Cannot delete it.");
        }

    }

}
