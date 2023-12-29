package it.unibo.trashware.controller.api;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import it.unibo.trashware.commons.FieldTags;

public interface InventoryController {
    
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
	 * Binds a component to a PC.
	 * @param componentID
	 * @param pcID
	 */
	void bindComponentToPC(String componentID, String pcID);

	List<Map<FieldTags, String>> getLaptopsList();

	List<Map<FieldTags, String>> getMonitorsList();

	List<Map<FieldTags, String>> getOtherPeripheralsList();

	List<Map<FieldTags, String>> getCPUsList();

	List<Map<FieldTags, String>> getRAMModulesList();

	List<Map<FieldTags, String>> getStorageDevicesList();

	List<Map<FieldTags, String>> getChassisList();

}
