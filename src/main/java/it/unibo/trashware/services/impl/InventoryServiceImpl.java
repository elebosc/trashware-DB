package it.unibo.trashware.services.impl;

import java.io.IOException;
import java.util.Optional;

import it.unibo.trashware.model.dao.GenericDAO;
import it.unibo.trashware.model.dao.GenericDAOImpl;
import it.unibo.trashware.model.entities.Chassis;
import it.unibo.trashware.model.entities.Component;
import it.unibo.trashware.model.entities.Cpu;
import it.unibo.trashware.model.entities.MassStorageDevice;
import it.unibo.trashware.model.entities.RAMModule;
import it.unibo.trashware.services.api.InventoryService;

public class InventoryServiceImpl implements InventoryService {

    private GenericDAO<Cpu, String> cpuDAO;
    private GenericDAO<RAMModule, String> ramDAO;
    private GenericDAO<MassStorageDevice, String> massStorageDAO;
    private GenericDAO<Chassis, String> chassisDAO;
    private GenericDAO<Component, String> otherComponentsDAO;

    public InventoryServiceImpl() throws IOException {
        this.cpuDAO = new GenericDAOImpl<>(Cpu.class);
        this.ramDAO = new GenericDAOImpl<>(RAMModule.class);
        this.massStorageDAO = new GenericDAOImpl<>(MassStorageDevice.class);
        this.chassisDAO = new GenericDAOImpl<>(Chassis.class);
        this.otherComponentsDAO = new GenericDAOImpl<>(Component.class);
    }

    private Component createComponentObject(String componentID, String type, String brand, String model,
        Optional<String> notes) {
        final Component component = new Component();
        component.setComponentID(componentID);
        component.setType(type);
        component.setBrand(brand);
        component.setModel(model);
        notes.ifPresent(value -> component.setNotes(value));
        return component;
    }

    @Override
    public void addCPU(String componentID, String type, String brand, String model, Optional<String> notes,
            int architecture) {
        final Component component = createComponentObject(componentID, type, brand, model, notes);
        final Cpu cpu = new Cpu();
        cpu.setComponent(component);
        cpu.setArchitecture(architecture);
        // CPU insertion
        this.cpuDAO.add(cpu);
    }

    @Override
    public void addRAM(String componentID, String type, String brand, String model, Optional<String> notes, int size) {
        final Component component = createComponentObject(componentID, type, brand, model, notes);
        final RAMModule ramModule = new RAMModule();
        ramModule.setComponent(component);
        ramModule.setSize(size);
        // RAM module insertion
        this.ramDAO.add(ramModule);
    }

    @Override
    public void addMassStorage(String componentID, String type, String brand, String model, Optional<String> notes,
            String massStorageType, int size) {
        final Component component = createComponentObject(componentID, type, brand, model, notes);
        final MassStorageDevice massStorage = new MassStorageDevice();
        massStorage.setComponent(component);
        massStorage.setType(massStorageType);
        massStorage.setSize(size);
        // Mass storage device insertion
        this.massStorageDAO.add(massStorage);
    }

    @Override
    public void addChassis(String componentID, String type, String brand, String model, Optional<String> notes,
            String color) {
        final Component component = createComponentObject(componentID, type, brand, model, notes);
        final Chassis chassis = new Chassis();
        chassis.setComponent(component);
        chassis.setColor(color);
        // Chassis insertion
        this.chassisDAO.add(chassis);
    }

    @Override
    public void addComponent(String componentID, String type, String brand, String model, Optional<String> notes) {
        // Component insertion
        this.otherComponentsDAO.add(createComponentObject(componentID, type, brand, model, notes));
    }

}
