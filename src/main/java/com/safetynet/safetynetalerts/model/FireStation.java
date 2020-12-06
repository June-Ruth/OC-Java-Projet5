package com.safetynet.safetynetalerts.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class FireStation {
    /**
     * Number and Street information.
     */
    private String address;
    /**
     * Fire station associated to the address.
     */
    @Id
    private int station;
}
