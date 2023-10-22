package it.unibo.trashware.controller.impl;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

import it.unibo.trashware.controller.api.InventoryController;
import it.unibo.trashware.model.dao.GenericDAO;
import it.unibo.trashware.model.dao.GenericDAOImpl;
import it.unibo.trashware.model.entities.Chassis;
import it.unibo.trashware.model.entities.Component;
import it.unibo.trashware.model.entities.Cpu;
import it.unibo.trashware.model.entities.DesktopPC;
import it.unibo.trashware.model.entities.Laptop;
import it.unibo.trashware.model.entities.MassStorageDevice;
import it.unibo.trashware.model.entities.Monitor;
import it.unibo.trashware.model.entities.OperatingSystem;
import it.unibo.trashware.model.entities.OperatingSystemId;
import it.unibo.trashware.model.entities.OtherPCComponent;
import it.unibo.trashware.model.entities.PC;
import it.unibo.trashware.model.entities.Peripheral;
import it.unibo.trashware.model.entities.RAMModule;

public class InventoryControllerImpl implements InventoryController {
    
    private GenericDAO<Cpu, String> cpuDAO;
    private GenericDAO<RAMModule, String> ramDAO;
    private GenericDAO<MassStorageDevice, String> massStorageDAO;
    private GenericDAO<Chassis, String> chassisDAO;
    private GenericDAO<Component, String> otherComponentsDAO;
    private GenericDAO<Peripheral, String> peripheralsDAO;
    private GenericDAO<Monitor, String> monitorsDAO;
    private GenericDAO<PC, String> pcsDAO; 
    private GenericDAO<DesktopPC, String> desktopPCsDAO;
    private GenericDAO<Laptop, String> laptopsDAO;
    private GenericDAO<OperatingSystem, OperatingSystemId> operatingSystemsDAO;
    private GenericDAO<OtherPCComponent, String> otherPCComponentsDAO;

