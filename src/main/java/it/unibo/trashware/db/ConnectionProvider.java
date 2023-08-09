package it.unibo.trashware.db;

import java.util.Optional;

import jakarta.persistence.EntityManager;

public interface ConnectionProvider {
    
    Optional<EntityManager> getConnection();
    
    void closeConnection();

}
