package it.unibo.populator;

import java.io.IOException;

/**
 * This interface allows the population of the database.
 */
public interface Populator {
    
    void populateDB() throws IOException;

}
