package it.unibo.trashware.view.controllers.subpages;

import java.io.IOException;
import java.util.Optional;

import it.unibo.trashware.controller.api.InventoryController;
import it.unibo.trashware.controller.impl.InventoryControllerImpl;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class CPUFormController {

    @FXML
    private TextField IDField;

    @FXML
    private ComboBox<String> architectureComboBox;

    @FXML
    private TextField brandField;

    @FXML
    private Button insertBtn;

    @FXML
    private TextField modelField;

    @FXML
    private TextArea notesField;

    private InventoryController controller;

    @FXML
    public void initialize() {
        try {
            this.controller = new InventoryControllerImpl();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.architectureComboBox.setItems(FXCollections.observableArrayList("32", "64"));
        this.insertBtn.setOnAction(e -> {
            try {
                insertCPUData();
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

    private void insertCPUData() {
        this.controller.addCPU(
            this.IDField.getText(),
            this.brandField.getText(), 
            this.modelField.getText(), 
            this.notesField.getText().equals("")
                ? Optional.empty()
                : Optional.of(this.notesField.getText()),
            Integer.parseInt(this.architectureComboBox.getValue())
        );
    }

}

