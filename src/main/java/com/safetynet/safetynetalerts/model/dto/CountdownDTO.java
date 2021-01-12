package com.safetynet.safetynetalerts.model.dto;

public class CountdownDTO {

    private int adultCountdown;

    private int childrenCountdown;

    public int getAdultCountdown() {
        return adultCountdown;
    }

    public void setAdultCountdown(final int pAdultCountdown) {
        adultCountdown = pAdultCountdown;
    }

    public int getChildrenCountdown() {
        return childrenCountdown;
    }

    public void setChildrenCountdown(final int pChildrenCountdown) {
        childrenCountdown = pChildrenCountdown;
    }
}
