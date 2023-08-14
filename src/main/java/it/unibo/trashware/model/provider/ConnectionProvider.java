package it.unibo.trashware.model.provider;

import java.util.Optional;

import jakarta.persistence.EntityManager;

/**
 * This interface allows to open and close a connection to the database.
 */
public interface ConnectionProvider {
    
    /**
     * Opens a connection to the database.
     * @return if the connection is successfully established, a {@link Optional} that contains
     * a reference to the {@link EntityManager} that will be used to interact with the database;
     * an empty {@link Optional} otherwise.
     */
    Optional<EntityManager> getConnection();
    
    /**
     * Closes the connection to the database.
     */
    void closeConnection();

}
