package it.unibo.populator.utils;

import java.util.Random;

/**
 * This enum provides constants for mass storage devices types.
 */
public enum MassStorageTypes {

    HDD("HDD"),
    SSD("SSD");
    
    private static final Random RANDOM = new Random();

    private final String type;
    
    private MassStorageTypes(final String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    public static String getRandomMassStorageType() {
        return values()[RANDOM.nextInt(values().length)].getType();
    }
}
