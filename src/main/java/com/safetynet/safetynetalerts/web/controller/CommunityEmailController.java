package com.safetynet.safetynetalerts.web.controller;

import com.safetynet.safetynetalerts.service.PersonService;
import com.safetynet.safetynetalerts.web.exceptions.NotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;
import java.util.Set;

@RestController
public class CommunityEmailController {
    /**
     * @see Logger
     */
    private static final Logger LOGGER = LogManager.getLogger(CommunityEmailController.class);

    /**
     * @see PersonService
     */
    private PersonService personService;

    public CommunityEmailController(final PersonService pPersonService) {
        Objects.requireNonNull(pPersonService);
        personService = pPersonService;
    }

    /**
     * Get all Email of inhabitants in the city.
     * If city is not existing,
     * then throw NotFoundException and HTTP Status will be 404 - Not Found.
     * @param city searched
     * @return set of all email in city
     */
    @GetMapping(value = "/communityEmail")
    public Set<String> getAllEmailInCity(@RequestParam(value = "city") final String city) {
        Set<String> result = personService.getAllEmailInCity(city);
        if (result != null) {
            LOGGER.info("Emails in the city " + city + " were found.");
            return result;
        } else {
            RuntimeException e = new NotFoundException("The following city "
                    + city + " has not referenced inhabitants.");
            LOGGER.error(e);
            throw e;
        }
    }
}
