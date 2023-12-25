package it.unibo.trashware.controller.impl;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import it.unibo.trashware.commons.FieldTags;
import it.unibo.trashware.controller.api.OperationsController;
import it.unibo.trashware.entities.Completion;
import it.unibo.trashware.entities.Delivery;
import it.unibo.trashware.entities.Operation;
import it.unibo.trashware.entities.OperationObjectComponent;
import it.unibo.trashware.entities.OperationObjectComponentId;
import it.unibo.trashware.entities.OperationObjectDescription;
import it.unibo.trashware.entities.OperationObjectDescriptionId;
import it.unibo.trashware.entities.OperationObjectPC;
import it.unibo.trashware.entities.OperationObjectPCId;
import it.unibo.trashware.entities.OperationObjectPeripheral;
import it.unibo.trashware.entities.OperationObjectPeripheralId;
import it.unibo.trashware.entities.Representation;
import it.unibo.trashware.entities.RepresentationId;
import it.unibo.trashware.entities.Representative;
import it.unibo.trashware.entities.Request;
import it.unibo.trashware.entities.Society;
import it.unibo.trashware.model.dao.GenericDAO;
import it.unibo.trashware.model.dao.GenericDAOImpl;
import it.unibo.trashware.model.provider.ConnectionProvider;
import it.unibo.trashware.model.provider.ConnectionProviderImpl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

public class OperationsControllerImpl implements OperationsController {

    private static final String DEFAULT_REQUEST_STATUS = "In lavorazione";
    private static final String REQUEST_COMPLETED = "Pronto per la consegna";
    private static final String DEVICES_DELIVERED = "Consegna effettuata";

    private EntityManager em;

    private GenericDAO<Operation, String> operationsDAO;
    private GenericDAO<Request, String> requestsDAO;
    private GenericDAO<Representative, String> representativesDAO;
    private GenericDAO<Society, String> societiesDAO;
    private GenericDAO<Representation, RepresentationId> representationsDAO;
    private GenericDAO<OperationObjectDescription, String> objectDescriptionsDAO;
    private GenericDAO<Completion, String> completionsDAO;
    private GenericDAO<Delivery, String> deliveriesDAO;
    private GenericDAO<OperationObjectPC, OperationObjectPCId> pcOperationLinksDAO;
    private GenericDAO<OperationObjectPeripheral, OperationObjectPeripheralId> peripheralOperationLinksDAO;
    private GenericDAO<OperationObjectComponent, OperationObjectComponentId> componentOperationLinksDAO;

