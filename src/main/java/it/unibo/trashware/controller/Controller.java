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

}
