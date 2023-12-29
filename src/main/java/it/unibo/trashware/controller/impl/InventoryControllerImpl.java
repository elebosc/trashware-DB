package it.unibo.trashware.controller.impl;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import it.unibo.trashware.commons.FieldTags;
import it.unibo.trashware.controller.api.InventoryController;
import it.unibo.trashware.entities.Chassis;
import it.unibo.trashware.entities.Component;
import it.unibo.trashware.entities.Cpu;
import it.unibo.trashware.entities.DesktopPC;
import it.unibo.trashware.entities.Laptop;
import it.unibo.trashware.entities.MassStorageDevice;
import it.unibo.trashware.entities.Monitor;
import it.unibo.trashware.entities.OperatingSystem;
import it.unibo.trashware.entities.OperatingSystemId;
import it.unibo.trashware.entities.OtherPCComponent;
import it.unibo.trashware.entities.PC;
import it.unibo.trashware.entities.Peripheral;
import it.unibo.trashware.entities.RAMModule;
import it.unibo.trashware.model.dao.GenericDAO;
import it.unibo.trashware.model.dao.GenericDAOImpl;
import it.unibo.trashware.model.provider.ConnectionProvider;
import it.unibo.trashware.model.provider.ConnectionProviderImpl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;

public class InventoryControllerImpl implements InventoryController {
    
    private EntityManager em;

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
        // Connect to the database
        final ConnectionProvider provider = new ConnectionProviderImpl();
        final Optional<EntityManager> response = provider.getConnection();
        if (response.isEmpty()) {
            throw new IOException("Error: inventory controller could not establish a connection with the database.");
        }
        this.em = response.get();
        this.cpuDAO = new GenericDAOImpl<>(this.em, Cpu.class);
        this.ramDAO = new GenericDAOImpl<>(this.em, RAMModule.class);
        this.massStorageDAO = new GenericDAOImpl<>(this.em, MassStorageDevice.class);
        this.chassisDAO = new GenericDAOImpl<>(this.em, Chassis.class);
        this.otherComponentsDAO = new GenericDAOImpl<>(this.em, Component.class);
        this.peripheralsDAO = new GenericDAOImpl<>(this.em, Peripheral.class);
        this.monitorsDAO = new GenericDAOImpl<>(this.em, Monitor.class);
        this.pcsDAO = new GenericDAOImpl<>(this.em, PC.class);
        this.desktopPCsDAO = new GenericDAOImpl<>(this.em, DesktopPC.class);
        this.laptopsDAO = new GenericDAOImpl<>(this.em, Laptop.class);
        this.operatingSystemsDAO = new GenericDAOImpl<>(this.em, OperatingSystem.class);
        this.otherPCComponentsDAO = new GenericDAOImpl<>(this.em, OtherPCComponent.class);
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

