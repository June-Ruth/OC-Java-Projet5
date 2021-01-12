package com.safetynet.safetynetalerts.model.dto;

public class PersonContactInfoDTO {
    /**
     * FirstName.
     */
    private String firstName;
    /**
     * Last Name.
     */
    private String lastName;
    /**
     * Address.
     */
    private String address;
    /**
     * Phone number.
     */
    private String phone;
    /**
     * Email.
     */
    private String email;

    /**
     * Getter.
     * @return firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Setter.
     * @param pFirstName .
     */
    public void setFirstName(final String pFirstName) {
        this.firstName = pFirstName;
    }

    /**
     * Getter.
     * @return lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Setter.
     * @param pLastName .
     */
    public void setLastName(final String pLastName) {
        this.lastName = pLastName;
    }

    /**
     * Getter.
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Setter.
     * @param pAdress .
     */
    public void setAddress(final String pAdress) {
        this.address = pAdress;
    }

    /**
     * Getter.
     * @return phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Setter.
     * @param pPhone .
     */
    public void setPhone(final String pPhone) {
        this.phone = pPhone;
    }

    /**
     * Getter.
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter.
     * @param pEmail .
     */
    public void setEmail(final String pEmail) {
        this.email = pEmail;
    }
}
