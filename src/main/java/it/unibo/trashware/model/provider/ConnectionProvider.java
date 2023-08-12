package it.unibo.trashware.model.provider;

import java.util.Optional;

import jakarta.persistence.EntityManager;

public interface ConnectionProvider {
    
    Optional<EntityManager> getConnection();
    
    void closeConnection();

}
