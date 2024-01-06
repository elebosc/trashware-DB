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

    private static final String IN_PROGRESS = "In lavorazione";
    private static final String COMPLETED = "Pronto";
    private static final String DELIVERED = "Consegnato";

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
        request.setStatus(IN_PROGRESS);
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
            throw new IllegalArgumentException("Errore: non esiste un referente con l'ID specificato.");
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
            throw new IllegalArgumentException("Errore: non esiste una richiesta con l'ID specificato.");
        }
        final Request request = response.get();
        // Update request state
        request.setStatus(COMPLETED);
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
            throw new IllegalArgumentException("Errore: non esiste una richiesta con l'ID specificato.");
        }
        if (searchedCompletion.isEmpty()) {
            throw new IllegalArgumentException("Errore: la richiesta con l'ID specificato non risulta completata.");
        }
        final Request request = searchedRequest.get();
        final Completion completion = searchedCompletion.get();
        // Update request state
        request.setStatus(DELIVERED);
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
            "SELECT o.IDOperazione, ref.Nome, ref.Cognome, rap.Nome as NomeSocietà, o.DataEffettuazione, ref.NumTelefono1, ref.NumTelefono2, ref.Fax, ref.Email, o.Note\n" +
            "FROM (\n" +
                "operazioni o JOIN referente ref ON (o.CodiceFiscaleReferente = ref.CodiceFiscale)\n" +
                "LEFT OUTER JOIN (\n" +
                    "SELECT *\n" +
                    "FROM società s JOIN rappresentanza r ON (s.PartitaIVA = r.PartitaIVASocietà)\n" +
                ") AS rap ON (rap.CodiceFiscaleReferente = ref.CodiceFiscale)\n" +
            ")\n" +
            "WHERE o.tipo = 'Donazione';"
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
            entryMap.put(FieldTags.DETAILS, (entry[9] != null) ? entry[9].toString() : "");

            resultMaps.add(entryMap);
        }

        return resultMaps;
    }

    @Override
    public List<Map<FieldTags, String>> getRequestsList(final String requestStatus) {

        Query query = this.em.createNativeQuery(
            "SELECT ric.IDRichiesta, ref.Nome, ref.Cognome, rap.Nome AS NomeSocietà, ric.Tipo, ric.Motivazione, ric.Note, ric.DataEffettuazione, ric.DataLimite, ric.LivelloPriorità, ref.NumTelefono1, ref.NumTelefono2, ref.Fax, ref.Email\n" +
            "FROM ((\n" +
                    "SELECT r.IDRichiesta, r.Tipo, r.Motivazione, o.Note, o.DataEffettuazione , r.DataLimite, r.LivelloPriorità, o.CodiceFiscaleReferente\n" +
                    "FROM richieste r JOIN operazioni o ON (r.IDRichiesta = o.IDOperazione)\n" +
                    "WHERE r.Stato = ?1\n" +
                ") AS ric JOIN referente ref ON (ric.CodiceFiscaleReferente = ref.CodiceFiscale)\n" +
                "LEFT OUTER JOIN (\n" +
                    "SELECT *\n" +
                    "FROM società s JOIN rappresentanza r ON (s.PartitaIVA = r.PartitaIVASocietà)\n" +
                ") AS rap ON (rap.CodiceFiscaleReferente = ref.CodiceFiscale)\n" +
            ")\n" +
            "ORDER BY ric.LivelloPriorità DESC;"
        );
        query.setParameter(1, requestStatus);
        List<Object[]> result = query.getResultList();

        final List<Map<FieldTags, String>> resultMaps = new LinkedList<>();
        for (final var entry : result) {

            final Map<FieldTags, String> entryMap = new HashMap<>();
            final String opID = entry[0].toString();

            entryMap.put(FieldTags.OPERATION_ID, opID);
            entryMap.put(FieldTags.REPRESENTATIVE, entry[1].toString() + " " + entry[2].toString());
            entryMap.put(FieldTags.SOCIETY, (entry[3] != null) ? entry[3].toString() : "");
            entryMap.put(FieldTags.REQUEST_TYPE, entry[4].toString());
            entryMap.put(FieldTags.REASON, entry[5].toString());
            entryMap.put(FieldTags.DETAILS, (entry[6] != null) ? entry[6].toString() : "");
            entryMap.put(FieldTags.EFFECTUATION_DATE, entry[7].toString());
            entryMap.put(FieldTags.DEADLINE, entry[8].toString());
            entryMap.put(FieldTags.PRIORITY, entry[9].toString());
            entryMap.put(
                FieldTags.PHONE_CONTACTS,
                entry[10].toString() + "\n" + ((entry[11] != null) ? entry[11].toString() : "")
            );
            entryMap.put(FieldTags.FAX, (entry[12] != null) ? entry[13].toString() : "");
            entryMap.put(FieldTags.EMAIL, (entry[13] != null) ? entry[14].toString() : "");

            resultMaps.add(entryMap);
        }

        return resultMaps;
    }

}
