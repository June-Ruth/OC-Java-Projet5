package com.safetynet.safetynetalerts.web.controller;

import com.safetynet.safetynetalerts.model.FireStation;
import com.safetynet.safetynetalerts.service.FireStationService;
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
public class FireStationController {
    /**
     * @see Logger
     */
    private static final Logger LOGGER = LogManager.getLogger(FireStationController.class);
    /**
     * @see FireStationService
     */
    private FireStationService fireStationService;

    /**
     * public constructor for FireStation Controller.
     * The controller requires a non null FireStationService.
     * @param pFireStationService not null
     */
    public  FireStationController(
            final FireStationService pFireStationService) {
        Objects.requireNonNull(pFireStationService);
        fireStationService = pFireStationService;
    }

    /**
     * Get all Entities for Fire Station.
     * Http Status will be 200 - OK if we can access to all entities,
     * even if list is empty.
     * @return Set of all entities of FireStation
     */
    @GetMapping(value = "/firestation")
    public Set<FireStation> getFireStations() {
        Set<FireStation> result = fireStationService.getFireStations();
        LOGGER.info("Get all fire stations : {}", result);
        return result;
    }

    /**
     * Save a new FireStation mapping
     * (one address and its station number associated).
     * Duplicate are not allowed.
     * If the arguments fields of the fire station to add are not correct,
     * HTTP Status will be 400 - Bad Request.
     * If FireStation address is already existing (= duplicate),
     * then throw AlreadyExistingException
     * and HTTP Status will be 409 - Conflict.
     * @param fireStation full filled to save
     * @return 201 - Created if the new Fire Station is correctly saved
     */
    @PostMapping(value = "/firestation")
    public ResponseEntity<Void> createFireStation(
            @RequestBody final FireStation fireStation) {
      if (fireStationService.saveFireStation(fireStation)) {
            LOGGER.info("New fire station was saved.");
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{address}")
                    .buildAndExpand(fireStation.getAddress())
                    .toUri();
            return ResponseEntity.created(location).build();
        } else {
            RuntimeException e = new AlreadyExistingException("The fire station address : "
                    + fireStation.getAddress()
                    + ", is already existing. Cannot add it.");
            LOGGER.error(e);
            throw e;
        }
    }

    /**
     * Update an existing FireStation depending on its address.
     * If the arguments fields of the fire station to update are not correct,
     * HTTP Status will be 400 - Bad Request.
     * If FireStation address is not existing,
     * then throw NotFoundException
     * and HTTP Status will be 404 - Not Found.
     * @param fireStation to update full filled
     * @return 200 - OK if the fire station is correctly updated
     */
    @PutMapping(value = "/firestation")
    public ResponseEntity<Void> updateFireStation(@Valid
            @RequestBody final FireStation fireStation) {
        if (fireStationService.updateFireStation(fireStation)) {
            LOGGER.info("Fire station was updated.");
            return ResponseEntity.ok().build();
        } else {
            RuntimeException e = new NotFoundException(
                    "The fire station mapping at the address : "
                    + fireStation.getAddress()
                    + ", is not existing. Cannot update it.");
            LOGGER.error(e);
            throw e;
        }
    }

    /**
     * Delete an existing fire station number and all the addresses mapped with.
     * If the station number is not existing,
     * then throw NotFoundException
     * and HTTP Status will be 404 - Not Found.
     * @param station - number of the station to delete
     * @return 200 - OK if the fire station number
     * and all the addresses mapped with are deleted
     */
    @DeleteMapping(value = "/firestation/station/{station}")
    public ResponseEntity<Void> deleteFireStationNumberMapping(
            @PathVariable final int station) {
        if (fireStationService.deleteFireStationbyNumber(station)) {
            LOGGER.info("All fire station associated to number "
                    + station + " were deleted.");
            return ResponseEntity.ok().build();
        } else {
            RuntimeException e =  new NotFoundException(
                    "The fire station number : " + station
                            + ", is not existing. Cannot delete it.");
            LOGGER.error(e);
            throw e;
        }
    }

    /**
     * Delete an existing address and its mapping with station number.
     * If the station address is not existing,
     * then throw NotFoundException
     * and HTTP Status will be 404 - Not Found.
     * @param address to delete
     * @return 200 - OK if the fire station mapping associated
     * to the address is correctly deleted.
     */
    @DeleteMapping(value = "/firestation/address/{address}")
    public ResponseEntity<String> deleteFireStationAddressMapping(
            @PathVariable final String address) {
        if (fireStationService.deleteFireStationbyAddress(address)) {
            LOGGER.info("Fire station at the address : "
                    + address + "was deleted.");
            return ResponseEntity.ok().build();
        } else {
            RuntimeException e = new NotFoundException("The fire station address : "
                    + address + ", is not existing. Cannot delete it.");
            LOGGER.error(e);
            throw e;
        }

    }

}
