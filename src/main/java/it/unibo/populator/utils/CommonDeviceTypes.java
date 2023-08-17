package it.unibo.populator.utils;

import java.util.Random;

/**
 * This enum provides constants for the most common types of donated/requested devices.
 */
public enum CommonDeviceTypes {

    DESKTOP_PC("PC Desktop"),
    LAPTOP("PC Portatile"),
    MONITOR("Monitor"),
    KEYBOARD("Tastiera"),
    MOUSE("Mouse"),
    SPEAKERS("Casse Audio"),
    CPU("CPU"),
    RAM("RAM"),
    MASS_STORAGE("Memoria di massa"),
    CHASSIS("Case");

    private static final Random RANDOM = new Random();

    private final String type;
    
    private CommonDeviceTypes(final String deviceType) {
        this.type = deviceType;
    }

    public String getType() {
        return this.type;
    }

    public static String getRandomDeviceType() {
        return values()[RANDOM.nextInt(values().length)].getType();
    }

}
