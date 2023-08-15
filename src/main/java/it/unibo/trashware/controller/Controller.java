package it.unibo.trashware.controller;

import java.time.LocalDate;
import java.util.Optional;

/**
 * This interface allows to interact with the controller of the application.
 */
public interface Controller {
    
    /**
     * Stores data about a new representative.
     * @param fiscalCode
     * @param name
     * @param surname
     * @param birthplace
     * @param birthday
     * @param residenceCity
     * @param residenceCAP
     * @param residenceProvince
     * @param residenceStreet
     * @param residenceStreetNumber
     * @param telephoneNumber1
     * @param telephoneNumber2
     * @param faxNumber
     * @param email
     */
    void addRepresentative(String fiscalCode, String name, String surname, String birthplace, LocalDate birthday,
            String residenceCity, String residenceCAP, String residenceProvince, String residenceStreet,
            int residenceStreetNumber, String telephoneNumber1, Optional<String> telephoneNumber2,
            Optional<String> faxNumber, Optional<String> email);

    /**
     * Stores data about a new society.
     * @param VATNumber
     * @param fiscalCode
     * @param name
     * @param registeredOfficeCity
     * @param registeredOfficeCAP
     * @param registeredOfficeProvince
     * @param registeredOfficeStreet
     * @param registeredOfficeStreetNumber
     */
    void addSociety(String VATNumber, String fiscalCode, String name, String registeredOfficeCity, String registeredOfficeCAP,
            String registeredOfficeProvince, String registeredOfficeStreet, int registeredOfficeStreetNumber);

}