    public OperationsControllerImpl() throws IOException {
        // Connect to the database
        final ConnectionProvider provider = new ConnectionProviderImpl();
        final Optional<EntityManager> response = provider.getConnection();
        if (response.isEmpty()) {
            throw new IOException("Error: operations controller could not establish a connection with the database.");
        }
        this.em = response.get();
        this.operationsDAO = new GenericDAOImpl<>(this.em, Operation.class);
        this.requestsDAO = new GenericDAOImpl<>(this.em, Request.class);
        this.representativesDAO = new GenericDAOImpl<>(this.em, Representative.class);
        this.societiesDAO = new GenericDAOImpl<>(this.em, Society.class);
        this.representationsDAO = new GenericDAOImpl<>(this.em, Representation.class);
        this.objectDescriptionsDAO = new GenericDAOImpl<>(this.em, OperationObjectDescription.class);
        this.completionsDAO = new GenericDAOImpl<>(this.em, Completion.class);
        this.deliveriesDAO = new GenericDAOImpl<>(this.em, Delivery.class);
        this.pcOperationLinksDAO = new GenericDAOImpl<>(this.em, OperationObjectPC.class);
        this.peripheralOperationLinksDAO = new GenericDAOImpl<>(this.em, OperationObjectPeripheral.class);
        this.componentOperationLinksDAO = new GenericDAOImpl<>(this.em, OperationObjectComponent.class);
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

    @Override
    public void registerRequestCompletion(String requestID, LocalDate date) {
        final Completion completion = new Completion();
        final Optional<Request> response = this.requestsDAO.getByID(requestID);
        if (response.isEmpty()) {
            throw new IllegalArgumentException("Error: no request with such ID exists.");
        }
        final Request request = response.get();
        // Update request state
        request.setStatus(REQUEST_COMPLETED);
        this.requestsDAO.update(request);
        // Register completion
        completion.setRequest(request);
        completion.setDate(date);
        this.completionsDAO.add(completion);
    }

    @Override
    public void registerDevicesDelivery(String requestID, LocalDate date) {
        final Delivery delivery = new Delivery();
        final Optional<Completion> searchedCompletion = this.completionsDAO.getByID(requestID);
        final Optional<Request> searchedRequest = this.requestsDAO.getByID(requestID);
        if (searchedRequest.isEmpty()) {
            throw new IllegalArgumentException("Error: no request with such ID exists.");
        }
        if (searchedCompletion.isEmpty()) {
            throw new IllegalArgumentException("Error: no request with such ID has been completed.");
        }
        final Request request = searchedRequest.get();
        final Completion completion = searchedCompletion.get();
        // Update request state
        request.setStatus(DEVICES_DELIVERED);
        this.requestsDAO.update(request);
        // Register delivery
        delivery.setCompletion(completion);
        delivery.setDate(date);
        this.deliveriesDAO.add(delivery);
    }

    @Override
    public void associatePCToOperation(String pcID, String operationID) {
        final OperationObjectPCId id = new OperationObjectPCId();
        id.setOperationID(operationID);
        id.setPcID(pcID);
        final OperationObjectPC link = new OperationObjectPC();
        link.setId(id);
        this.pcOperationLinksDAO.add(link);
    }

    @Override
    public void associatePeripheralToOperation(String peripheralID, String operationID) {
        final OperationObjectPeripheralId id = new OperationObjectPeripheralId();
        id.setOperationID(operationID);
        id.setPeripheralID(peripheralID);
        final OperationObjectPeripheral link = new OperationObjectPeripheral();
        link.setId(id);
        this.peripheralOperationLinksDAO.add(link);
    }

    @Override
    public void associateComponentToOperation(String componentID, String operationID) {
        final OperationObjectComponentId id = new OperationObjectComponentId();
        id.setOperationID(operationID);
        id.setComponentID(componentID);
        final OperationObjectComponent link = new OperationObjectComponent();
        link.setId(id);
        this.componentOperationLinksDAO.add(link);
    }

    @Override
    public List<Map<FieldTags, String>> getDonationsList() {
        Query query = this.em.createNativeQuery(
            "SELECT o.IDOperazione, ref.Nome, ref.Cognome, s.Nome, o.DataEffettuazione, ref.NumTelefono1, ref.NumTelefono2, ref.Fax, ref.Email\n" +
            "FROM operazioni o JOIN referente ref ON (o.CodiceFiscaleReferente = ref.CodiceFiscale)\n" +
            "LEFT OUTER JOIN (\n" +
                   "SELECT *\n" +
                   "FROM società, rappresentanza rap\n" +
            ") AS s ON (s.CodiceFiscaleReferente = ref.CodiceFiscale)\n" +
            "WHERE o.tipo = 'Donazione';\n"
        );

        List<Object[]> result = query.getResultList();
        final List<Map<FieldTags, String>> resultMaps = new LinkedList<>();
        for (final var entry : result) {

            final Map<FieldTags, String> entryMap = new HashMap<>();
            final String opID = entry[0].toString();

            entryMap.put(FieldTags.OPERATION_ID, opID);
            entryMap.put(FieldTags.REPRESENTATIVE, entry[1].toString() + " " + entry[2].toString());
            entryMap.put(FieldTags.SOCIETY, (entry[3] != null) ? entry[3].toString() : "");
            entryMap.put(FieldTags.EFFECTUATION_DATE, entry[4].toString());
            entryMap.put(
                FieldTags.PHONE_CONTACTS,
                entry[5].toString() + "\n" + ((entry[6] != null) ? entry[6].toString() : "")
            );
            entryMap.put(FieldTags.FAX, (entry[7] != null) ? entry[7].toString() : "");
            entryMap.put(FieldTags.EMAIL, (entry[8] != null) ? entry[8].toString() : "");

            /*
             * Get received devices counts
             */

            long count;
            
            // Count desktops
            query = this.em.createNativeQuery(
                "SELECT COUNT(*)\n" +
                "FROM oggetto_pc JOIN pc ON (oggetto_pc.IDPC = pc.IDPC)\n" + 
                "WHERE (IDOperazione = ?1) AND (Tipo = 'Desktop');"
            );
            query.setParameter(1, opID);
            count = (long) query.getSingleResult();
            entryMap.put(FieldTags.NUM_DESKTOPS, String.valueOf(count));

            // Count laptops
            query = this.em.createNativeQuery(
                "SELECT COUNT(*)\n" +
                "FROM oggetto_pc JOIN pc ON (oggetto_pc.IDPC = pc.IDPC)\n" + 
                "WHERE (IDOperazione = ?1) AND (Tipo = 'Laptop');"
            );
            query.setParameter(1, opID);
            count = (long) query.getSingleResult();
            entryMap.put(FieldTags.NUM_LAPTOPS, String.valueOf(count));

            // Count monitors
            query = this.em.createNativeQuery(
                "SELECT COUNT(*)\n" +
                "FROM oggetto_periferica o JOIN periferiche p ON (o.IDPeriferica = p.IDPeriferica)\n" +
                "WHERE (IDOperazione = ?1) AND (Tipo = 'Monitor');"
            );
            query.setParameter(1, opID);
            count = (long) query.getSingleResult();
            entryMap.put(FieldTags.NUM_MONITORS, String.valueOf(count));

            // Count keyboards
            query = this.em.createNativeQuery(
                "SELECT COUNT(*)\n" +
                "FROM oggetto_periferica o JOIN periferiche p ON (o.IDPeriferica = p.IDPeriferica)\n" +
                "WHERE (IDOperazione = ?1) AND (Tipo = 'Tastiera');"
            );
            query.setParameter(1, opID);
            count = (long) query.getSingleResult();
            entryMap.put(FieldTags.NUM_KEYBOARDS, String.valueOf(count));

            // Count mouse
            query = this.em.createNativeQuery(
                "SELECT COUNT(*)\n" +
                "FROM oggetto_periferica o JOIN periferiche p ON (o.IDPeriferica = p.IDPeriferica)\n" +
                "WHERE (IDOperazione = ?1) AND (Tipo = 'Mouse');"
            );
            query.setParameter(1, opID);
            count = (long) query.getSingleResult();
            entryMap.put(FieldTags.NUM_MOUSE, String.valueOf(count));

            // Count other peripherals
            query = this.em.createNativeQuery(
                "SELECT COUNT(*) as 'altre periferiche'\n" +
                "FROM oggetto_periferica o JOIN periferiche p ON (o.IDPeriferica = p.IDPeriferica)\n" +
                "WHERE (IDOperazione = ?1) AND (Tipo NOT IN ('Monitor', 'Tastiera', 'Mouse'));"
            );
            query.setParameter(1, opID);
            count = (long) query.getSingleResult();
            entryMap.put(FieldTags.NUM_OTHER_PERIPHERALS, String.valueOf(count));

            // Count components
            query = this.em.createNativeQuery(
                "SELECT COUNT(*)\n" +
                "FROM oggetto_componente\n" +
                "WHERE (IDOperazione = ?1);"
            );
            query.setParameter(1, opID);
            count = (long) query.getSingleResult();
            entryMap.put(FieldTags.NUM_COMPONENTS, String.valueOf(count));

            resultMaps.add(entryMap);
        }

        return resultMaps;
    }

}
