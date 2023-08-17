package it.unibo.trashware.services.impl;

import java.io.IOException;
import java.util.Optional;

import it.unibo.trashware.model.dao.GenericDAO;
import it.unibo.trashware.model.dao.GenericDAOImpl;
import it.unibo.trashware.model.entities.Component;
import it.unibo.trashware.model.entities.Cpu;
import it.unibo.trashware.services.api.InventoryService;

public class InventoryServiceImpl implements InventoryService {

    private GenericDAO<Cpu, String> cpuDAO;

    public InventoryServiceImpl() throws IOException {
        this.cpuDAO = new GenericDAOImpl<>(Cpu.class);
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

}
