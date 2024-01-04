package it.unibo.trashware.view.controllers.subpages;

import java.io.IOException;
import java.util.Optional;

import it.unibo.trashware.controller.api.InventoryController;
import it.unibo.trashware.controller.impl.InventoryControllerImpl;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class RAMFormController {

    @FXML
    private TextField IDField;

    @FXML
    private TextField brandField;

    @FXML
    private Button insertBtn;

    @FXML
    private TextField modelField;

    @FXML
    private TextArea notesField;

    @FXML
    private TextField sizeField;

    private InventoryController controller;

    @FXML
    public void initialize() {
        try {
            this.controller = new InventoryControllerImpl();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.insertBtn.setOnAction(e -> {
            try {
                insertRAMModuleData();
                final Alert alert = new Alert(AlertType.INFORMATION);
                alert.setHeaderText("Info");
                alert.setContentText("Dispositivo registrato con successo.");
                alert.showAndWait();
            } catch (final Exception ex) {
                ex.printStackTrace();
                final Alert alert = new Alert(AlertType.ERROR);
                alert.setHeaderText("Errore");
                alert.setContentText(ex.getMessage());
                alert.showAndWait();
            }
        });
    }

    private void insertRAMModuleData() {
        this.controller.addRAM(
            this.IDField.getText(),
            this.brandField.getText(), 
            this.modelField.getText(), 
            this.notesField.getText().equals("")
                ? Optional.empty()
                : Optional.of(this.notesField.getText()),
            Integer.parseInt(this.sizeField.getText())
        );
    }

}

