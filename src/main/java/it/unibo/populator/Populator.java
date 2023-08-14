package it.unibo.populator;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Optional;

import it.unibo.populator.utils.Generator;
import it.unibo.trashware.controller.Controller;
import net.datafaker.Faker;
import net.datafaker.providers.base.Address;

/**
 * The database populator.
 */
public final class Populator {

    private static final int N_REP = 50;

    /**
     * This method populates the database by interacting the application controller, which in turn interacts
     * with the underlying services to enable data storing.
     * @param controller the controller of the application
     * @throws IOException if an error occurs during an I/O operation related to the data generation.
     */
    public static void populateDB(final Controller controller) throws IOException {
        final Faker faker = new Faker(Locale.ITALY);
        populateRepresentativesTable(controller, faker);
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
    
}