package it.unibo.trashware;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import it.unibo.trashware.entities.Representative;
import it.unibo.trashware.model.provider.ConnectionProvider;
import it.unibo.trashware.model.provider.ConnectionProviderImpl;
import jakarta.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
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
            fail("Error: could not establish a connection with the database.");
        }
        em = response.get();    // gets the entity manager
    }

    @Test
    void testInsertAndRemove() {

        // Write a new row in the table
        final Representative newRep = createRepresentativeObj();
        em.getTransaction().begin();
        try {
            em.persist(newRep);
            em.getTransaction().commit();
        } catch (final Exception ex) {
            LOGGER.error("Error: insert operation failed.", ex);
            em.getTransaction().rollback();
            fail("Error: insert operation failed.");
        }

        // Read the row just inserted
        final Representative readRep = em.find(Representative.class, newRep.getFiscalCode());
        assertEquals(newRep, readRep);

        // Remove the inserted row
        em.getTransaction().begin();
        try {
            em.remove(newRep);
            em.getTransaction().commit();
        } catch (final Exception ex) {
            LOGGER.error("Error: delete operation failed.", ex);
            em.getTransaction().rollback();
            fail("Error: delete operation failed.");
        }

        // Try to read the row; no result should be returned.
        assertNull(em.find(Representative.class, newRep.getFiscalCode()));

    }

    @AfterAll
    static void tearDownAll() {
        em.close();
        provider.closeConnection();
    }

    private Representative createRepresentativeObj() {
        final Representative rep = new Representative();
        rep.setFiscalCode("RSSMRA90M09C573R");
        rep.setName("Mario");
        rep.setSurname("Rossi");
        rep.setBirthplace("Cesena");
        rep.setBirthday(LocalDate.of(1990, 8, 9));
        rep.setResidenceCity("Cesena");
        rep.setResidenceCAP("47522");
        rep.setResidenceProvince("FC");
        rep.setResidenceStreet("Via Cesare Battisti");
        rep.setResidenceStreetNumber(18);
        rep.setTelephoneNumber1("+393330000000");
        rep.setTelephoneNumber2("+393330000001");
        rep.setFaxNumber("0547000000");
        rep.setEmail("mario.rossi@outlook.com");
        return rep;
    }

}
