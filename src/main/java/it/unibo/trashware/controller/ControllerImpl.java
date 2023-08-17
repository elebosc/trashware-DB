package it.unibo.trashware.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

import it.unibo.trashware.services.api.InventoryService;
import it.unibo.trashware.services.api.OperationsService;
import it.unibo.trashware.services.impl.InventoryServiceImpl;
import it.unibo.trashware.services.impl.OperationsServiceImpl;

/**
 * This class implements the controller of the application.
 */
public class ControllerImpl implements Controller {
    
    private OperationsService opService;
    private InventoryService inventoryService;

    /**
     * Creates a new controller.
     * @throws IOException if an error occurs while trying to create a connection to the database.
     */
    public ControllerImpl() throws IOException {
        this.opService = new OperationsServiceImpl();
        this.inventoryService = new InventoryServiceImpl();
    }

    @Override
    public void addRepresentative(String fiscalCode, String name, String surname, String birthplace, LocalDate birthday,
            String residenceCity, String residenceCAP, String residenceProvince, String residenceStreet,
            int residenceStreetNumber, String telephoneNumber1, Optional<String> telephoneNumber2,
            Optional<String> faxNumber, Optional<String> email) {
        opService.addRepresentative(
            fiscalCode, name, surname, birthplace, birthday, residenceCity, residenceCAP, residenceProvince,
            residenceStreet, residenceStreetNumber, telephoneNumber1, telephoneNumber2, faxNumber, email
        );
    }

    @Override
    public void addSociety(String VATNumber, String fiscalCode, String name, String registeredOfficeCity,
            String registeredOfficeCAP, String registeredOfficeProvince, String registeredOfficeStreet,
            int registeredOfficeStreetNumber) {
        opService.addSociety(
            VATNumber, fiscalCode, name, registeredOfficeCity, registeredOfficeCAP, registeredOfficeProvince,
            registeredOfficeStreet, registeredOfficeStreetNumber
        );
    }

    @Override
    public void addRepresentation(String societyVATNumber, String representativeFiscalCode, String representativeTitle) {
        opService.addRepresentation(societyVATNumber, representativeFiscalCode, representativeTitle);
    }

    @Override
    public void addDonation(String id, LocalDate date, Optional<String> notes, String representativeID) {
        this.opService.addDonation(id, date, notes, representativeID);
    }

    @Override
    public void addRequest(String id, String type, String reason, LocalDate date, LocalDate deadline, int priorityLevel,
            Optional<String> notes, String representativeID) {
        this.opService.addRequest(id, type, reason, date, deadline, priorityLevel, notes, representativeID);
    }

    @Override
    public void addObjectDescription(String operationID, int lineNumber, String type, int quantity,
            Optional<String> notes) {
        this.opService.addObjectDescription(operationID, lineNumber, type, quantity, notes);
    }

    @Override
    public void addCPU(String componentID, String type, String brand, String model, Optional<String> notes,
            int architecture) {
        this.inventoryService.addCPU(componentID, type, brand, model, notes, architecture);
    }

}
