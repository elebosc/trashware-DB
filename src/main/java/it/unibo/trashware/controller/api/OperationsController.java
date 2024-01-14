package it.unibo.trashware.controller.api;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import it.unibo.trashware.commons.FieldTags;

public interface OperationsController {
    
    /**
	 * Stores data about a new donation.
	 * @param id
	 * @param date
	 * @param notes
	 * @param representativeID
	 */
    void addDonation(String id, LocalDate date, String devicesList, Optional<String> notes, String representativeID);
	
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
    void addRequest(String id, String type, String reason, LocalDate date, String devicesList,
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
	 * Registers the completion of the request.
	 * @param requestID
	 * @param date
	 */
	void registerRequestCompletion(String requestID, LocalDate date);

	/**
	 * Registers the delivery of the devices involved in the specified request.
	 * @param requestID
	 * @param date
	 */
	void registerDevicesDelivery(String requestID, LocalDate date);

	/**
	 * Creates a link an operation and a PC which is involved in the operation.
	 * @param pcID
	 * @param operationID
	 */
	void associatePCToOperation(String pcID, String operationID);

	/**
	 * Creates a link an operation and a peripheral which is involved in the operation.
	 * @param peripheralID
	 * @param operationID
	 */
	void associatePeripheralToOperation(String peripheralID, String operationID);

	/**
	 * Creates a link an operation and a component which is involved in the operation.
	 * @param componentID
	 * @param operationID
	 */
	void associateComponentToOperation(String componentID, String operationID);

	List<Map<FieldTags, String>> getDonationsList();

	List<Map<FieldTags, String>> getRequestsList(String requestStatus);

}
