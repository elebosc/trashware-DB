package it.unibo.trashware.controller;

import java.time.LocalDate;
import java.util.Optional;

/**
 * This interface allows to interact with the controller of the application.
 */
public interface Controller {
    
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
	 * Stores data about a new CPU.
	 * @param componentID
	 * @param brand
	 * @param model
	 * @param notes
	 * @param architecture
	 */
	void addCPU(String componentID, String brand, String model, Optional<String> notes, int architecture);

	/**
	 * Stores data about a new RAM module.
	 * @param componentID
	 * @param brand
	 * @param model
	 * @param notes
	 * @param size
	 */
	void addRAM(String componentID, String brand, String model, Optional<String> notes, int size);

	/**
	 * Stores data about a new mass storage device.
	 * @param componentID
	 * @param brand
	 * @param model
	 * @param notes
	 * @param massStorageType
	 * @param size
	 */
	void addMassStorage(String componentID, String brand, String model, Optional<String> notes,
			String massStorageType, int size);
	
	/**
	 * Stores data about a new chassis for a desktop PC.
	 * @param componentID
	 * @param brand
	 * @param model
	 * @param notes
	 * @param color
	 */
	void addChassis(String componentID, String brand, String model, Optional<String> notes, String color);

	/**
	 * Stores data about other types of components.
	 * @param componentID
	 * @param type
	 * @param brand
	 * @param model
	 * @param notes
	 */
	void addComponent(String componentID, String type, String brand, String model, Optional<String> notes);

	/**
	 * Stores data about a new peripheral.
	 * @param peripheralID
	 * @param type
	 * @param brand
	 * @param model
	 * @param connectivity
	 * @param notes
	 */
	void addPeripheral(String peripheralID, String type, String brand, String model, String connectivity,
			Optional<String> notes);

	/**
	 * Stores data about a new monitor.
	 * @param peripheralID
	 * @param brand
	 * @param model
	 * @param connectivity
	 * @param notes
	 * @param monitorType
	 * @param size
	 * @param aspectRatio
	 * @param isVGASupported
	 * @param isDVISupported
	 * @param hasEmbeddedAudio
	 */
	void addMonitor(String peripheralID, String brand, String model, String connectivity, Optional<String> notes,
			String monitorType, int size, String aspectRatio, boolean isVGASupported, boolean isDVISupported,
			boolean hasEmbeddedAudio);

	/**
	 * Stores data about a new desktop PC.
	 * @param pcID
	 * @param cpuID
	 * @param massStorage01ID
	 * @param massStorage02ID
	 * @param ramModule01ID
	 * @param ramModule02ID
	 * @param ramModule03ID
	 * @param ramModule04ID
	 * @param isEthernetSupported
	 * @param isWiFiSupported
	 * @param isBluetoothSupported
	 * @param chassisID
	 * @param monitorID
	 * @param keyboardID
	 * @param mouseID
	 * @param speakersID
	 * @param notes
	 */
	void addDesktopPC(String pcID, String cpuID, String massStorage01ID, Optional<String> massStorage02ID, String ramModule01ID,
			Optional<String> ramModule02ID, Optional<String> ramModule03ID, Optional<String> ramModule04ID, boolean isEthernetSupported,
			boolean isWiFiSupported, boolean isBluetoothSupported, String chassisID, Optional<String> monitorID,
			Optional<String> keyboardID, Optional<String> mouseID, Optional<String> speakersID, Optional<String> notes);

	/**
	* Stores data about a new laptop.
	* @param pcID
	* @param cpuID
	* @param massStorage01ID
	* @param massStorage02ID
	* @param ramModule01ID
	* @param ramModule02ID
	* @param ramModule03ID
	* @param ramModule04ID
	* @param isEthernetSupported
	* @param isWiFiSupported
	* @param isBluetoothSupported
	* @param brand
	* @param model
	* @param size
	* @param color
	* @param notes
	*/
	void addLaptop(String pcID, String cpuID, String massStorage01ID, Optional<String> massStorage02ID, String ramModule01ID,
			Optional<String> ramModule02ID, Optional<String> ramModule03ID, Optional<String> ramModule04ID, boolean isEthernetSupported,
			boolean isWiFiSupported, boolean isBluetoothSupported, String brand, String model, int size, String color,
			Optional<String> notes);

	/**
	* Stores data about a new installation of an operating system.
	* @param pcID
	* @param name
	* @param version
	* @param lastUpdateDate
	*/
	void addOperatingSystem(String pcID, String name, String version, LocalDate lastUpdateDate);

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

}
