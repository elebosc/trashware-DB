package it.unibo.trashware.view;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * This class defines the application as a JavaFX application.
 */
public final class JavaFXApp extends Application {

    @Override
    public void start(final Stage stage) throws Exception {

        stage.setTitle("TrashwareDB");
        final View view = new JavaFXView(stage);
        view.setScene(ScenesConfig.MAIN);

        // final Controller controller = new ControllerImpl();
        // final Populator populator = new PopulatorImpl(controller);
        // populator.populateDB();

        view.show();
    }

}