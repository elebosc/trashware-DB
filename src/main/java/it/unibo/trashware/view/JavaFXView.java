package it.unibo.trashware.view;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class JavaFXView implements View {

    private final Stage stage;

    public JavaFXView(final Stage stage) {
        this.stage = stage;
    }

    @Override
    public void setScene(final ScenesConfig sceneConfig) throws IOException {
        final Parent root = FXMLLoader.load(ClassLoader.getSystemResource(sceneConfig.getFXMLFilePath()));
        final Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    @Override
    public void show() {
        this.stage.show();
    }
    
}
