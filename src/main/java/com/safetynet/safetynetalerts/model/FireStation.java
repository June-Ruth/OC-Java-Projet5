package com.safetynet.safetynetalerts.model;

import com.fasterxml.jackson.annotation.*;

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

    @JsonCreator
    public FireStation(@JsonProperty("address") String address,
                       @JsonProperty("station") int station) {
        this.address = address;
        this.station = station;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getStation() {
        return station;
    }

    public void setStation(int station) {
        this.station = station;
    }
}
