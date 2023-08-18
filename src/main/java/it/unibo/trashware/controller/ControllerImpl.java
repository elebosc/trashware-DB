package it.unibo.trashware.controller;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Optional;

import it.unibo.trashware.services.api.InventoryService;
import it.unibo.trashware.services.api.OperationsService;
import it.unibo.trashware.services.api.WorkShiftsService;
import it.unibo.trashware.services.impl.InventoryServiceImpl;
import it.unibo.trashware.services.impl.OperationsServiceImpl;
import it.unibo.trashware.services.impl.WorkShiftsServiceImpl;

/**
 * This class implements the controller of the application.
 */
public class ControllerImpl implements Controller {
    
    private OperationsService opService;
    private InventoryService inventoryService;
    private WorkShiftsService workShiftsService;

    /**
     * Creates a new controller.
     * @throws IOException if an error occurs while trying to create a connection to the database.
     */
    public ControllerImpl() throws IOException {
        this.opService = new OperationsServiceImpl();
        this.inventoryService = new InventoryServiceImpl();
        this.workShiftsService = new WorkShiftsServiceImpl();
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
    public void registerRequestCompletion(String requestID, LocalDate date) {
        this.opService.registerRequestCompletion(requestID, date);
    }

    @Override
    public void registerDevicesDelivery(String requestID, LocalDate date) {
        this.opService.registerDevicesDelivery(requestID, date);
    }

    @Override
    public void addCPU(String componentID, String brand, String model, Optional<String> notes,
            int architecture) {
        this.inventoryService.addCPU(componentID, brand, model, notes, architecture);
    }

    @Override
    public void addRAM(String componentID, String brand, String model, Optional<String> notes, int size) {
        this.inventoryService.addRAM(componentID, brand, model, notes, size);
    }

    @Override
    public void addMassStorage(String componentID, String brand, String model, Optional<String> notes,
            String massStorageType, int size) {
        this.inventoryService.addMassStorage(componentID, brand, model, notes, massStorageType, size);
    }

    @Override
    public void addChassis(String componentID, String brand, String model, Optional<String> notes,
            String color) {
        this.inventoryService.addChassis(componentID, brand, model, notes, color);
    }

    @Override
    public void addComponent(String componentID, String type, String brand, String model, Optional<String> notes) {
        this.inventoryService.addComponent(componentID, type, brand, model, notes);
    }

    @Override
    public void addPeripheral(String peripheralID, String type, String brand, String model, String connectivity,
            Optional<String> notes) {
        this.inventoryService.addPeripheral(peripheralID, type, brand, model, connectivity, notes);
    }

    @Override
    public void addMonitor(String peripheralID, String brand, String model, String connectivity,
            Optional<String> notes, String monitorType, int size, String aspectRatio, boolean isVGASupported,
            boolean isDVISupported, boolean hasEmbeddedAudio) {
        this.inventoryService.addMonitor(peripheralID, brand, model, connectivity, notes, monitorType,
                size, aspectRatio, isVGASupported, isDVISupported, hasEmbeddedAudio);
    }

    @Override
    public void addDesktopPC(String pcID, String cpuID, String massStorage01ID, Optional<String> massStorage02ID,
            String ramModule01ID, Optional<String> ramModule02ID, Optional<String> ramModule03ID,
            Optional<String> ramModule04ID, boolean isEthernetSupported, boolean isWiFiSupported,
            boolean isBluetoothSupported, String chassisID, Optional<String> monitorID, Optional<String> keyboardID,
            Optional<String> mouseID, Optional<String> speakersID, Optional<String> notes) {

        this.inventoryService.addDesktopPC(pcID, cpuID, massStorage01ID, massStorage02ID, ramModule01ID, ramModule02ID,
                ramModule03ID, ramModule04ID, isEthernetSupported, isWiFiSupported, isBluetoothSupported, chassisID, monitorID,
                keyboardID, mouseID, speakersID, notes);

    }

    @Override
    public void addLaptop(String pcID, String cpuID, String massStorage01ID, Optional<String> massStorage02ID,
            String ramModule01ID, Optional<String> ramModule02ID, Optional<String> ramModule03ID,
            Optional<String> ramModule04ID, boolean isEthernetSupported, boolean isWiFiSupported,
            boolean isBluetoothSupported, String brand, String model, int size, String color, Optional<String> notes) {

        this.inventoryService.addLaptop(pcID, cpuID, massStorage01ID, massStorage02ID, ramModule01ID, ramModule02ID,
                ramModule03ID, ramModule04ID, isEthernetSupported, isWiFiSupported, isBluetoothSupported, brand, model,
                size, color, notes);

    }

    @Override
    public void addOperatingSystem(String pcID, String name, String version, LocalDate lastUpdateDate) {
        this.inventoryService.addOperatingSystem(pcID, name, version, lastUpdateDate);
    }

    @Override
    public void associatePCToOperation(String pcID, String operationID) {
        this.opService.associatePCToOperation(pcID, operationID);
    }

    @Override
    public void associatePeripheralToOperation(String peripheralID, String operationID) {
        this.opService.associatePeripheralToOperation(peripheralID, operationID);
    }

    @Override
    public void associateComponentToOperation(String componentID, String operationID) {
        this.opService.associateComponentToOperation(componentID, operationID);
    }

    @Override
    public void addOperator(String fiscalCode, String name, String surname, String birthplace, LocalDate birthday,
            String residenceCity, String residenceCAP, String residenceProvince, String residenceStreet,
            int residenceStreetNumber, String telephoneNumber1, Optional<String> telephoneNumber2,
            Optional<String> email) {
        this.workShiftsService.addOperator(fiscalCode, name, surname, birthplace, birthday, residenceCity, residenceCAP,
                residenceProvince, residenceStreet, residenceStreetNumber, telephoneNumber1, telephoneNumber2, email);
    }

    @Override
    public void registerWorkShift(String operatorFiscalCode, LocalDate date, Instant startTime, Instant endTime) {
        this.workShiftsService.registerWorkShift(operatorFiscalCode, date, startTime, endTime);
    }

    @Override
    public void registerTask(String operatorFiscalCode, LocalDate date, Instant startTime, int taskNumber,
            String description, Optional<String> operationID) {
        this.workShiftsService.registerTask(operatorFiscalCode, date, startTime, taskNumber, description, operationID);
    }

}
