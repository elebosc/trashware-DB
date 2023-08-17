package it.unibo.populator.utils;

import java.io.IOException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import it.unibo.populator.utils.fiscalcode.ComputeFiscalCode;

/**
 * This class provides methods to generate other types of data that the faker is not able to generate.
 */
public final class Generator {

	private static final Random RANDOM = new Random();
	private static final int DEFAULT_CODE_LENGTH = 7;
    
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
                        gender[RANDOM.nextInt(gender.length)])
                        // gender is not chosen according to the name;
                        // it is not accurate, but it is not relevant to the purpose of the generated fake data.
                )
                .append(ComputeFiscalCode.computeTownOfBirth(birthplace))
                .toString();
        return new StringBuilder().append(incompleteFiscalCode)
                .append(ComputeFiscalCode.computeControlChar(incompleteFiscalCode))
                .toString();
    }

	/**
	 * Generates a random numeric code.
	 * @param length the length of the code to generate.
	 * @return the generated code
	 */
	public static String generateNumericCode(final int length) {
		final int DIGIT_BOUND_EXCLUSIVE = 10;
		return IntStream.generate(() -> RANDOM.nextInt(DIGIT_BOUND_EXCLUSIVE))
				.limit(length)
				.mapToObj(digit -> String.valueOf(digit))
				.collect(Collectors.joining());
	}

	private static String generateID(String prefix, int numericCodeLength) {
		final String number = generateNumericCode(numericCodeLength);
		return prefix + number;
	}

	/**
	 * Generates a random ID for a donation.
	 * @return the generated donation ID
	 */
	public static String generateDonationID() {
		return generateID("DON", DEFAULT_CODE_LENGTH);
	}

	/**
	 * Generates a random ID for a request.
	 * @return the generated request ID
	 */
	public static String generateRequestID() {
		return generateID("REQ", DEFAULT_CODE_LENGTH);
	}

	/**
	 * @return a random component type
	 */
	public static String getRandomComponentType() {
		return CommonComponentTypes.getRandomComponentType();
	}

	/**
	 * @return a random peripheral type
	 */
	public static String getRandomPeripheralType() {
		return CommonPeripheralTypes.getRandomPeripheralType();
	}

	/**
	 * @return a random device type
	 */
	public static String getRandomDeviceType() {
		final List<String> devices = new LinkedList<>();
		final String peripheral = CommonPeripheralTypes.getRandomPeripheralType();
		final String component = CommonComponentTypes.getRandomComponentType();
		devices.add(peripheral);
		devices.add(component);
		return devices.get(RANDOM.nextInt(devices.size()));
	}

	/**
	 * @return a random mass storage type
	 */
	public static String getRandomMassStorageType() {
		return MassStorageTypes.getRandomMassStorageType();
	}

	/**
	 * Generates a random ID for a component.
	 * @return the generated component ID
	 */
	public static String generateComponentID() {
		return generateID("CMP", DEFAULT_CODE_LENGTH);
	}

	/**
	 * Generates a random ID for a peripheral.
	 * @return the generated peripheral ID
	 */
	public static String generatePeripheralID() {
		return generateID("PRF", DEFAULT_CODE_LENGTH);
	}

	/**
	 * Generates a random ID for a monitor.
	 * @return the generated monitor ID
	 */
	public static String generateMonitorID() {
		return generateID("MTR", DEFAULT_CODE_LENGTH);
	}

}
