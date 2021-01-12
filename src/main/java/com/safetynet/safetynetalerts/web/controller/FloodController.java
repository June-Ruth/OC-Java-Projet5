package com.safetynet.safetynetalerts.web.controller;

import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.model.dto.FloodDTO;
import com.safetynet.safetynetalerts.model.dto.PersonHealthInfoDTO;
import com.safetynet.safetynetalerts.service.FireStationService;
import com.safetynet.safetynetalerts.service.MedicalRecordService;
import com.safetynet.safetynetalerts.service.PersonService;
import com.safetynet.safetynetalerts.util.DtoConverter;
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
public class FloodController {
    /**
     * @see Logger
     */
    private static final Logger LOGGER = LogManager.getLogger(FloodController.class);
    /**
     * @see FireStationService
     */
    private FireStationService fireStationService;
    /**
     * @see PersonService
     */
    private PersonService personService;
    /**
     * @see MedicalRecordService
     */
    private MedicalRecordService medicalRecordService;

    public FloodController(final FireStationService pFireStationService,
                           final PersonService pPersonService,
                           final MedicalRecordService pMedicalRecordService) {
        Objects.requireNonNull(pMedicalRecordService);
        Objects.requireNonNull(pPersonService);
        Objects.requireNonNull(pFireStationService);
        medicalRecordService = pMedicalRecordService;
        personService = pPersonService;
        fireStationService = pFireStationService;
    }

    /**
     * Get all flood by station number.
     * Each flood has a list with Person.
     * @param stationNumber .
     * @return List of inhabitant
     */
    @GetMapping(value = "/flood/stations")
    public Set<FloodDTO> getAllFloodsByStationNumber(@RequestParam(value = "station") final int stationNumber) {
        Set<String> addressList = fireStationService.getAllAddressByStationNumber(stationNumber);
        Set<FloodDTO> floodDTOSet = new HashSet<>();
       if(addressList != null) {
           addressList.iterator().forEachRemaining(address -> {
               Set<Person> personsAtAddress = personService.getAllByAddress(address);
               Set<MedicalRecord> medicalRecordsAtAddress = medicalRecordService.getAllMedicalRecordsByPersonList(personsAtAddress);
               Set<PersonHealthInfoDTO> personHealthInfoDTOSet = DtoConverter.convertToPersonHealthInfoDTOSet(medicalRecordsAtAddress);
               FloodDTO floodDTO = DtoConverter.convertToFloodDTO(address, personHealthInfoDTOSet);
               floodDTOSet.add(floodDTO);
           });
           LOGGER.info("Find all flood cover by station number " + stationNumber);
           return floodDTOSet;
       } else {
           RuntimeException e = new NotFoundException("No address cover by station number " + stationNumber + " was found.");
           LOGGER.error(e);
           throw e;
       }
    }
}
