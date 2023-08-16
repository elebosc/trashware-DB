package it.unibo.trashware.services.api;

import java.time.LocalDate;
import java.util.Optional;

/**
 * This interface gives access to the services that enable the management
 * of the data related to the operations that involve the Trashware project.
 */
public interface OperationsService {

	/**
	 * Stores data about a new donation.
	 * @param id
	 * @param date
	 * @param notes
	 * @param representativeID
	 */
    void addDonation(String id, LocalDate date, Optional<String> notes, String representativeID);
	
	/**
	 * Stores data about a new request.
	 * @param id
	 * @param type
	 * @param reason
	 * @param date
	 * @param deadline
	 * @param priorityLevel
	 * @param notes
	 * @param representativeID
	 */
    void addRequest(String id, String type, String reason, LocalDate date,
            LocalDate deadline, int priorityLevel, Optional<String> notes, String representativeID);
    
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
    void addRepresentative(String fiscalCode, String name, String surname, String birthplace,
            LocalDate birthday, String residenceCity, String residenceCAP, String residenceProvince,
            String residenceStreet, int residenceStreetNumber, String telephoneNumber1,
            Optional<String> telephoneNumber2, Optional<String> faxNumber, Optional<String> email);
    
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
    void addSociety(String VATNumber, String fiscalCode, String name, String registeredOfficeCity,
            String registeredOfficeCAP, String registeredOfficeProvince, String registeredOfficeStreet,
            int registeredOfficeStreetNumber);

	/**
	 * Stores data about a new representation relationship between a society and its representative.
	 * @param societyVATNumber
	 * @param representativeFiscalCode
	 * @param representativeTitle
	 */
	void addRepresentation(String societyVATNumber, String representativeFiscalCode, String representativeTitle);
	
	/**
	 * Stores data about a new object description.
	 * @param operationID
	 * @param lineNumber
	 * @param type
	 * @param quantity
	 * @param notes
	 */
    void addObjectDescription(String operationID, int lineNumber, String type, int quantity, Optional<String> notes);

}