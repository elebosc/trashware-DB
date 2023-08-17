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

}
