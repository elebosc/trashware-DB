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

}
