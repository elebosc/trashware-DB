package it.unibo.populator;

import java.io.IOException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import it.unibo.populator.utils.CommonPeripheralTypes;
import it.unibo.populator.utils.Generator;
import it.unibo.trashware.controller.api.InventoryController;
import it.unibo.trashware.controller.api.OperationsController;
import it.unibo.trashware.controller.api.WorkShiftsController;
import net.datafaker.Faker;
import net.datafaker.providers.base.Address;

/**
 * Implementation of the database populator.
 */
public final class PopulatorImpl implements Populator {

    private static final int N_REP = 40;
    private static final int N_SOCIETIES = 15;
    private static final int N_DONATIONS = 20;
    private static final int N_REQUESTS = 20;
    // an upper bound to the number of objects descriptions generated for each operation
    private static final int MAX_OPERATION_OBJECTS = 5;
    private static final int N_CPU = 40;
    private static final int N_RAM = 80;
    private static final int N_MASS_STORAGES = 50;
    private static final int N_CHASSIS_UNITS = 40;
    private static final int N_OTHER_COMPONENTS_TYPES = 5;
    private static final int N_MONITORS = 40;
    private static final int N_OTHER_PERIPHERALS = 50;
    private static final int N_DESKTOP_PC = 30;
    private static final int N_LAPTOPS = 10;
    private static final int N_OS_INSTALLATIONS = 40;

    private OperationsController operationsController;
    private InventoryController inventoryController;
    private WorkShiftsController workShiftsController;
    private Faker faker;
    private Random random;

    /**
     * Creates a new database populator.
     * @param controller the controller of the application
     */
    public PopulatorImpl(
        final OperationsController operationsController,
        final InventoryController inventoryController,
        final WorkShiftsController workShiftsController
    ) {
        this.operationsController = operationsController;
        this.inventoryController = inventoryController;
        this.workShiftsController = workShiftsController;
        this.faker = new Faker(Locale.ITALY);
        this.random = new Random();
    }

    @Override
    public void populateDB() throws IOException {
        // Insert devices
        final List<String> cpuIDs = createCPUs();
        final List<String> ramIDs = createRAMModules();
        final List<String> massStorageIDs = createMassStorageDevices();
        final List<String> chassisIDs = createChassisUnits();
        final List<String> otherComponentIDs = createOtherTypesOfComponents();
        final List<String> monitorIDs = createMonitors();
        final List<String> otherPeripheralIDs = createOtherPeripherals();
        final List<String> desktopPCIDs = createDesktopPCs(cpuIDs, ramIDs, massStorageIDs, chassisIDs, monitorIDs);
        final List<String> laptopIDs = createLaptops(cpuIDs, ramIDs, massStorageIDs);
        final List<String> pcIDs = new LinkedList<>(desktopPCIDs);
        pcIDs.addAll(laptopIDs);
        createOSInstallations(pcIDs);
        // Insert data related to operations
        final List<String> repFiscalCodes = createRepresentatives();
        final List<String> societiesVATNumbers = createSocieties();
        createRepresentations(repFiscalCodes, societiesVATNumbers);
        final List<String> operationIDs = createOperations(repFiscalCodes);
        createDescriptionsAndLinkDevicesToOperations(operationIDs, cpuIDs, ramIDs, massStorageIDs, chassisIDs,
                otherComponentIDs, monitorIDs, otherPeripheralIDs);
    }

    private List<String> createRepresentatives() throws IOException {
        final List<String> repFiscalCodes = new LinkedList<>();
        for (int i = 0; i < N_REP; i++) {
            String name = faker.name().firstName();
            String surname = faker.name().lastName();
            String birthplace = faker.address().city();
            LocalDate birthday = faker.date().birthday().toLocalDateTime().toLocalDate();
            Address address = faker.address();
            String emailLocalPart = name.toLowerCase() + "." + surname.toLowerCase();
            String fiscalCode = Generator.generateFiscalCode(name, surname, birthplace, birthday);
            operationsController.addRepresentative(
                fiscalCode,
                name,
                surname,
                birthplace,
                birthday,
                address.cityName(),
                address.postcode(),
                address.cityName().substring(0, 2).toUpperCase(),
                // The province initials are chosen as the first two letters of the city name;
                // it is not accurate, but it is not relevant to the purpose of the generated fake data.
                address.streetName(),
                Integer.parseInt(address.buildingNumber()),
                faker.phoneNumber().phoneNumberNational(),
                Optional.of(faker.phoneNumber().phoneNumberNational()),
                Optional.of(faker.phoneNumber().phoneNumberNational()),
                Optional.of(faker.internet().emailAddress(emailLocalPart))
            );
            repFiscalCodes.add(fiscalCode);
        }
        return repFiscalCodes;
    }

