package it.unibo.populator;

import java.io.IOException;

/**
 * Interface for database populator.
 */
public interface Populator {
    
    /**
     * This method triggers the database population.
     * @throws IOException if an error occurs during an I/O operation related to the data generation.
     */
    public void populateDB() throws IOException;

}