package com.safetynet.safetynetalerts.web.controller;

import com.safetynet.safetynetalerts.model.FireStation;
import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.model.dto.FireDTO;
import com.safetynet.safetynetalerts.model.dto.PersonHealthInfoDTO;
import com.safetynet.safetynetalerts.service.FireStationService;
import com.safetynet.safetynetalerts.service.MedicalRecordService;
import com.safetynet.safetynetalerts.service.PersonService;
import com.safetynet.safetynetalerts.util.DtoConverter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Objects;
import java.util.Set;

public class FireController {
    /**
     * @see Logger
     */
    private static Logger LOGGER = LogManager.getLogger(FireController.class);
    /**
     * @see MedicalRecordService
     */
    private MedicalRecordService medicalRecordService;
    /**
     * @see PersonService
     */
    private PersonService personService;
    /**
     * @see FireStationService
     */
    private FireStationService fireStationService;

    /**
     * Public constructor.
     * @param pMedicalRecordService not null
     * @param pPersonService not null
     * @param pFireStationService not null
     */
    public FireController(final MedicalRecordService pMedicalRecordService,
                          final PersonService pPersonService,
                          final FireStationService pFireStationService) {
        Objects.requireNonNull(pMedicalRecordService);
        Objects.requireNonNull(pPersonService);
        Objects.requireNonNull(pFireStationService);
        medicalRecordService = pMedicalRecordService;
        personService = pPersonService;
        fireStationService = pFireStationService;
    }

    /**
     * Get list of inhabitant at the specified address and the station number concerned.
     * @param address .
     * @return List of inhabitant
     */
    @GetMapping(value = "/fire")
    public FireDTO getAllPersonsAndStationByAddress(@RequestParam(value = "address") final String address) {
        FireStation fireStation = fireStationService.getByAddress(address);
        Set<Person> personsListAtAddress = personService.getAllByAddress(address);
        Set<MedicalRecord> medicalRecordSet = medicalRecordService.getAllMedicalRecordsByPersonList(personsListAtAddress);
        Set<PersonHealthInfoDTO> personHealthInfoDTOSet = DtoConverter.convertToPersonHealthInfoDTOSet(medicalRecordSet);
        LOGGER.info("Find all persons and the station at address " + address);
        return DtoConverter.convertToFireAddressDTO(fireStation, personHealthInfoDTOSet);
    }
}
