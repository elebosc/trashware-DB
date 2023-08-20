package it.unibo.trashware.view;

import java.io.IOException;

import it.unibo.trashware.view.controllers.SceneController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class JavaFXView implements View {

    private final Stage stage;
    // private final Controller controller;

    @FXML
    private Pane sidePane;

    public JavaFXView(final Stage stage) {
        this.stage = stage;
    }

    @Override
    public void setScene(final ScenesConfig sceneConfig) throws IOException {
        final FXMLLoader loader = new FXMLLoader();
        final Parent root = loader.load(this.getClass().getResourceAsStream(sceneConfig.getFXMLFilePath()));
        final SceneController sceneController = loader.getController();
        // sceneController.init(this, this.controller);
        final Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    @Override
    public void setPane(final PanesConfig pane) throws IOException {
        final FXMLLoader loader = new FXMLLoader();
        final Node node = loader.load(this.getClass().getResourceAsStream(pane.getFXMLFilePath()));
        this.sidePane.getChildren().add(node);
    }

    @Override
    public void show() {
        this.stage.show();
    }
    
}
