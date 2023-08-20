package it.unibo.trashware;

import java.io.IOException;

import it.unibo.trashware.view.JavaFXApp;
import javafx.application.Application;

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
        Application.launch(JavaFXApp.class);
    }
}
