package it.unibo.trashware.view;

import it.unibo.trashware.controller.Controller;
import it.unibo.trashware.controller.ControllerImpl;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * This class defines the application as a JavaFX application.
 */
public final class JavaFXApp extends Application {

    @Override
    public void start(final Stage stage) throws Exception {

        final Controller controller = new ControllerImpl();

        stage.setTitle("TrashwareDB");
        final View view = new JavaFXView(stage, controller);
        view.setScene(ScenesConfig.MAIN);

        // final Populator populator = new PopulatorImpl(controller);
        // populator.populateDB();

        view.show();
    }

}