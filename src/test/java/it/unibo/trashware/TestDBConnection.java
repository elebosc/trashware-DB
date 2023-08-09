package it.unibo.trashware;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import it.unibo.trashware.db.ConnectionProvider;
import it.unibo.trashware.db.ConnectionProviderImpl;
import it.unibo.trashware.model.Representative;
import it.unibo.trashware.utils.DateUtils;
import jakarta.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.Optional;

class TestDBConnection {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(TestDBConnection.class);

    private static ConnectionProvider provider;
    private static EntityManager em;

    @BeforeAll
    static void initAll() {
        provider = new ConnectionProviderImpl();
        final Optional<EntityManager> response = provider.getConnection();
        if (response.isEmpty()) {
            fail("Error: could not estabilish a connection with the database.");
        }
        em = response.get();    // gets the entity manager
    }

    @Test
    void testInsert() {
        em.getTransaction().begin();
        final Representative representative = new Representative(
            "RSSMRA90M09C573R",
            "Mario", 
            "Rossi", 
            "Cesena",
            DateUtils.buildDate(9, 8, 1990).get(), 
            "Cesena",
            "47522",
            "FC",
            "Via Cesare Battisti",
            18,
            "+393330000000",
            "+393330000001",
            "0547000000",
            "mario.rossi@outlook.com");
        try {
            em.persist(representative);
            em.getTransaction().commit();
        } catch (final Exception ex) {
            LOGGER.error("Error: insert operation failed.", ex);
            em.getTransaction().rollback();
            fail("Error: insert operation failed.");
        }

        // TO DO: check with query

        // TO DO: remove row 

        em.close();
    }

    @AfterAll
    static void tearDownAll() {
        provider.closeConnection();
    }

}
