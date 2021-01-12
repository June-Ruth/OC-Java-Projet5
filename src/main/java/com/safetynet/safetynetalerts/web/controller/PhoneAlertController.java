package com.safetynet.safetynetalerts.web.controller;

import com.safetynet.safetynetalerts.service.FireStationService;
import com.safetynet.safetynetalerts.service.PersonService;
import com.safetynet.safetynetalerts.web.exceptions.NotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@RestController
public class PhoneAlertController {
    /**
     * @see Logger
     */
    private static final Logger LOGGER =
            LogManager.getLogger(PhoneAlertController.class);
    /**
     * @see FireStationService
     */
    private FireStationService fireStationService;
    /**
     * @see PersonService
     */
    private PersonService personService;

    /**
     * Public constructor for PhoneAlertController.
     * @param pFireStationService not null
     * @param pPersonService not null
     */
    public PhoneAlertController(final FireStationService pFireStationService,
                                final PersonService pPersonService) {
        Objects.requireNonNull(pFireStationService);
        Objects.requireNonNull(pPersonService);
        fireStationService = pFireStationService;
        personService = pPersonService;
    }

    /**
     * Get all phone number of inhabitant associated to given station number.
     * @param stationNumber concerned
     * @return set of all phone number
     */
    @GetMapping(value = "/phoneAlert")
    public Set<String> getAllPhoneByStationNumber(
            @RequestParam(value = "firestation") final int stationNumber) {
        Set<String> phoneList = new HashSet<>();
        Set<String> addressList = fireStationService
                .getAllAddressByStationNumber(stationNumber);
        if (addressList != null) {
            addressList.iterator().forEachRemaining((address) -> {
                phoneList.addAll(personService.findAllPhoneByAddress(address));
            });
            LOGGER.info("Phone at station number "
                    + stationNumber + " were found");
            return phoneList;
        } else {
            RuntimeException e = new NotFoundException(
                    "Station number " + stationNumber + " not found.");
            LOGGER.error(e);
            throw e;
        }
    }
}
