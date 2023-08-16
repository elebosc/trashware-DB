package it.unibo.trashware.services.impl;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

import it.unibo.trashware.model.dao.GenericDAO;
import it.unibo.trashware.model.dao.GenericDAOImpl;
import it.unibo.trashware.model.entities.Operation;
import it.unibo.trashware.model.entities.OperationObjectDescription;
import it.unibo.trashware.model.entities.OperationObjectDescriptionId;
import it.unibo.trashware.model.entities.Representation;
import it.unibo.trashware.model.entities.RepresentationId;
import it.unibo.trashware.model.entities.Representative;
import it.unibo.trashware.model.entities.Request;
import it.unibo.trashware.model.entities.Society;
import it.unibo.trashware.services.api.OperationsService;

/**
 * Implementation of {@link OperationsService}.
 */
public class OperationsServiceImpl implements OperationsService {

    private static String DEFAULT_REQUEST_STATUS = "In lavorazione";

    private GenericDAO<Operation, String> operationsDAO;
    private GenericDAO<Request, String> requestsDAO;
    private GenericDAO<Representative, String> representativesDAO;
    private GenericDAO<Society, String> societiesDAO;
    private GenericDAO<Representation, RepresentationId> representationsDAO;
    private GenericDAO<OperationObjectDescription, String> objectDescriptionsDAO;

    /**
     * Creates a new instance of {@link OperationsService}.
     * @throws IOException if an error occurs while trying to create a connection to the database.
     */
    public OperationsServiceImpl() throws IOException {
        this.operationsDAO = new GenericDAOImpl<>(Operation.class);
        this.requestsDAO = new GenericDAOImpl<>(Request.class);
        this.representativesDAO = new GenericDAOImpl<>(Representative.class);
        this.societiesDAO = new GenericDAOImpl<>(Society.class);
        this.representationsDAO = new GenericDAOImpl<>(Representation.class);
        this.objectDescriptionsDAO = new GenericDAOImpl<>(OperationObjectDescription.class);
    }

    @Override
    public void addDonation(String id, LocalDate date, Optional<String> notes, String representativeID) {
        final Operation op = createOperationObj(id, "Donazione", date, notes, representativeID);
        // Operation insertion
        this.operationsDAO.add(op);
    }

    @Override
    public void addRequest(String id, String requestType, String reason, LocalDate date, LocalDate deadline, int priorityLevel,
            Optional<String> notes, String representativeID) {
        final Operation operation = createOperationObj(id, "Richiesta", date, notes, representativeID);
        final Request request = new Request();
        request.setOperationID(operation);  // sets the reference to the Operation object containing the general info about the operation
        request.setType(requestType);
        request.setReason(reason);
        request.setDeadlineDate(deadline);
        request.setStatus(DEFAULT_REQUEST_STATUS);
        request.setPriorityLevel(priorityLevel);
        // Request insertion
        this.requestsDAO.add(request);
    }

    private Operation createOperationObj(String id, String type, LocalDate date, Optional<String> notes, String representativeID) {
        final Operation op = new Operation();
        op.setOperationID(id);
        op.setType(type);
        op.setDate(date);
        notes.ifPresent((value) -> op.setNotes(value));
        final Optional<Representative> representative = this.representativesDAO.getByID(representativeID);
        if (representative.isEmpty()) {
            throw new IllegalArgumentException("Error: a representative with the specified ID does not exist.");
        }
        op.setRepresentativeFiscalCode(representative.get());
        return op;
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
        // Representative insertion
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
        // Society insertion
        this.societiesDAO.add(society);
    }

    @Override
    public void addRepresentation(String societyVATNumber, String representativeFiscalCode, String representativeTitle) {
        final RepresentationId representationID = new RepresentationId();
        representationID.setSocietyVATNumber(societyVATNumber);
        representationID.setRepresentativeFiscalCode(representativeFiscalCode);
        final Representation representation = new Representation();
        representation.setId(representationID);
        representation.setRepresentativeTitle(representativeTitle);
        // Representation insertion
        this.representationsDAO.add(representation);
    }

    @Override
    public void addObjectDescription(String operationID, int lineNumber, String type, int quantity,
            Optional<String> notes) {
        final OperationObjectDescription objDescription = new OperationObjectDescription();
        final OperationObjectDescriptionId objDescriptionID = new OperationObjectDescriptionId();
        objDescriptionID.setOperationID(operationID);
        objDescriptionID.setLineNumber(lineNumber);
        objDescription.setId(objDescriptionID);
        objDescription.setType(type);
        objDescription.setQuantity(quantity);
        notes.ifPresent(value -> objDescription.setNotes(value));
        // Object description insertion
        this.objectDescriptionsDAO.add(objDescription);
    }
    
}