    public InventoryControllerImpl() throws IOException {
        this.cpuDAO = new GenericDAOImpl<>(Cpu.class);
        this.ramDAO = new GenericDAOImpl<>(RAMModule.class);
        this.massStorageDAO = new GenericDAOImpl<>(MassStorageDevice.class);
        this.chassisDAO = new GenericDAOImpl<>(Chassis.class);
        this.otherComponentsDAO = new GenericDAOImpl<>(Component.class);
        this.peripheralsDAO = new GenericDAOImpl<>(Peripheral.class);
        this.monitorsDAO = new GenericDAOImpl<>(Monitor.class);
        this.pcsDAO = new GenericDAOImpl<>(PC.class);
        this.desktopPCsDAO = new GenericDAOImpl<>(DesktopPC.class);
        this.laptopsDAO = new GenericDAOImpl<>(Laptop.class);
        this.operatingSystemsDAO = new GenericDAOImpl<>(OperatingSystem.class);
        this.otherPCComponentsDAO = new GenericDAOImpl<>(OtherPCComponent.class);
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
    public void addCPU(String componentID, String brand, String model, Optional<String> notes,
            int architecture) {
        final Component component = createComponentObject(componentID, "CPU", brand, model, notes);
        final Cpu cpu = new Cpu();
        cpu.setComponent(component);
        cpu.setArchitecture(architecture);
        // CPU insertion
        this.cpuDAO.add(cpu);
    }

    @Override
    public void addRAM(String componentID, String brand, String model, Optional<String> notes, int size) {
        final Component component = createComponentObject(componentID, "RAM", brand, model, notes);
        final RAMModule ramModule = new RAMModule();
        ramModule.setComponent(component);
        ramModule.setSize(size);
        // RAM module insertion
        this.ramDAO.add(ramModule);
    }

    @Override
    public void addMassStorage(String componentID, String brand, String model, Optional<String> notes,
            String massStorageType, int size) {
        final Component component = createComponentObject(componentID, "Memoria di massa", brand, model, notes);
        final MassStorageDevice massStorage = new MassStorageDevice();
        massStorage.setComponent(component);
        massStorage.setType(massStorageType);
        massStorage.setSize(size);
        // Mass storage device insertion
        this.massStorageDAO.add(massStorage);
    }

    @Override
    public void addChassis(String componentID, String brand, String model, Optional<String> notes,
            String color) {
        final Component component = createComponentObject(componentID, "Case", brand, model, notes);
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

    private Peripheral createPeripheralObject(String peripheralID, String type, String brand, String model, String connectivity,
            Optional<String> notes) {
        final Peripheral peripheral = new Peripheral();
        peripheral.setPeripheralID(peripheralID);
        peripheral.setType(type);
        peripheral.setBrand(brand);
        peripheral.setModel(model);
        peripheral.setConnectivity(connectivity);
        notes.ifPresent(value -> peripheral.setNotes(value));
        return peripheral;
    }

    @Override
    public void addPeripheral(String peripheralID, String type, String brand, String model, String connectivity,
            Optional<String> notes) {
        // Peripheral insertion
        this.peripheralsDAO.add(createPeripheralObject(peripheralID, type, brand, model, connectivity, notes));
    }

    @Override
    public void addMonitor(String peripheralID, String brand, String model, String connectivity,
            Optional<String> notes, String monitorType, int size, String aspectRatio, boolean isVGASupported,
            boolean isDVISupported, boolean hasEmbeddedAudio) {
        final Peripheral peripheral = createPeripheralObject(peripheralID, "Monitor", brand, model, connectivity, notes);
        final Monitor monitor = new Monitor();
        monitor.setPeripheral(peripheral);
        monitor.setType(monitorType);
        monitor.setSize(size);
        monitor.setAspectRatio(aspectRatio);
        monitor.setIsVGASupported(isVGASupported);
        monitor.setIsDVISupported(isDVISupported);
        monitor.setHasEmbeddedAudio(hasEmbeddedAudio);
        // Monitor insertion
        this.monitorsDAO.add(monitor);
    }

    @Override
    public void addDesktopPC(String pcID, String cpuID, String massStorage01ID, Optional<String> massStorage02ID,
            String ramModule01ID, Optional<String> ramModule02ID, Optional<String> ramModule03ID,
            Optional<String> ramModule04ID, boolean isEthernetSupported, boolean isWiFiSupported,
            boolean isBluetoothSupported, String chassisID, Optional<String> monitorID, Optional<String> keyboardID,
            Optional<String> mouseID, Optional<String> speakersID, Optional<String> notes) {

        final PC pc = createPCObject(pcID, cpuID, massStorage01ID, massStorage02ID, ramModule01ID, ramModule02ID,
                ramModule03ID, ramModule04ID, isEthernetSupported, isWiFiSupported, isBluetoothSupported, notes);
        pc.setType("Desktop");

        final DesktopPC desktopPC = new DesktopPC();
        desktopPC.setPc(pc);
        Optional<Chassis> searchedchassis = this.chassisDAO.getByID(chassisID);
        searchedchassis.ifPresentOrElse(
            (chassis) -> desktopPC.setChassisID(chassis),
            () -> new IllegalArgumentException("A chassis with such ID does not exist.")
        );
        if (monitorID.isPresent()) {
            Optional<Monitor> searchedMonitor = this.monitorsDAO.getByID(monitorID.get());
            searchedMonitor.ifPresentOrElse(
                (monitor) -> desktopPC.setMonitorID(monitor),
                () -> new IllegalArgumentException("A monitor with such ID does not exist.")
            );
        }
        if (keyboardID.isPresent()) {
            Optional<Peripheral> searchedKeyboard = this.peripheralsDAO.getByID(keyboardID.get());
            searchedKeyboard.ifPresentOrElse(
                (keyboard) -> desktopPC.setKeyboardID(keyboard),
                () -> new IllegalArgumentException("A keyboard with such ID does not exist.")
            );
        }
        if (mouseID.isPresent()) {
            Optional<Peripheral> searchedMouse = this.peripheralsDAO.getByID(mouseID.get());
            searchedMouse.ifPresentOrElse(
                (mouse) -> desktopPC.setMouseID(mouse),
                () -> new IllegalArgumentException("A mouse with such ID does not exist.")
            );
        }
        if (speakersID.isPresent()) {
            Optional<Peripheral> searchedSpeakers = this.peripheralsDAO.getByID(speakersID.get());
            searchedSpeakers.ifPresentOrElse(
                (speakers) -> desktopPC.setAudioSpeakersID(speakers),
                () -> new IllegalArgumentException("Speakers with such ID do not exist.")
            );
        }

        // Desktop PC insertion
        this.desktopPCsDAO.add(desktopPC);
    }

    @Override
    public void addLaptop(String pcID, String cpuID, String massStorage01ID, Optional<String> massStorage02ID,
            String ramModule01ID, Optional<String> ramModule02ID, Optional<String> ramModule03ID,
            Optional<String> ramModule04ID, boolean isEthernetSupported, boolean isWiFiSupported,
            boolean isBluetoothSupported, String brand, String model, int size, String color, Optional<String> notes) {
        
        final PC pc = createPCObject(pcID, cpuID, massStorage01ID, massStorage02ID, ramModule01ID, ramModule02ID,
                ramModule03ID, ramModule04ID, isEthernetSupported, isWiFiSupported, isBluetoothSupported, notes);
        pc.setType("Laptop");
        
        final Laptop laptop = new Laptop();
        laptop.setPc(pc);
        laptop.setBrand(brand);
        laptop.setModel(model);
        laptop.setSize(size);
        laptop.setColor(color);

        // Laptop insertion
        this.laptopsDAO.add(laptop);
    }

    private PC createPCObject(String pcID, String cpuID, String massStorage01ID, Optional<String> massStorage02ID,
            String ramModule01ID, Optional<String> ramModule02ID, Optional<String> ramModule03ID,
            Optional<String> ramModule04ID, boolean isEthernetSupported, boolean isWiFiSupported,
            boolean isBluetoothSupported, Optional<String> notes) {
        final PC pc = new PC();
        pc.setPcID(pcID);
        final Optional<Cpu> searchedCPU = this.cpuDAO.getByID(cpuID);
        searchedCPU.ifPresentOrElse(
            (cpu) -> pc.setCpuID(cpu),
            () -> new IllegalArgumentException("A CPU with such ID does not exist.")
        );
        Optional<RAMModule> searchedRAM = this.ramDAO.getByID(ramModule01ID);
        searchedRAM.ifPresentOrElse(
            (ram) -> pc.setRAMModule01ID(ram),
            () -> new IllegalArgumentException("A RAM module with such ID does not exist.")
        );
        if (ramModule02ID.isPresent()) {
            searchedRAM = this.ramDAO.getByID(ramModule02ID.get());
            searchedRAM.ifPresentOrElse(
                (ram) -> pc.setRAMModule02ID(ram),
                () -> new IllegalArgumentException("A RAM module with such ID does not exist.")
            );
        }
        if (ramModule03ID.isPresent()) {
            searchedRAM = this.ramDAO.getByID(ramModule03ID.get());
            searchedRAM.ifPresentOrElse(
                (ram) -> pc.setRAMModule03ID(ram),
                () -> new IllegalArgumentException("A RAM module with such ID does not exist.")
            );
        }
        if (ramModule04ID.isPresent()) {
            searchedRAM = this.ramDAO.getByID(ramModule04ID.get());
            searchedRAM.ifPresentOrElse(
                (ram) -> pc.setRAMModule04ID(ram),
                () -> new IllegalArgumentException("A RAM module with such ID does not exist.")
            );
        }
        Optional<MassStorageDevice> searchedMassStorage = this.massStorageDAO.getByID(massStorage01ID);
        searchedMassStorage.ifPresentOrElse(
            (massStorage) -> pc.setMassStorage01ID(massStorage),
            () -> new IllegalArgumentException("A mass storage device with such ID does not exist.")
        );
        if (massStorage02ID.isPresent()) {
            searchedMassStorage = this.massStorageDAO.getByID(massStorage02ID.get());
            searchedMassStorage.ifPresentOrElse(
                (massStorage) -> pc.setMassStorage02ID(massStorage),
                () -> new IllegalArgumentException("A mass storage device with such ID does not exist.")
            );
        }
        pc.setIsEthernetSupported(isEthernetSupported);
        pc.setIsWiFiSupported(isWiFiSupported);
        pc.setIsBluetoothSupported(isBluetoothSupported);
        notes.ifPresent((value) -> pc.setNotes(value));
        return pc;
    }

    @Override
    public void addOperatingSystem(String pcID, String name, String version, LocalDate lastUpdateDate) {
        final OperatingSystemId osID = new OperatingSystemId();
        osID.setPcID(pcID);
        osID.setName(name);
        osID.setVersion(version);
        final OperatingSystem os = new OperatingSystem();
        os.setId(osID);
        os.setLastUpdateDate(lastUpdateDate);
        // OS insertion
        this.operatingSystemsDAO.add(os);
    }

    @Override
    public void bindComponentToPC(String componentID, String pcID) {
        final OtherPCComponent pcComponentLink = new OtherPCComponent();
        final Optional<Component> searchedComponent = this.otherComponentsDAO.getByID(componentID);
        searchedComponent.ifPresentOrElse(
            (component) -> pcComponentLink.setComponentID(componentID), 
            () -> new IllegalArgumentException("A component with such ID does not exist.")
        );
        final Optional<PC> searchedPC = this.pcsDAO.getByID(pcID);
        searchedPC.ifPresentOrElse(
            (pc) -> pcComponentLink.setPcID(pcID), 
            () -> new IllegalArgumentException("A PC with such ID does not exist.")
        );
        this.otherPCComponentsDAO.add(pcComponentLink);
    }
    
}
