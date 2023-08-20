package it.unibo.trashware.view;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class JavaFXView implements View {

    private static final String MAIN_SCENE_FXML = "layouts/main.fxml";

    private final Stage stage;

    // @FXML
    // private Pane sidePane;

    public JavaFXView(final Stage stage) throws IOException {
        this.stage = stage;
        // Load the main scene
        final Parent root = FXMLLoader.load(ClassLoader.getSystemResource(MAIN_SCENE_FXML));
        // final SceneController sceneController = loader.getController();
        // sceneController.init(this, this.controller);
        final Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    @Override
    public void setPane(final PanesConfig pane) throws IOException {
        // final FXMLLoader loader = new FXMLLoader();
        // final Node node = loader.load(this.getClass().getResourceAsStream(pane.getFXMLFilePath()));
        // this.sidePane.getChildren().add(node);
    }

    @Override
    public void show() {
        this.stage.show();
    }
    
}
