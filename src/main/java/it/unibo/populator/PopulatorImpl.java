package it.unibo.populator;

import java.io.IOException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import it.unibo.populator.utils.Generator;
import it.unibo.trashware.controller.Controller;
import net.datafaker.Faker;
import net.datafaker.providers.base.Address;

/**
 * Implementation of the database populator.
 */
public final class PopulatorImpl implements Populator {

    private static final int N_REP = 50;
    private static final int N_SOCIETIES = 25;
    private static final int N_DONATIONS = 25;
    private static final int N_REQUESTS = 25;

    private Controller controller;
    private Faker faker;

    /**
     * Creates a new database populator.
     * @param controller the controller of the application
     */
    public PopulatorImpl(final Controller controller) {
        this.controller = controller;
        this.faker = new Faker(Locale.ITALY);
    }

    @Override
    public void populateDB() throws IOException {
        final List<String> repFiscalCodes = createRepresentatives();
        final List<String> societiesVATNumbers = createSocieties();
        createRepresentations(repFiscalCodes, societiesVATNumbers);
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
            controller.addRepresentative(
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
            controller.addSociety(
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
        final Random rand = new Random();
        for (final String societyID : societiesVATNumbers) {
            controller.addRepresentation(
                societyID,
                repFiscalCodes.get(rand.nextInt(N_REP)),
                faker.company().profession()
            );
        }
    }

    private void createOperations() {
        for (int i = 0; i < N_DONATIONS; i++) {
            controller.addDonation(
                Generator.generateDonationID(),
                faker.date().future(365, TimeUnit.DAYS).toLocalDateTime().toLocalDate(), // generates a future date within one year from now
                Optional.empty(),   // empty notes
                null);
        }
    }
    
}