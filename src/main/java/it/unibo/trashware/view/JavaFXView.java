package it.unibo.trashware.view;

import java.io.IOException;

import it.unibo.trashware.view.controllers.MainSceneController;
import it.unibo.trashware.view.controllers.Pages;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class JavaFXView implements View {

    private static final String MAIN_SCENE_FXML = "layouts/main.fxml";

    private final Stage stage;
    private final Scene mainScene;
    private final MainSceneController controller;

    public JavaFXView(final Stage stage) throws IOException {
        this.stage = stage;
        // Load the main scene
        final FXMLLoader loader = new FXMLLoader();
        final Parent root = loader.load(ClassLoader.getSystemResourceAsStream(MAIN_SCENE_FXML));
        this.controller = loader.getController();
        this.controller.init(this);
        this.mainScene = new Scene(root);
        stage.setScene(this.mainScene);
    }

    @Override
    public void setPage(final Pages page) {
        final FXMLLoader loader = new FXMLLoader();
        try {
            final Node node = loader.load(ClassLoader.getSystemResourceAsStream(page.getFXMLFilePath()));
            this.controller.getSidePage().getChildren().add(node);
        } catch (final IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void show() {
        this.stage.show();
    }
    
}