    private List<String> createSocieties() throws IOException {
        final int VAT_NUMBER_LENGTH = 11;
        final List<String> societiesVATNumbers = new LinkedList<>();
        for (int i = 0; i < N_SOCIETIES; i++) {
            // The fiscal code of an Italian society is the fiscal code of the owner
            String ownerName = faker.name().firstName();
            String ownerSurname = faker.name().lastName();
            String ownerBirthplace = faker.address().city();
            LocalDate ownerBirthday = faker.date().birthday().toLocalDateTime().toLocalDate();
            Address address = faker.address();
            String VATNumber = Generator.generateNumericCode(VAT_NUMBER_LENGTH);
            operationsController.addSociety(
                // The validity of the VAT number is not relevant to the purpose of tha generated fake data,
                // so a random 11 digits long code will be generated for simplicity.
                VATNumber,
                Generator.generateFiscalCode(ownerName, ownerSurname, ownerBirthplace, ownerBirthday),
                faker.company().name(),
                address.cityName(),
                address.postcode(),
                address.cityName().substring(0, 2).toUpperCase(),
                // The province initials are chosen as the first two letters of the city name;
                // it is not accurate, but it is not relevant to the purpose of the generated fake data.
                address.streetName(),
                Integer.parseInt(address.buildingNumber())
            );
            societiesVATNumbers.add(VATNumber);
        }
        return societiesVATNumbers;
    }

    private void createRepresentations(final List<String> repFiscalCodes, final List<String> societiesVATNumbers) {
        for (final String societyID : societiesVATNumbers) {
            operationsController.addRepresentation(
                societyID,
                repFiscalCodes.get(this.random.nextInt(N_REP)),
                faker.company().profession()
            );
        }
    }

    private List<String> createOperations(final List<String> repFiscalCodes) {
        final List<String> operationIDs = new LinkedList<>();
        // Create donations
        for (int i = 0; i < N_DONATIONS; i++) {
            String donationID = Generator.generateDonationID();
            operationsController.addDonation(
                donationID,
                faker.date().past(365, TimeUnit.DAYS).toLocalDateTime().toLocalDate(), // generates a past date within one year from now
                Optional.empty(),   // empty notes
                repFiscalCodes.get(this.random.nextInt(N_REP))
            );
            operationIDs.add(donationID);
        }
        // Create requests
        final int MIN_PRIORITY_LEVEL = 1;
        final int MAX_PRIORITY_LEVEL = 5;
        for (int i = 0; i < N_REQUESTS; i++) {
            String requestID = Generator.generateRequestID();
            operationsController.addRequest(
                requestID,
                "Ordine",
                "",
                faker.date().past(365, TimeUnit.DAYS).toLocalDateTime().toLocalDate(), // generates a past date within one year from now
                faker.date().future(365, TimeUnit.DAYS).toLocalDateTime().toLocalDate(), // generates a future date within one year from now
                random.nextInt(MIN_PRIORITY_LEVEL, MAX_PRIORITY_LEVEL),
                Optional.empty(),
                repFiscalCodes.get(this.random.nextInt(N_REP))
            );
            operationIDs.add(requestID);
        }
        return operationIDs;
    }

    private void createDescriptionsAndLinkDevicesToOperations(
        final List<String> operationsIDs,
        final List<String> cpuIDs, 
        final List<String> ramIDs,
        final List<String> massStorageIDs,
        final List<String> chassisIDs,
        final List<String> otherComponentIDs,
        final List<String> monitorIDs,
        final List<String> otherPeripheralIDs
    ) {
        for (int i = 0; i < N_DONATIONS; i++) {
            // Link a random number of objects descriptions to the operation
            for (int j = 0; j < this.random.nextInt(1, MAX_OPERATION_OBJECTS); j++) {
                operationsController.addObjectDescription(
                    operationsIDs.get(i),
                    j + 1,
                    Generator.getRandomDeviceType(),
                    1,
                    Optional.empty()
                );
            }
        }
    }

