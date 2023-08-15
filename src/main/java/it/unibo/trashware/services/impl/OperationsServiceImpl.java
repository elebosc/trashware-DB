package it.unibo.trashware.services.impl;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

import it.unibo.trashware.model.dao.GenericDAO;
import it.unibo.trashware.model.dao.GenericDAOImpl;
import it.unibo.trashware.model.entities.Operation;
import it.unibo.trashware.model.entities.Representative;
import it.unibo.trashware.model.entities.Society;
import it.unibo.trashware.services.api.OperationsService;

/**
 * Implementation of {@link OperationsService}.
 */
public class OperationsServiceImpl implements OperationsService {

    private GenericDAO<Operation, String> operationsDAO;
    private GenericDAO<Representative, String> representativesDAO;
    private GenericDAO<Society, String> societiesDAO;

    /**
     * Creates a new instance of {@link OperationsService}.
     * @throws IOException if an error occurs while trying to create a connection to the database.
     */
    public OperationsServiceImpl() throws IOException {
        this.operationsDAO = new GenericDAOImpl<>(Operation.class);
        this.representativesDAO = new GenericDAOImpl<>(Representative.class);
        this.societiesDAO = new GenericDAOImpl<>(Society.class);
    }

    @Override
    public void addDonation(String id, LocalDate date, Optional<String> notes, String representativeID) {
        final Operation op = new Operation();
        op.setOperationID(id);
        op.setType("Donazione");
        op.setDate(date);
        // op.setRepresentativeFiscalCode(this.representativesDAO.getByID(representativeID));
        notes.ifPresent((value) -> op.setNotes(value));
        // Request operation insertion
        this.operationsDAO.add(op);
    }

    @Override
    public void addRequest(String id, String type, String reason, LocalDate date, LocalDate deadline, int priorityLevel,
            Optional<String> notes, String representativeID) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addRequest'");
    }

    @Override
    public void addRepresentative(String fiscalCode, String name, String surname, String birthplace, LocalDate birthday,
            String residenceCity, String residenceCAP, String residenceProvince, String residenceStreet,
            int residenceStreetNumber, String telephoneNumber1, Optional<String> telephoneNumber2,
            Optional<String> faxNumber, Optional<String> email) {
        // Create representative entity
        final Representative rep = new Representative();
        rep.setFiscalCode(fiscalCode);
        rep.setName(name);
        rep.setSurname(surname);
        rep.setBirthplace(birthplace);
        rep.setBirthday(birthday);
        rep.setResidenceCity(residenceCity);
        rep.setResidenceCAP(residenceCAP);
        rep.setResidenceProvince(residenceProvince);
        rep.setResidenceStreet(residenceStreet);
        rep.setResidenceStreetNumber(Integer.valueOf(residenceStreetNumber));
        rep.setTelephoneNumber1(telephoneNumber1);
        telephoneNumber2.ifPresent((number) -> rep.setTelephoneNumber2(number));
        faxNumber.ifPresent((number) -> rep.setFaxNumber(number));
        email.ifPresent((address) -> rep.setEmail(address));
        // Request representative insertion
        this.representativesDAO.add(rep);
    }

    @Override
    public void addSociety(String VATNumber, String fiscalCode, String name, String registeredOfficeCity,
            String registeredOfficeCAP, String registeredOfficeProvince, String registeredOfficeStreet,
            int registeredOfficeStreetNumber) {
        final Society society = new Society();
        society.setVATNumber(VATNumber);
        society.setFiscalCode(fiscalCode);
        society.setName(name);
        society.setRegisteredOfficeCity(registeredOfficeCity);
        society.setRegisteredOfficeCAP(registeredOfficeCAP);
        society.setRegisteredOfficeProvince(registeredOfficeProvince);
        society.setRegisteredOfficeStreet(registeredOfficeStreet);
        society.setRegisteredOfficeStreetNumber(registeredOfficeStreetNumber);
        // Request society insertion
        this.societiesDAO.add(society);
    }

    @Override
    public void addObjectDescription(String operationID, int lineNumber, String type, int quantity,
            Optional<String> notes) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addObjectDescription'");
    }
    
}
