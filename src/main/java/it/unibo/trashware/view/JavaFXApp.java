package it.unibo.trashware.view;

import it.unibo.populator.Populator;
import it.unibo.populator.PopulatorImpl;
import it.unibo.trashware.controller.api.InventoryController;
import it.unibo.trashware.controller.api.OperationsController;
import it.unibo.trashware.controller.api.WorkShiftsController;
import it.unibo.trashware.controller.impl.InventoryControllerImpl;
import it.unibo.trashware.controller.impl.OperationsControllerImpl;
import it.unibo.trashware.controller.impl.WorkShiftsControllerImpl;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * This class defines the application as a JavaFX application.
 */
public final class JavaFXApp extends Application {

    @Override
    public void start(final Stage stage) throws Exception {

        // final OperationsController opController = new OperationsControllerImpl();
        // final InventoryController invController = new InventoryControllerImpl();
        // final WorkShiftsController wsController = new WorkShiftsControllerImpl();
        // final Populator populator = new PopulatorImpl(opController, invController, wsController);
        // populator.populateDB();

        stage.setTitle("TrashwareDB");
        final View view = new JavaFXView(stage);
        view.setScene(ScenesConfig.MAIN);

        // final Populator populator = new PopulatorImpl(controller);
        // populator.populateDB();

        view.show();
    }

}