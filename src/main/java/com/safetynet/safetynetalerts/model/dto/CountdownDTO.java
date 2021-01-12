package com.safetynet.safetynetalerts.model.dto;

public class CountdownDTO {
    /**
     * Number of adult.
     */
    private int adultCountdown;
    /**
     * Number of children.
     */
    private int childrenCountdown;

    /**
     * Getter.
     * @return adultCountdown
     */
    public int getAdultCountdown() {
        return adultCountdown;
    }

    /**
     * Setter.
     * @param pAdultCountdown .
     */
    public void setAdultCountdown(final int pAdultCountdown) {
        adultCountdown = pAdultCountdown;
    }

    /**
     * Getter.
     * @return children countdown
     */
    public int getChildrenCountdown() {
        return childrenCountdown;
    }

    /**
     * Setter.
     * @param pChildrenCountdown .
     */
    public void setChildrenCountdown(final int pChildrenCountdown) {
        childrenCountdown = pChildrenCountdown;
    }
}
