package it.unibo.trashware.view;

import java.io.IOException;

import it.unibo.trashware.view.controllers.MainSceneController;
import it.unibo.trashware.view.controllers.Pages;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class JavaFXView implements View {

    private static final String MAIN_SCENE_FXML = "it/unibo/trashware/layouts/main.fxml";

    private final Stage stage;
    private final Scene mainScene;
    private final MainSceneController controller;
    private Node currentPage;

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
        try {
            final Node node = FXMLLoader.load(ClassLoader.getSystemResource(page.getFXMLFilePath()));
            if (this.currentPage != null) {
                this.controller.getSidePage().getChildren().remove(this.currentPage);
            }
            this.controller.getSidePage().getChildren().add(node);
            this.currentPage = node;
        } catch (final IOException ex) {
            final Alert alert = new Alert(AlertType.ERROR);
            alert.setHeaderText("Errore: impossibile aprire la pagina.");
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }
    }

    @Override
    public void show() {
        this.stage.show();
    }
    
}
