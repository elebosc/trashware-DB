package it.unibo.trashware;

import java.io.IOException;

import it.unibo.populator.Populator;
import it.unibo.populator.PopulatorImpl;
import it.unibo.trashware.controller.api.InventoryController;
import it.unibo.trashware.controller.api.OperationsController;
import it.unibo.trashware.controller.api.WorkShiftsController;
import it.unibo.trashware.controller.impl.InventoryControllerImpl;
import it.unibo.trashware.controller.impl.OperationsControllerImpl;
import it.unibo.trashware.controller.impl.WorkShiftsControllerImpl;

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
        final OperationsController opController = new OperationsControllerImpl();
        final InventoryController invController = new InventoryControllerImpl();
        final WorkShiftsController wsController = new WorkShiftsControllerImpl();
        final Populator populator = new PopulatorImpl(opController, invController, wsController);
        populator.populateDB();
    }
}
