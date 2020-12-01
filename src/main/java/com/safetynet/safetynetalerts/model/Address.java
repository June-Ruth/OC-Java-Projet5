package com.safetynet.safetynetalerts.model;

import lombok.Data;

@Data
public class Address {
    /**
     * Number and Street information.
     */
    private String address;
    /**
     * City information.
     */
    private String city;
    /**
     * Zip information.
     */
    private int zip;
    /**
     * Fire station associated to the address.
     */
    private int firestation;

    /**
     * Public constructor for Address, composed of following elements.
     * @param pAddress for number and street.
     * @param pCity for city name.
     * @param pZip for zip number.
     * @param pFirestation for fire station number.
     */
    public Address(final String pAddress, final String pCity,
                   final int pZip, final int pFirestation) {
        address = pAddress;
        city = pCity;
        zip = pZip;
        firestation = pFirestation;
    }
}