    private List<String> createCPUs() {
        final int DEFAULT_BITS = 32;
        final List<String> cpuIDs = new LinkedList<>();
        for (int i = 0; i < N_CPU; i++) {
            final String cpuID = Generator.generateComponentID();
            inventoryController.addCPU(
                cpuID,
                faker.device().manufacturer(),  // generated manufacturers names are not very appropriate
                "",     // should find a way to generate models names
                Optional.empty(),
                DEFAULT_BITS
            );
            cpuIDs.add(cpuID);
        }
        return cpuIDs;
    }

    private List<String> createRAMModules() {
        final int DEFAULT_MB_SIZE = 1024;
        final List<String> ramIDs = new LinkedList<>();
        for (int i = 0; i < N_RAM; i++) {
            final String ramID = Generator.generateComponentID();
            inventoryController.addRAM(
                ramID,
                faker.device().manufacturer(),  // generated manufacturers names are not very appropriate
                "",     // should find a way to generate models names
                Optional.empty(),
                DEFAULT_MB_SIZE
            );
            ramIDs.add(ramID);
        }
        return ramIDs;
    }

    private List<String> createMassStorageDevices() {
        final int DEFAULT_GB_SIZE = 512;
        final List<String> massStorageIDs = new LinkedList<>();
        for (int i = 0; i < N_MASS_STORAGES; i++) {
            final String massStorageID = Generator.generateComponentID();
            inventoryController.addMassStorage(
                massStorageID,
                faker.device().manufacturer(),  // generated manufacturers names are not very appropriate
                "",     // should find a way to generate models names
                Optional.empty(),
                Generator.getRandomMassStorageType(),
                DEFAULT_GB_SIZE
            );
            massStorageIDs.add(massStorageID);
        }
        return massStorageIDs;
    }

    private List<String> createChassisUnits() {
        final List<String> chassisIDs = new LinkedList<>();
        for (int i = 0; i < N_CHASSIS_UNITS; i++) {
            final String chassisID = Generator.generateComponentID();
            inventoryController.addChassis(
                chassisID,
                faker.device().manufacturer(),  // generated manufacturers names are not very appropriate
                "",     // should find a way to generate models names
                Optional.empty(),
                faker.color().name()
            );
            chassisIDs.add(chassisID);
        }
        return chassisIDs;
    }

    private List<String> createOtherTypesOfComponents() {
        final String NETWORK_ADAPTER = "Scheda di rete";    // example of another type of component
        final List<String> componentIDs = new LinkedList<>();
        for (int i = 0; i < N_OTHER_COMPONENTS_TYPES; i++) {
            final String componentID = Generator.generateComponentID();
            inventoryController.addComponent(
                componentID, 
                NETWORK_ADAPTER,
                faker.device().manufacturer(),  // generated manufacturers names are not very appropriate
                "",     // should find a way to generate models names
                Optional.empty()
            );
            componentIDs.add(componentID);
        }
        return componentIDs;
    }

    private List<String> createMonitors() {
        final String DEFAULT_CONNECTIVITY = "Wired";
        final String DEFAULT_MONITOR_TYPE = "LCD";
        final int MIN_MONITOR_SIZE = 15;
        final int MAX_MONITOR_SIZE = 19;
        final String DEFAULT_ASPECT_RATIO = "16:9";
        final List<String> monitorIDs = new LinkedList<>();
        for (int i = 0; i < N_MONITORS; i++) {
            final String monitorID = Generator.generateMonitorID();
            inventoryController.addMonitor(
                monitorID,
                faker.device().manufacturer(), 
                "",     // should find a way to generate models names
                DEFAULT_CONNECTIVITY,
                Optional.empty(),
                DEFAULT_MONITOR_TYPE,
                this.random.nextInt(MIN_MONITOR_SIZE, MAX_MONITOR_SIZE + 1),
                DEFAULT_ASPECT_RATIO,
                this.random.nextBoolean(),
                this.random.nextBoolean(),
                this.random.nextBoolean()
            );
            monitorIDs.add(monitorID);
        }
        return monitorIDs;
    }

