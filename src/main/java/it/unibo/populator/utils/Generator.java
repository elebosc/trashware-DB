package it.unibo.populator.utils;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Random;

import it.unibo.populator.utils.fiscalcode.ComputeFiscalCode;

/**
 * This class provides methods to generate other types of data that the faker is not able to generate.
 */
public final class Generator {
    
	/**
	 * Generates the fiscal code according to the given name, surname, birthplace and birthday.
	 * @param name
	 * @param surname
	 * @param birthplace
	 * @param birthday
	 * @return the generated fiscal code
	 * @throws IOException if an error occurs while reading the code associated to the birthplace city.
	 */
    public static String generateFiscalCode(final String name, final String surname, final String birthplace,
            final LocalDate birthday) throws IOException {
        final String[] gender = new String[]{"m", "f"};
        final String incompleteFiscalCode = new StringBuilder()
                .append(ComputeFiscalCode.computeSurname(surname))
                .append(ComputeFiscalCode.computeName(name))
                .append(ComputeFiscalCode.computeDateOfBirth(
                        birthday.getDayOfMonth(),
                        birthday.getMonthValue(),
                        birthday.getYear(),
                        gender[new Random().nextInt(gender.length)])
                        // gender is not chosen according to the name;
                        // it is not accurate, but it is not relevant to the purpose of the generated fake data.
                )
                .append(ComputeFiscalCode.computeTownOfBirth(birthplace))
                .toString();
        return new StringBuilder().append(incompleteFiscalCode)
                .append(ComputeFiscalCode.computeControlChar(incompleteFiscalCode))
                .toString();
    }
}
