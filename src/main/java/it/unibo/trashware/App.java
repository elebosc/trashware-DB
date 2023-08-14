package it.unibo.trashware;

import java.io.IOException;

import it.unibo.populator.Populator;
import it.unibo.trashware.controller.Controller;
import it.unibo.trashware.controller.ControllerImpl;

/**
 * This class provides the entry point for the application.
 */
public final class App {
    
    private App() {
        // Empty
    }

    /**
     * The entry point of the application.
     * @param args unused
     * @throws IOException if an error occurs while populating or connecting to the database.
     */
    public static void main(final String... args) throws IOException {
        final Controller controller = new ControllerImpl();
        Populator.populateDB(controller);
    }
}