    private List<String> createOtherPeripherals() {
        final String DEFAULT_CONNECTIVITY = "Wired";
        final List<String> peripheralIDs = new LinkedList<>();
        for (int i = 0; i < N_OTHER_PERIPHERALS; i++) {
            final String peripheralID = Generator.generatePeripheralID();
            String peripheralType = CommonPeripheralTypes.getRandomPeripheralType();
            // Exclude monitors since they require to generate more info which are not generated in this method
            while (peripheralType.equals(CommonPeripheralTypes.MONITOR.getType())) {
                // Regenerate peripheral type
                peripheralType = CommonPeripheralTypes.getRandomPeripheralType();
            }
            inventoryController.addPeripheral(
                peripheralID, 
                peripheralType, 
                faker.device().manufacturer(), 
                "",     // should find a way to generate models names
                DEFAULT_CONNECTIVITY,
                Optional.empty()
            );
            peripheralIDs.add(peripheralID);
        }
        return peripheralIDs;
    }

    private List<String> createDesktopPCs(
        final List<String> cpuIDs,
        final List<String> ramIDs,
        final List<String> massStorageIDs,
        final List<String> chassisIDs,
        final List<String> monitorIDs
    ) {
        final List<String> pcIDs = new LinkedList<>();
        for (int i = 0; i < N_DESKTOP_PC; i++) {
            final String pcID = Generator.generatePcID();
            inventoryController.addDesktopPC(
                pcID,
                cpuIDs.get(this.random.nextInt(cpuIDs.size())),
                massStorageIDs.get(this.random.nextInt(massStorageIDs.size())),
                Optional.empty(),
                ramIDs.get(this.random.nextInt(ramIDs.size())),
                Optional.of(ramIDs.get(this.random.nextInt(ramIDs.size()))),
                Optional.empty(),
                Optional.empty(),
                this.random.nextBoolean(),
                this.random.nextBoolean(),
                this.random.nextBoolean(),
                chassisIDs.get(this.random.nextInt(chassisIDs.size())),
                Optional.of(monitorIDs.get(this.random.nextInt(monitorIDs.size()))),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty()
            );
            pcIDs.add(pcID);
        }
        return pcIDs;
    }

    private List<String> createLaptops(
        final List<String> cpuIDs,
        final List<String> ramIDs,
        final List<String> massStorageIDs
    ) {
        final int MIN_LAPTOP_SIZE = 11;
        final int MAX_LAPTOP_SIZE = 17;
        final List<String> pcIDs = new LinkedList<>();
        for (int i = 0; i < N_LAPTOPS; i++) {
            final String pcID = Generator.generatePcID();
            inventoryController.addLaptop(
                pcID,
                cpuIDs.get(this.random.nextInt(cpuIDs.size())),
                massStorageIDs.get(this.random.nextInt(massStorageIDs.size())),
                Optional.empty(),
                ramIDs.get(this.random.nextInt(ramIDs.size())),
                Optional.of(ramIDs.get(this.random.nextInt(ramIDs.size()))),
                Optional.empty(),
                Optional.empty(),
                this.random.nextBoolean(),
                this.random.nextBoolean(),
                this.random.nextBoolean(),
                faker.computer().brand(),
                "",     // should find a way to generate models names
                this.random.nextInt(MIN_LAPTOP_SIZE, MAX_LAPTOP_SIZE + 1),
                faker.color().name(),
                Optional.empty()
            );
            pcIDs.add(pcID);
        }
        return pcIDs;
    }

    private void createOSInstallations(final List<String> pcIDs) {
        // Create a copy of the list. Since the variety of generated OS names and versions is not very wide,
        // each pcID inserted will be removed by the copy of the list to avoid duplicate tuples more easily.
        final List<String> pcIDsCopy = new LinkedList<>(pcIDs);
        for (int i = 0; i < N_OS_INSTALLATIONS; i++) {
            // The generated OS name also contains the OS version
            // Remove parenthesis that are eventually present in the generated OS name
            final String osNameAndVersion = faker.computer().operatingSystem().replaceAll("[\\[\\]()]", "");
            // Parse generated OS name + version to split name and version into two strings
            final OptionalInt firstDigitIndex = IntStream.range(0, osNameAndVersion.length())
                    .filter(j -> Character.isDigit(osNameAndVersion.charAt(j)))
                    .findFirst();
            final String osName = osNameAndVersion.substring(0, firstDigitIndex.getAsInt());
            final String osVersion = osNameAndVersion.substring(firstDigitIndex.getAsInt());
            final String pcID = pcIDsCopy.get(this.random.nextInt(pcIDsCopy.size()));
           inventoryController.addOperatingSystem(
                pcID,
                osName,
                osVersion,
                faker.date().past(365, TimeUnit.DAYS).toLocalDateTime().toLocalDate()
            );
            pcIDsCopy.remove(pcID);
        }
    }

}