    @Override
    public List<Map<FieldTags, String>> getLaptopsList() {

        // Get laptops info, except components and os info

        Query query = this.em.createNativeQuery(
            "SELECT lpt.IDPC, lpt.Marca, lpt.Modello, lpt.Colore, lpt.Dimensione, pc.Ethernet, pc.WiFi, pc.Bluetooth, pc.Note\n" +
            "FROM portatili lpt JOIN pc ON (lpt.IDPC = pc.IDPC);"
        );
        List<Object[]> result1 = query.getResultList();

        final List<Map<FieldTags, String>> resultMaps = new LinkedList<>();
        for (final var entry : result1) {

            final Map<FieldTags, String> entryMap = new HashMap<>();
            final String lptID = entry[0].toString();

            entryMap.put(FieldTags.PCID, lptID);
            entryMap.put(FieldTags.BRAND, entry[1].toString());
            entryMap.put(FieldTags.MODEL, entry[2].toString());
            entryMap.put(FieldTags.COLOR, entry[3].toString());
            entryMap.put(FieldTags.SCREENSIZE, entry[4].toString());
            entryMap.put(FieldTags.ETH, entry[5].toString());
            entryMap.put(FieldTags.WIFI, entry[6].toString());
            entryMap.put(FieldTags.BLUETOOTH, entry[7].toString());
            entryMap.put(FieldTags.NOTES, (entry[8] != null) ? entry[8].toString() : "");

            // Get cpu info
            query = this.em.createNativeQuery(
                "SELECT proc.Marca, proc.Modello, Architettura\n" +
                "FROM (SELECT c.IDComponente, c.Marca, c.Modello FROM componenti c JOIN pc ON (c.IDComponente = pc.IDCPU) WHERE (pc.IDPC = ?1)) AS proc\n" +
                    "JOIN cpu ON (proc.IDComponente = cpu.IDComponente);"
            );
            query.setParameter(1, lptID);
            List<Object[]> result2 = query.getResultList();
            for (var e : result2) {
                entryMap.put(FieldTags.CPU_BRAND, e[0].toString());
                entryMap.put(FieldTags.CPU_MODEL, e[1].toString());
                entryMap.put(FieldTags.CPU_ARC, e[2].toString());
            }

            // Get ram quantity
            // TO DO
            entryMap.put(FieldTags.RAM_SIZE, "");

            // Get storage info
            query = this.em.createNativeQuery(
                "SELECT Tipologia, Dimensione\n" +
                "FROM (SELECT c.IDComponente FROM componenti c JOIN pc ON (c.IDComponente = pc.IDMemMassa_01) WHERE (pc.IDPC = ?1)) AS stor\n" + //
                    "JOIN memoria_di_massa m ON (stor.IDComponente = m.IDComponente);"
            );
            query.setParameter(1, lptID);
            List<Object[]> result3 = query.getResultList();
            for (var e : result3) {
                entryMap.put(FieldTags.STORAGE_TYPE, e[0].toString());
                entryMap.put(FieldTags.STORAGE_SIZE, e[1].toString());
            }

            // Get OS info
            query = this.em.createNativeQuery(
                "SELECT Nome, Versione, DataUltimoAggiornamento\n" +
                "FROM sistema_operativo os JOIN pc ON (os.IDPC = pc.IDPC)\n" +
                "WHERE (os.IDPC = ?1);"
            );
            query.setParameter(1, lptID);
            List<Object[]> result4 = query.getResultList();
            for (var e : result4) {
                entryMap.put(FieldTags.OS_VERSION, e[0].toString() + " " + e[1].toString());
                entryMap.put(FieldTags.OS_UPDATE, e[2].toString());
            }

            resultMaps.add(entryMap);
        }

        return resultMaps;
    }

    @Override
    public List<Map<FieldTags, String>> getMonitorsList() {
        
        Query query = this.em.createNativeQuery(
            "SELECT p.IDPeriferica, p.Marca, p.Modello, p.Connettività, m.Tipologia, m.Dimensione, m.AspectRatio, m.VGA, m.DVI, m.AudioIntegrato, p.Note\n" +
            "FROM monitor m JOIN periferiche p ON (m.IDPeriferica = p.IDPeriferica);");
        List<Object[]> result1 = query.getResultList();

        final List<Map<FieldTags, String>> resultMaps = new LinkedList<>();
        for (final var entry : result1) {

            final Map<FieldTags, String> entryMap = new HashMap<>();
            final String monitorID = entry[0].toString();

            entryMap.put(FieldTags.MONITOR_ID, monitorID);
            entryMap.put(FieldTags.BRAND, entry[1].toString());
            entryMap.put(FieldTags.MODEL, entry[2].toString());
            entryMap.put(FieldTags.CONNECTIVITY, entry[3].toString());
            entryMap.put(FieldTags.MONITOR_TYPE, entry[4].toString());
            entryMap.put(FieldTags.MONITOR_SIZE, entry[5].toString());
            entryMap.put(FieldTags.RATIO, entry[6].toString());
            entryMap.put(FieldTags.VGA, entry[7].toString());
            entryMap.put(FieldTags.DVI, entry[8].toString());
            entryMap.put(FieldTags.EMBEDDED_AUDIO, entry[9].toString());
            entryMap.put(FieldTags.NOTES, (entry[10] != null) ? entry[10].toString() : "");

            // Is monitor assigned to a PC?
            query = this.em.createNativeQuery(
                "SELECT IDPC\n" + //
                "FROM desktop WHERE (IDMonitor = ?1);"
            );
            query.setParameter(1, monitorID);
            try {
                String result2 = (String) query.getSingleResult();
                entryMap.put(FieldTags.ASSIGNED_TO_PC, result2.toString());
            } catch (NoResultException ex) {
                entryMap.put(FieldTags.ASSIGNED_TO_PC, "");
            }

            resultMaps.add(entryMap);
        }

        return resultMaps;
    }

