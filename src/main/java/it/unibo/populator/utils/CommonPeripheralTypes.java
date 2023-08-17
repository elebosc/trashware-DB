package it.unibo.populator.utils;

import java.util.Random;

/**
 * This enum provides constants for the most common types of donated/requested components.
 */
public enum CommonPeripheralTypes {

    MONITOR("Monitor"),
    KEYBOARD("Tastiera"),
    MOUSE("Mouse"),
    SPEAKERS("Casse Audio");

    private static final Random RANDOM = new Random();

    private final String type;
    
    private CommonPeripheralTypes(final String deviceType) {
        this.type = deviceType;
    }

    public String getType() {
        return this.type;
    }

    public static String getRandomPeripheralType() {
        return values()[RANDOM.nextInt(values().length)].getType();
    }

}
