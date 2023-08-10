package it.unibo.trashware;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import it.unibo.trashware.db.ConnectionProvider;
import it.unibo.trashware.db.ConnectionProviderImpl;
import it.unibo.trashware.model.entities.Representative;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
        final String fiscalCode = "RSSMRA90M09C573R";
        em.getTransaction().begin();
        final Representative newRep = new Representative();
        newRep.setCodiceFiscale(fiscalCode);
        newRep.setName("Mario");
        newRep.setSurname("Rossi");
        newRep.setBirthplace("Cesena");
        newRep.setBirthday(LocalDate.of(1990, 8, 9));
        newRep.setResidenceCity("Cesena");
        newRep.setResidenceCAP("47522");
        newRep.setResidenceProvince("FC");
        newRep.setResidenceStreet("Via Cesare Battisti");
        newRep.setResidenceStreetNumber(18);
        newRep.setTelephoneNumber1("+393330000000");
        newRep.setTelephoneNumber2("+393330000001");
        newRep.setFaxNumber("0547000000");
        newRep.setEmail("mario.rossi@outlook.com");
        try {
            em.persist(newRep);
            em.getTransaction().commit();
        } catch (final Exception ex) {
            LOGGER.error("Error: insert operation failed.", ex);
            em.getTransaction().rollback();
            fail("Error: insert operation failed.");
        }

        // Read the row just inserted
        final TypedQuery<Representative> query = em.createQuery(
            "SELECT ref FROM Representative ref WHERE ref.codiceFiscale = :fiscalCode",
            Representative.class
        );
        query.setParameter("fiscalCode", fiscalCode);
        final Representative readRep = query.getSingleResult();
        assertTrue(readRep.equals(newRep));

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
        assertThrows(
            jakarta.persistence.NoResultException.class,
            () -> query.getSingleResult()
        );

    }

    @AfterAll
    static void tearDownAll() {
        em.close();
        provider.closeConnection();
    }

}
