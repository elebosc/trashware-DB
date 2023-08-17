package it.unibo.populator.utils;

import java.util.Random;

/**
 * This enum provides constants for the most common types of donated/requested components.
 */
public enum CommonComponentTypes {

    CPU("CPU"),
    RAM("RAM"),
    MASS_STORAGE("Memoria di massa"),
    CHASSIS("Case");

    private static final Random RANDOM = new Random();

    private final String type;
    
    private CommonComponentTypes(final String deviceType) {
        this.type = deviceType;
    }

    public String getType() {
        return this.type;
    }

    public static String getRandomComponentType() {
        return values()[RANDOM.nextInt(values().length)].getType();
    }

}
