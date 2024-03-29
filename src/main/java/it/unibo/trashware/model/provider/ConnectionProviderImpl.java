package it.unibo.trashware.model.provider;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 * An implementation of a {@link ConnectionProvider}.
 */
public final class ConnectionProviderImpl implements ConnectionProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConnectionProvider.class);

    private EntityManagerFactory emf;
    
    @Override
    public Optional<EntityManager> getConnection() {
        final String persistenceUnitName = "persistence-unit";
        this.emf = Persistence.createEntityManagerFactory(persistenceUnitName);
        if (this.emf.isOpen()) {
            return Optional.of(emf.createEntityManager());
        } else {
            LOGGER.error("Error: could not estabilish a connection with the database.");
            return Optional.empty();
        }
    }

    @Override
    public void closeConnection() {
        this.emf.close();
    }

}
