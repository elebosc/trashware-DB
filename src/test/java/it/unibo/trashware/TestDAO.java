package it.unibo.trashware;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.unibo.trashware.model.dao.GenericDAO;
import it.unibo.trashware.model.dao.GenericDAOImpl;
import it.unibo.trashware.model.entities.Representative;

/**
 * Test class for {@link GenericDAO} testing.
 */
public class TestDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestDAO.class);

    private static GenericDAO<Representative, String> dao;

    @BeforeAll
    static void initAll() {
        try {
            dao = new GenericDAOImpl<>(Representative.class);
        } catch (final IOException ex) {
            final String errorMsg = "Error: could not establish a connection with the database.";
            LOGGER.error(errorMsg, ex);
            fail(errorMsg);
        }
    }
    
    /**
     * Tests basic create, read and delete operations implemented by the methods
     * {@link GenericDAO#add()}, {@link GenericDAO#getByID()} and {@link GenericDAO#delete()}
     */
    @Test
    void testCRD() {
        final Representative newRep = createRepresentativeObj();
        // Add representative to the table
        dao.add(newRep);
        // Check if the entity has been inserted in the table
        final Optional<Representative> readRep = dao.getByID(newRep.getFiscalCode());
        assertTrue(readRep.isPresent());
        assertEquals(newRep, readRep.get());
        // Remove the inserted entity
        dao.delete(newRep);
        // Check if the entity has been removed
        assertTrue(dao.getByID(newRep.getFiscalCode()).isEmpty());
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
