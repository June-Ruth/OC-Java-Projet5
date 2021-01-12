package com.safetynet.safetynetalerts.web.controller;

import com.safetynet.safetynetalerts.constant.BusinessConstants;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.model.dto.ChildAlertDTO;
import com.safetynet.safetynetalerts.model.dto.PersonNameDTO;
import com.safetynet.safetynetalerts.service.MedicalRecordService;
import com.safetynet.safetynetalerts.service.PersonService;
import com.safetynet.safetynetalerts.util.DtoConverter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@RestController
public class ChildAlertController {
    /**
     * @see Logger
     */
    private static final Logger LOGGER = LogManager.getLogger(ChildAlertController.class);
    /**
     * @see MedicalRecordService
     */
    private MedicalRecordService medicalRecordService;
    /**
     * @see PersonService
     */
    private PersonService personService;

    /**
     * Public constructor for ChildAlertController.
     * @param pPersonService not null
     * @param pMedicalRecordService not null
     */
    public ChildAlertController(final PersonService pPersonService,
                                final MedicalRecordService pMedicalRecordService) {
        Objects.requireNonNull(pPersonService);
        Objects.requireNonNull(pMedicalRecordService);
        personService = pPersonService;
        medicalRecordService = pMedicalRecordService;
    }

    /**
     * Get all children at the specified address with other inhabitant.
     * If there is no child at the address, return an empty set.
     * @param address .
     * @return set of children
     */
    @GetMapping(value = "/childAlert")
    public Set<ChildAlertDTO> getAllChildrenByAddress(@RequestParam(value = "address") final String address) {
        Set<ChildAlertDTO> childrenAtAddress = new HashSet<>();
        Set<Person> personsAtAddress = personService.getAllByAddress(address);
        if (personsAtAddress != null) {
            personsAtAddress.iterator().forEachRemaining((person -> {
                int age = medicalRecordService.getAge(person);
                if (age <= BusinessConstants.LIMIT_CHILD_AGE) {
                    Set<Person> personsAtAddressNew = new HashSet<>(personsAtAddress);
                    personsAtAddressNew.remove(person);
                    Set<PersonNameDTO> otherPersonsAtAddress = DtoConverter.convertToPersonNameDTOSet(personsAtAddressNew);
                    ChildAlertDTO childAlertDTO = DtoConverter.convertToChildInfoDTO(person, age, otherPersonsAtAddress);
                    childrenAtAddress.add(childAlertDTO);
                }
            }));
        }
        LOGGER.info("Find all children if exists at the address " + address);
        return childrenAtAddress;
    }
}
