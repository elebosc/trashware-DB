package it.unibo.trashware.services.api;

import java.util.Optional;

public interface InventoryService {
    
    /**
     * Stores data about a new CPU.
     * @param componentID
     * @param type
     * @param brand
     * @param model
     * @param notes
     * @param architecture
     */
	void addCPU(String componentID, String type, String brand, String model, Optional<String> notes,
            int architecture);

    /**
	 * Stores data about a new RAM module.
	 * @param componentID
	 * @param type
	 * @param brand
	 * @param model
	 * @param notes
	 * @param size
	 */
	void addRAM(String componentID, String type, String brand, String model, Optional<String> notes, int size);

    /**
	 * Stores data about a new mass storage device.
	 * @param componentID
	 * @param type
	 * @param brand
	 * @param model
	 * @param notes
	 * @param massStorageType
	 * @param size
	 */
	void addMassStorage(String componentID, String type, String brand, String model, Optional<String> notes,
            String massStorageType, int size);

    /**
    * Stores data about a new chassis for a desktop PC.
    * @param componentID
    * @param type
    * @param brand
    * @param model
    * @param notes
    * @param color
    */
    void addChassis(String componentID, String type, String brand, String model, Optional<String> notes, String color);

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
	 * @param type
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
	void addMonitor(String peripheralID, String type, String brand, String model, String connectivity,
			Optional<String> notes, String monitorType, int size, String aspectRatio, boolean isVGASupported,
			boolean isDVISupported, boolean hasEmbeddedAudio);

}