    @Override
    public List<Map<FieldTags, String>> getOtherPeripheralsList() {

        Query query = this.em.createNativeQuery(
            "SELECT IDPeriferica, Tipo, Marca, Modello, Connettività, Note\n" +
            "FROM periferiche\n" +
            "WHERE (Tipo != 'Monitor');"
        );
        List<Object[]> result1 = query.getResultList();

        final List<Map<FieldTags, String>> resultMaps = new LinkedList<>();
        for (final var entry : result1) {

            final Map<FieldTags, String> entryMap = new HashMap<>();
            final String peripheralID = entry[0].toString();

            entryMap.put(FieldTags.PERIPHERAL_ID, peripheralID);
            entryMap.put(FieldTags.PERIPHERAL_TYPE, entry[1].toString());
            entryMap.put(FieldTags.BRAND, entry[2].toString());
            entryMap.put(FieldTags.MODEL, entry[3].toString());
            entryMap.put(FieldTags.CONNECTIVITY, entry[4].toString());
            entryMap.put(FieldTags.NOTES, (entry[5] != null) ? entry[5].toString() : "");

            // Is peripheral assigned to a PC?
            query = this.em.createNativeQuery(
                "SELECT IDPC\n" +
                "FROM desktop\n" +
                "WHERE (IDTastiera = ?1) OR (IDMouse = ?1) OR (IDCasseAudio = ?1);"
            );
            query.setParameter(1, peripheralID);
            try {
                String result2 = (String) query.getSingleResult();
                entryMap.put(FieldTags.ASSIGNED_TO_PC, result2.toString());
            } catch (NoResultException ex) {
                entryMap.put(FieldTags.ASSIGNED_TO_PC, "");
            }

            resultMaps.add(entryMap);
        }

        return resultMaps;
    }

    @Override
    public List<Map<FieldTags, String>> getCPUsList() {

        Query query = this.em.createNativeQuery(
            "SELECT cpu.IDComponente, Marca, Modello, Architettura\n" +
            "FROM cpu JOIN componenti c ON (cpu.IDComponente = c.IDComponente);"
        );
        List<Object[]> result1 = query.getResultList();

        final List<Map<FieldTags, String>> resultMaps = new LinkedList<>();
        for (final var entry : result1) {

            final Map<FieldTags, String> entryMap = new HashMap<>();
            final String cpuID = entry[0].toString();

            entryMap.put(FieldTags.CPU_ID, cpuID);
            entryMap.put(FieldTags.CPU_BRAND, entry[1].toString());
            entryMap.put(FieldTags.CPU_MODEL, entry[2].toString());
            entryMap.put(FieldTags.CPU_ARC, entry[3].toString());

            // Is CPU assigned to a PC?
            query = this.em.createNativeQuery(
                "SELECT IDPC FROM pc WHERE (IDCPU = ?1);"
            );
            query.setParameter(1, cpuID);
            try {
                String result2 = (String) query.getSingleResult();
                entryMap.put(FieldTags.ASSIGNED_TO_PC, result2.toString());
            } catch (NoResultException ex) {
                entryMap.put(FieldTags.ASSIGNED_TO_PC, "");
            }

            resultMaps.add(entryMap);
        }

        return resultMaps;
    }

    @Override
    public List<Map<FieldTags, String>> getRAMModulesList() {

        Query query = this.em.createNativeQuery(
            "SELECT ram.IDComponente, Marca, Modello, Dimensione\n" +
            "FROM ram JOIN componenti c ON (ram.IDComponente = c.IDComponente);"
        );
        List<Object[]> result1 = query.getResultList();

        final List<Map<FieldTags, String>> resultMaps = new LinkedList<>();
        for (final var entry : result1) {

            final Map<FieldTags, String> entryMap = new HashMap<>();
            final String ramID = entry[0].toString();

            entryMap.put(FieldTags.RAM_ID, ramID);
            entryMap.put(FieldTags.BRAND, entry[1].toString());
            entryMap.put(FieldTags.MODEL, entry[2].toString());
            entryMap.put(FieldTags.RAM_SIZE, entry[3].toString());

            // Is RAM module assigned to a PC?
            query = this.em.createNativeQuery(
                "SELECT IDPC FROM pc\n" +
                "WHERE (IDRAM_01 = ?1) OR (IDRAM_02 = ?1) OR (IDRAM_03 = ?1) OR (IDRAM_04 = ?1);"
            );
            query.setParameter(1, ramID);
            try {
                String result2 = (String) query.getSingleResult();
                entryMap.put(FieldTags.ASSIGNED_TO_PC, result2.toString());
            } catch (NoResultException ex) {
                entryMap.put(FieldTags.ASSIGNED_TO_PC, "");
            }

            resultMaps.add(entryMap);
        }

        return resultMaps;
    }
    
}
