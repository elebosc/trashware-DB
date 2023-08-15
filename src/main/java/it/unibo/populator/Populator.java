package it.unibo.populator;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import it.unibo.populator.utils.Generator;
import it.unibo.trashware.controller.Controller;
import net.datafaker.Faker;
import net.datafaker.providers.base.Address;

/**
 * The database populator.
 */
public final class Populator {

    private static final int N_REP = 50;
    private static final int N_SOCIETIES = 25;

    /**
     * This method populates the database by interacting the application controller, which in turn interacts
     * with the underlying services to enable data storing.
     * @param controller the controller of the application
     * @throws IOException if an error occurs during an I/O operation related to the data generation.
     */
    public static void populateDB(final Controller controller) throws IOException {
        final Faker faker = new Faker(Locale.ITALY);
        populateRepresentativesTable(controller, faker);
        populateSocietiesTable(controller, faker);
    }

    private static void populateRepresentativesTable(final Controller controller, final Faker faker) throws IOException {
        for (int i = 0; i < N_REP; i++) {
            String name = faker.name().firstName();
            String surname = faker.name().lastName();
            String birthplace = faker.address().city();
            LocalDate birthday = faker.date().birthday().toLocalDateTime().toLocalDate();
            Address address = faker.address();
            String emailLocalPart = name.toLowerCase() + "." + surname.toLowerCase();
            controller.addRepresentative(
                Generator.generateFiscalCode(name, surname, birthplace, birthday),
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
        }
    }

    private static void populateSocietiesTable(final Controller controller, final Faker faker) throws IOException {
        final int UPPER_BOUND = 10;
        final int VAT_NUMBER_LENGTH = 11;
        for (int i = 0; i < N_SOCIETIES; i++) {
            // The fiscal code of an Italian society is the fiscal code of the owner
            String ownerName = faker.name().firstName();
            String ownerSurname = faker.name().lastName();
            String ownerBirthplace = faker.address().city();
            LocalDate ownerBirthday = faker.date().birthday().toLocalDateTime().toLocalDate();
            Address address = faker.address();
            controller.addSociety(
                // The validity of the VAT number is not relevant to the purpose of tha generated fake data,
                // so a random 11 digits long code will be generated for simplicity.
                IntStream.generate(() -> new Random().nextInt(UPPER_BOUND))
                        .limit(VAT_NUMBER_LENGTH)
                        .mapToObj(digit -> String.valueOf(digit))
                        .collect(Collectors.joining()),
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
        }
    }
    
}