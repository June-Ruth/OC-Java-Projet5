package com.safetynet.safetynetalerts.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.Objects;

@JsonTypeName(value = "firestations")
@JsonIgnoreProperties(ignoreUnknown = true)
public class FireStation {
    /**
     * Number and Street information.
     */
    private String address;
    /**
     * Fire station associated to the address.
     */
    private int station;

    /**
     * Constructor for FireStation with JSonCreator for serialization.
     * @param pAddress .
     * @param pStation .
     */
    @JsonCreator
    public FireStation(@JsonProperty("address") final String pAddress,
                       @JsonProperty("station") final int pStation) {
        address = pAddress;
        station = pStation;
    }

    /**
     * Getter.
     * @return adress
     */
    public String getAddress() {
        return address;
    }

    /**
     * Setter.
     * @param pAddress .
     */
    public void setAddress(final String pAddress) {
        this.address = pAddress;
    }

    /**
     * Getter.
     * @return station number
     */
    public int getStation() {
        return station;
    }

    /**
     * Setter.
     * @param pStation .
     */
    public void setStation(final int pStation) {
        this.station = pStation;
    }

    /**
     * Check equality  by the address.
     * @param o
     * @return true if address are equals.
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FireStation that = (FireStation) o;
        return address.equals(that.address);
    }

    /**
     * Do hash with the address.
     * @return hash by address.
     */
    @Override
    public int hashCode() {
        return Objects.hash(address);
    }
}
