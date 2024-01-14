package it.unibo.trashware.controller.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    private static final Logger LOGGER = LoggerFactory.getLogger(InventoryControllerImpl.class);
    
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
    public List<Map<FieldTags, String>> getDesktopsList() {

        // Get desktop PC info, except for components and OS info
        Query query = this.em.createNativeQuery(
            "SELECT d.IDPC, Ethernet, WiFi, Bluetooth, IDMonitor, IDTastiera, IDMouse, IDCasseAudio, Note\n" + //
            "FROM desktop d JOIN pc ON (d.IDPC = pc.IDPC);"
        );
        List<Object[]> result1 = query.getResultList();

        final List<Map<FieldTags, String>> resultMaps = new LinkedList<>();
        for (final var entry : result1) {

            final Map<FieldTags, String> entryMap = new HashMap<>();
            final String desktopID = entry[0].toString();

            entryMap.put(FieldTags.PCID, desktopID);
            entryMap.put(FieldTags.ETH, entry[1].toString());
            entryMap.put(FieldTags.WIFI, entry[2].toString());
            entryMap.put(FieldTags.BLUETOOTH, entry[3].toString());
            entryMap.put(FieldTags.MONITOR_ID, (entry[4] != null) ? entry[4].toString() : "");
            entryMap.put(FieldTags.KEYBOARD_ID, (entry[5] != null) ? entry[5].toString() : "");
            entryMap.put(FieldTags.MOUSE_ID, (entry[6] != null) ? entry[6].toString() : "");
            entryMap.put(FieldTags.SPEAKERS_ID, (entry[7] != null) ? entry[7].toString() : "");
            entryMap.put(FieldTags.NOTES, (entry[8] != null) ? entry[8].toString() : "");

            // Get cpu info
            query = this.em.createNativeQuery(
                "SELECT proc.Marca, proc.Modello, Architettura\n" +
                "FROM (SELECT c.IDComponente, c.Marca, c.Modello FROM componenti c JOIN pc ON (c.IDComponente = pc.IDCPU) WHERE (pc.IDPC = ?1)) AS proc\n" +
                    "JOIN cpu ON (proc.IDComponente = cpu.IDComponente);"
            );
            query.setParameter(1, desktopID);
            List<Object[]> result2 = query.getResultList();
            for (var e : result2) {
                entryMap.put(FieldTags.CPU_BRAND, e[0].toString());
                entryMap.put(FieldTags.CPU_MODEL, e[1].toString());
                entryMap.put(FieldTags.CPU_ARC, e[2].toString());
            }

            // Get ram quantity
            query = this.em.createNativeQuery(
                "SELECT SUM(ram.Dimensione)\n" +
                "FROM ram JOIN pc\n" +
                "ON (pc.IDPC = ?1) AND ((ram.IDComponente = pc.IDRAM_01) OR (ram.IDComponente = pc.IDRAM_02) OR (ram.IDComponente = pc.IDRAM_03) OR ((ram.IDComponente = pc.IDRAM_04)));"
            );
            query.setParameter(1, desktopID);
            BigDecimal result3 = (BigDecimal) query.getSingleResult();
            entryMap.put(FieldTags.RAM_SIZE, result3.toString());

            // Get storage info
            query = this.em.createNativeQuery(
                "SELECT Tipologia, Dimensione\n" +
                "FROM (SELECT c.IDComponente FROM componenti c JOIN pc ON (c.IDComponente = pc.IDMemMassa_01) WHERE (pc.IDPC = ?1)) AS stor\n" + //
                    "JOIN memoria_di_massa m ON (stor.IDComponente = m.IDComponente);"
            );
            query.setParameter(1, desktopID);
            List<Object[]> result4 = query.getResultList();
            for (var e : result4) {
                entryMap.put(FieldTags.STORAGE_01_TYPE, e[0].toString());
                entryMap.put(FieldTags.STORAGE_01_SIZE, e[1].toString());
            }
            query = this.em.createNativeQuery(
                "SELECT Tipologia, Dimensione\n" +
                "FROM (SELECT c.IDComponente FROM componenti c JOIN pc ON (c.IDComponente = pc.IDMemMassa_02) WHERE (pc.IDPC = ?1)) AS stor\n" + //
                    "JOIN memoria_di_massa m ON (stor.IDComponente = m.IDComponente);"
            );
            query.setParameter(1, desktopID);
            List<Object[]> result5 = query.getResultList();
            for (var e : result5) {
                entryMap.put(FieldTags.STORAGE_02_TYPE, (e[0].toString() != null) ? e[0].toString() : "");
                entryMap.put(FieldTags.STORAGE_02_SIZE, (e[1].toString() != null) ? e[1].toString() : "");
            }

            // Get OS info
            query = this.em.createNativeQuery(
                "SELECT Nome, Versione, DataUltimoAggiornamento\n" +
                "FROM sistema_operativo os JOIN pc ON (os.IDPC = pc.IDPC)\n" +
                "WHERE (os.IDPC = ?1);"
            );
            query.setParameter(1, desktopID);
            List<Object[]> result6 = query.getResultList();
            for (var e : result6) {
                entryMap.put(FieldTags.OS_VERSION, e[0].toString() + " " + e[1].toString());
                entryMap.put(FieldTags.OS_UPDATE, e[2].toString());
            }

            // Get case info
            query = this.em.createNativeQuery(
                "SELECT Marca, Modello, Colore\n" + //
                "FROM (SELECT c.IDComponente, c.Marca, c.Modello FROM componenti c JOIN desktop d ON (c.IDComponente = d.IDChassis) WHERE (d.IDPC = ?1)) AS ch1\n" + //
                    "JOIN chassis ch2 ON (ch1.IDComponente = ch2.IDComponente);"
            );
            query.setParameter(1, desktopID);
            List<Object[]> result7 = query.getResultList();
            for (var e : result7) {
                entryMap.put(FieldTags.CHASSIS_BRAND, e[0].toString());
                entryMap.put(FieldTags.CHASSIS_MODEL, e[1].toString());
                entryMap.put(FieldTags.CHASSIS_COLOR, e[2].toString());
            }

            // Is the PC assigned to a request?
            // Search among maintenance requests first, since they are more recent than a donation request
            // that involves the same device
            boolean request_found = false;
            query = this.em.createNativeQuery(
                "SELECT ric.IDOperazione\n" + //
                "FROM oggetto_pc opc JOIN (\n" + //
                    "SELECT o.IDOperazione\n" + //
                    "FROM operazioni o JOIN richieste r ON (o.IDOperazione = r.IDRichiesta)\n" + //
                    "WHERE (r.Tipo = 'Manutenzione')\n" + //
                ") AS ric ON (opc.IDOperazione = ric.IDOperazione)\n" + //
                "WHERE (opc.IDPC = ?1);"
            );
            query.setParameter(1, desktopID);
            try {
                String result8 = (String) query.getSingleResult();
                entryMap.put(FieldTags.ASSIGNED_TO_REQUEST, result8.toString());
                request_found = true;
            } catch (NoResultException ex) {
                // No maintenance request involving the device has been found
            }

            if (!request_found) {
                // Search among donation requests
                query = this.em.createNativeQuery(
                    "SELECT ric.IDOperazione\n" + //
                    "FROM oggetto_pc opc JOIN (\n" + //
                        "SELECT o.IDOperazione\n" + //
                        "FROM operazioni o JOIN richieste r ON (o.IDOperazione = r.IDRichiesta)\n" + //
                        "WHERE (r.Tipo = 'Ordine')\n" + //
                    ") AS ric ON (opc.IDOperazione = ric.IDOperazione)\n" + //
                    "WHERE (opc.IDPC = ?1);"
                );
                query.setParameter(1, desktopID);
                try {
                    String result8 = (String) query.getSingleResult();
                    entryMap.put(FieldTags.ASSIGNED_TO_REQUEST, result8.toString());
                    request_found = true;
                } catch (NoResultException ex) {
                    // No doantion request involving the device has been found
                    entryMap.put(FieldTags.ASSIGNED_TO_REQUEST, "");
                }
            }

            resultMaps.add(entryMap);
        }

        return resultMaps;
    }

    @Override
    public List<Map<FieldTags, String>> getLaptopsList() {

        // Get laptops info, except for components and OS info
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
            query = this.em.createNativeQuery(
                "SELECT SUM(ram.Dimensione)\n" +
                "FROM ram JOIN pc\n" +
                "ON (pc.IDPC = ?1) AND ((ram.IDComponente = pc.IDRAM_01) OR (ram.IDComponente = pc.IDRAM_02) OR (ram.IDComponente = pc.IDRAM_03) OR ((ram.IDComponente = pc.IDRAM_04)));"
            );
            query.setParameter(1, lptID);
            BigDecimal result3 = (BigDecimal) query.getSingleResult();
            entryMap.put(FieldTags.RAM_SIZE, result3.toString());

            // Get storage info
            query = this.em.createNativeQuery(
                "SELECT Tipologia, Dimensione\n" +
                "FROM (SELECT c.IDComponente FROM componenti c JOIN pc ON (c.IDComponente = pc.IDMemMassa_01) WHERE (pc.IDPC = ?1)) AS stor\n" + //
                    "JOIN memoria_di_massa m ON (stor.IDComponente = m.IDComponente);"
            );
            query.setParameter(1, lptID);
            List<Object[]> result4 = query.getResultList();
            for (var e : result4) {
                entryMap.put(FieldTags.STORAGE_01_TYPE, e[0].toString());
                entryMap.put(FieldTags.STORAGE_01_SIZE, e[1].toString());
            }
            query = this.em.createNativeQuery(
                "SELECT Tipologia, Dimensione\n" +
                "FROM (SELECT c.IDComponente FROM componenti c JOIN pc ON (c.IDComponente = pc.IDMemMassa_02) WHERE (pc.IDPC = ?1)) AS stor\n" + //
                    "JOIN memoria_di_massa m ON (stor.IDComponente = m.IDComponente);"
            );
            query.setParameter(1, lptID);
            List<Object[]> result5 = query.getResultList();
            for (var e : result5) {
                entryMap.put(FieldTags.STORAGE_02_TYPE, (e[0].toString() != null) ? e[0].toString() : "");
                entryMap.put(FieldTags.STORAGE_02_SIZE, (e[1].toString() != null) ? e[1].toString() : "");
            }

            // Get OS info
            query = this.em.createNativeQuery(
                "SELECT Nome, Versione, DataUltimoAggiornamento\n" +
                "FROM sistema_operativo os JOIN pc ON (os.IDPC = pc.IDPC)\n" +
                "WHERE (os.IDPC = ?1);"
            );
            query.setParameter(1, lptID);
            List<Object[]> result6 = query.getResultList();
            for (var e : result6) {
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
            "SELECT p.IDPeriferica, Tipo, Marca, Modello, Connettività, Note\n" +
            "FROM periferiche p \n" +
            "WHERE NOT EXISTS (\n" +
                "SELECT * FROM monitor m WHERE (m.IDPeriferica = p.IDPeriferica)\n" +
            ");"
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
            "SELECT cpu.IDComponente, Marca, Modello, Architettura, Note\n" +
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
            entryMap.put(FieldTags.NOTES, (entry[4] != null) ? entry[4].toString() : "");

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
            "SELECT ram.IDComponente, Marca, Modello, Dimensione, Note\n" +
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
            entryMap.put(FieldTags.NOTES, (entry[4] != null) ? entry[4].toString() : "");

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

    @Override
    public List<Map<FieldTags, String>> getStorageDevicesList() {

        Query query = this.em.createNativeQuery(
            "SELECT m.IDComponente, Marca, Modello, Tipologia, Dimensione, Note\n" +
            "FROM memoria_di_massa m JOIN componenti c ON (m.IDComponente = c.IDComponente);"
        );
        List<Object[]> result1 = query.getResultList();

        final List<Map<FieldTags, String>> resultMaps = new LinkedList<>();
        for (final var entry : result1) {

            final Map<FieldTags, String> entryMap = new HashMap<>();
            final String sdID = entry[0].toString();

            entryMap.put(FieldTags.STORAGE_ID, sdID);
            entryMap.put(FieldTags.BRAND, entry[1].toString());
            entryMap.put(FieldTags.MODEL, entry[2].toString());
            entryMap.put(FieldTags.STORAGE_01_TYPE, entry[3].toString());
            entryMap.put(FieldTags.STORAGE_01_SIZE, entry[4].toString());
            entryMap.put(FieldTags.NOTES, (entry[5] != null) ? entry[5].toString() : "");

            // Is storage device assigned to a PC?
            query = this.em.createNativeQuery(
                "SELECT IDPC FROM pc\n" +
                "WHERE (IDMemMassa_01 = ?1) OR (IDMemMassa_02 = ?1);"
            );
            query.setParameter(1, sdID);
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
    public List<Map<FieldTags, String>> getChassisList() {
        
        Query query = this.em.createNativeQuery(
            "SELECT ch.IDComponente, Marca, Modello, Colore, Note\n" +
            "FROM chassis ch JOIN componenti c ON (ch.IDComponente = c.IDComponente);"
        );
        List<Object[]> result1 = query.getResultList();

        final List<Map<FieldTags, String>> resultMaps = new LinkedList<>();
        for (final var entry : result1) {

            final Map<FieldTags, String> entryMap = new HashMap<>();
            final String chassisID = entry[0].toString();

            entryMap.put(FieldTags.CHASSIS_ID, chassisID);
            entryMap.put(FieldTags.BRAND, entry[1].toString());
            entryMap.put(FieldTags.MODEL, entry[2].toString());
            entryMap.put(FieldTags.COLOR, entry[3].toString());
            entryMap.put(FieldTags.NOTES, (entry[4] != null) ? entry[4].toString() : "");

            // Is chassis assigned to a PC?
            query = this.em.createNativeQuery(
                "SELECT IDPC FROM desktop WHERE (IDChassis = ?1);"
            );
            query.setParameter(1, chassisID);
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
    public List<Map<FieldTags, String>> getOtherComponentsList() {

        Query query = this.em.createNativeQuery(
            "SELECT c.IDComponente, Tipo, Marca, Modello, Note\n" +
            "FROM componenti c\n" +
            "WHERE NOT EXISTS (SELECT * FROM cpu WHERE (cpu.IDComponente = c.IDComponente))\n" +
                "AND NOT EXISTS (SELECT * FROM ram WHERE (ram.IDComponente = c.IDComponente))\n" +
                "AND NOT EXISTS (SELECT * FROM memoria_di_massa m WHERE (m.IDComponente = c.IDComponente))\n" +
                "AND NOT EXISTS (SELECT * FROM chassis ch WHERE (ch.IDComponente = c.IDComponente));"
        );
        List<Object[]> result1 = query.getResultList();

        final List<Map<FieldTags, String>> resultMaps = new LinkedList<>();
        for (final var entry : result1) {

            final Map<FieldTags, String> entryMap = new HashMap<>();
            final String componentID = entry[0].toString();

            entryMap.put(FieldTags.COMPONENT_ID, componentID);
            entryMap.put(FieldTags.COMPONENT_TYPE, entry[1].toString());
            entryMap.put(FieldTags.BRAND, entry[2].toString());
            entryMap.put(FieldTags.MODEL, entry[3].toString());
            entryMap.put(FieldTags.NOTES, (entry[4] != null) ? entry[4].toString() : "");

            // Is component assigned to a PC?
            query = this.em.createNativeQuery(
                "SELECT IDPC FROM altri_componenti_pc WHERE (IDComponente = ?1);"
            );
            query.setParameter(1, componentID);
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
    public void associateCPUToPC(String cpuID, String pcID) {

        // Check that a CPU with the specified ID exists
        if (this.cpuDAO.getByID(cpuID).isEmpty()) {
            throw new NoSuchElementException("Non esiste una CPU con l'ID specificato.");
        }
        // Check that a PC with the specified ID exists
        if (this.pcsDAO.getByID(pcID).isEmpty()) {
            throw new NoSuchElementException("Non esiste un PC con l'ID specificato.");
        }

        // Update CPU field in PC
        Query query = this.em.createNativeQuery("UPDATE pc SET IDCPU = ?1 WHERE (IDPC = ?2)");
        query.setParameter(1, cpuID);
        query.setParameter(2, pcID);
        try {
            this.em.getTransaction().begin();
            query.executeUpdate();
            this.em.getTransaction().commit();
        } catch (final Exception ex) {
            this.em.getTransaction().rollback();
            LOGGER.error(ex.getMessage());
            throw new IllegalStateException("Impossibile portare a termine l'operazione.");
        }
        
        return;
    }

    @Override
    public void associateRAMToPC(String ramID, String pcID, int nModule) {

        // Check that a RAM module with the specified ID exists
        if (this.ramDAO.getByID(ramID).isEmpty()) {
            throw new NoSuchElementException("Non esiste un modulo RAM con l'ID specificato.");
        }
        // Check that a PC with the specified ID exists
        if (this.pcsDAO.getByID(pcID).isEmpty()) {
            throw new NoSuchElementException("Non esiste un PC con l'ID specificato.");
        }

        // Update RAM field in PC
        Query query;
        switch (nModule) {
            case 1:
                query = this.em.createNativeQuery("UPDATE pc SET IDRAM_01 = ?1 WHERE (IDPC = ?2)");
                break;
            case 2:
                query = this.em.createNativeQuery("UPDATE pc SET IDRAM_02 = ?1 WHERE (IDPC = ?2)");
                break;
            case 3:
                query = this.em.createNativeQuery("UPDATE pc SET IDRAM_03 = ?1 WHERE (IDPC = ?2)");
                break;
            case 4:
                query = this.em.createNativeQuery("UPDATE pc SET IDRAM_04 = ?1 WHERE (IDPC = ?2)");
                break;
            default:
                throw new IllegalArgumentException("Numero del modulo RAM non valido.");
        };
        query.setParameter(1, ramID);
        query.setParameter(2, pcID);
        try {
            this.em.getTransaction().begin();
            query.executeUpdate();
            this.em.getTransaction().commit();
        } catch (final Exception ex) {
            this.em.getTransaction().rollback();
            LOGGER.error(ex.getMessage());
            throw new IllegalStateException("Impossibile portare a termine l'operazione.");
        }
        
        return;
    }

    @Override
    public void associateStorageToPC(String storageID, String pcID, int nStorage) {
        
        // Check that a mass storage device with the specified ID exists
        if (this.massStorageDAO.getByID(storageID).isEmpty()) {
            throw new NoSuchElementException("Non esiste un dispositivo di memoria di massa con l'ID specificato.");
        }
        // Check that a PC with the specified ID exists
        if (this.pcsDAO.getByID(pcID).isEmpty()) {
            throw new NoSuchElementException("Non esiste un PC con l'ID specificato.");
        }

        // Update mass storage field in PC
        Query query;
        switch (nStorage) {
            case 1:
                query = this.em.createNativeQuery("UPDATE pc SET IDMemMassa_01 = ?1 WHERE (IDPC = ?2)");
                break;
            case 2:
                query = this.em.createNativeQuery("UPDATE pc SET IDMemMassa_02 = ?1 WHERE (IDPC = ?2)");
                break;
            default:
                throw new IllegalArgumentException("Numero del dispositivo di memoria di massa non valido.");
        };
        query.setParameter(1, storageID);
        query.setParameter(2, pcID);
        try {
            this.em.getTransaction().begin();
            query.executeUpdate();
            this.em.getTransaction().commit();
        } catch (final Exception ex) {
            this.em.getTransaction().rollback();
            LOGGER.error(ex.getMessage());
            throw new IllegalStateException("Impossibile portare a termine l'operazione.");
        }
        
        return;
    }

    @Override
    public void associateChassisToPC(String chassisID, String pcID) {
        
        // Check that a chassis with the specified ID exists
        if (this.chassisDAO.getByID(chassisID).isEmpty()) {
            throw new NoSuchElementException("Non esiste uno chassis con l'ID specificato.");
        }
        // Check that a desktop PC with the specified ID exists
        if (this.desktopPCsDAO.getByID(pcID).isEmpty()) {
            throw new NoSuchElementException("Non esiste un PC desktop con l'ID specificato.");
        }

        // Update chassis field in desktop PC
        Query query = this.em.createNativeQuery("UPDATE desktop SET IDChassis = ?1 WHERE (IDPC = ?2)");
        query.setParameter(1, chassisID);
        query.setParameter(2, pcID);
        try {
            this.em.getTransaction().begin();
            query.executeUpdate();
            this.em.getTransaction().commit();
        } catch (final Exception ex) {
            this.em.getTransaction().rollback();
            LOGGER.error(ex.getMessage());
            throw new IllegalStateException("Impossibile portare a termine l'operazione.");
        }
        
        return;
    }

    @Override
    public void associateOtherComponentToPC(String componentID, String pcID) {
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
    public void associateMonitorToPC(String monitorID, String pcID) {
        
        // Check that a monitor with the specified ID exists
        if (this.monitorsDAO.getByID(monitorID).isEmpty()) {
            throw new NoSuchElementException("Non esiste un monitor con l'ID specificato.");
        }
        // Check that a desktop PC with the specified ID exists
        if (this.desktopPCsDAO.getByID(pcID).isEmpty()) {
            throw new NoSuchElementException("Non esiste un PC desktop con l'ID specificato.");
        }

        // Update monitor field in desktop PC
        Query query = this.em.createNativeQuery("UPDATE desktop SET IDMonitor = ?1 WHERE (IDPC = ?2)");
        query.setParameter(1, monitorID);
        query.setParameter(2, pcID);
        try {
            this.em.getTransaction().begin();
            query.executeUpdate();
            this.em.getTransaction().commit();
        } catch (final Exception ex) {
            this.em.getTransaction().rollback();
            LOGGER.error(ex.getMessage());
            throw new IllegalStateException("Impossibile portare a termine l'operazione.");
        }
        
        return;
    }

    @Override
    public void associateKeyboardToPC(String keyboardID, String pcID) {
        
        // Check that a keyboard with the specified ID exists
        if (this.peripheralsDAO.getByID(keyboardID).isEmpty()) {
            throw new NoSuchElementException("Non esiste una tastiera con l'ID specificato.");
        }
        // Check that a desktop PC with the specified ID exists
        if (this.desktopPCsDAO.getByID(pcID).isEmpty()) {
            throw new NoSuchElementException("Non esiste un PC desktop con l'ID specificato.");
        }

        // Update keyboard field in desktop PC
        Query query = this.em.createNativeQuery("UPDATE desktop SET IDTastiera = ?1 WHERE (IDPC = ?2)");
        query.setParameter(1, keyboardID);
        query.setParameter(2, pcID);
        try {
            this.em.getTransaction().begin();
            query.executeUpdate();
            this.em.getTransaction().commit();
        } catch (final Exception ex) {
            this.em.getTransaction().rollback();
            LOGGER.error(ex.getMessage());
            throw new IllegalStateException("Impossibile portare a termine l'operazione.");
        }
        
        return;
    }

    @Override
    public void associateMouseToPC(String mouseID, String pcID) {
        
        // Check that a mouse with the specified ID exists
        if (this.peripheralsDAO.getByID(mouseID).isEmpty()) {
            throw new NoSuchElementException("Non esiste un mouse con l'ID specificato.");
        }
        // Check that a desktop PC with the specified ID exists
        if (this.desktopPCsDAO.getByID(pcID).isEmpty()) {
            throw new NoSuchElementException("Non esiste un PC desktop con l'ID specificato.");
        }

        // Update mouse field in desktop PC
        Query query = this.em.createNativeQuery("UPDATE desktop SET IDMouse = ?1 WHERE (IDPC = ?2)");
        query.setParameter(1, mouseID);
        query.setParameter(2, pcID);
        try {
            this.em.getTransaction().begin();
            query.executeUpdate();
            this.em.getTransaction().commit();
        } catch (final Exception ex) {
            this.em.getTransaction().rollback();
            LOGGER.error(ex.getMessage());
            throw new IllegalStateException("Impossibile portare a termine l'operazione.");
        }
        
        return;
    }

    @Override
    public void associateSpeakersToPC(String speakersID, String pcID) {
        
        // Check that speakers with the specified ID exists
        if (this.peripheralsDAO.getByID(speakersID).isEmpty()) {
            throw new NoSuchElementException("Non esiste una coppia di casse audio con l'ID specificato.");
        }
        // Check that a desktop PC with the specified ID exists
        if (this.desktopPCsDAO.getByID(pcID).isEmpty()) {
            throw new NoSuchElementException("Non esiste un PC desktop con l'ID specificato.");
        }

        // Update keyboard field in desktop PC
        Query query = this.em.createNativeQuery("UPDATE desktop SET IDCasseAudio = ?1 WHERE (IDPC = ?2)");
        query.setParameter(1, speakersID);
        query.setParameter(2, pcID);
        try {
            this.em.getTransaction().begin();
            query.executeUpdate();
            this.em.getTransaction().commit();
        } catch (final Exception ex) {
            this.em.getTransaction().rollback();
            LOGGER.error(ex.getMessage());
            throw new IllegalStateException("Impossibile portare a termine l'operazione.");
        }
        
        return;
    }
    
}
