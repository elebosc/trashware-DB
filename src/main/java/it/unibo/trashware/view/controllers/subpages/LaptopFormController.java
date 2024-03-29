package it.unibo.trashware.view.controllers.subpages;

import java.io.IOException;
import java.util.Optional;

import it.unibo.trashware.controller.api.InventoryController;
import it.unibo.trashware.controller.impl.InventoryControllerImpl;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class LaptopFormController {

    @FXML
    private TextField CPUIDField;

    @FXML
    private TextField OSNameField;

    @FXML
    private TextField OSVersionField;

    @FXML
    private TextField PCIDField;

    @FXML
    private TextField RAM01IDField;

    @FXML
    private TextField RAM02IDField;

    @FXML
    private TextField RAM03IDField;

    @FXML
    private TextField RAM04IDField;

    @FXML
    private CheckBox bluetoothCheckBox;

    @FXML
    private TextField brandField;

    @FXML
    private TextField colorField;

    @FXML
    private CheckBox ethernetCheckBox;

    @FXML
    private Button insertBtn;

    @FXML
    private DatePicker lastOSUpdateDatePicker;

    @FXML
    private TextField modelField;

    @FXML
    private TextField ms01IDField;

    @FXML
    private TextField ms02IDField;

    @FXML
    private TextArea notesField;

    @FXML
    private TextField otherComponentIDField;

    @FXML
    private TextField sizeField;

    @FXML
    private CheckBox wifiCheckBox;

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
                insertLaptopData();
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

    private void insertLaptopData() {
        this.controller.addLaptop(
            this.PCIDField.getText(),
            this.CPUIDField.getText(),
            this.ms01IDField.getText(),
            this.ms02IDField.getText().equals("") ? Optional.empty() : Optional.of(this.ms02IDField.getText()),
            this.RAM01IDField.getText(),
            this.RAM02IDField.getText().equals("") ? Optional.empty() : Optional.of(this.RAM02IDField.getText()),
            this.RAM03IDField.getText().equals("") ? Optional.empty() : Optional.of(this.RAM03IDField.getText()),
            this.RAM04IDField.getText().equals("") ? Optional.empty() : Optional.of(this.RAM04IDField.getText()),
            this.ethernetCheckBox.isSelected(),
            this.wifiCheckBox.isSelected(),
            this.bluetoothCheckBox.isSelected(),
            this.brandField.getText(),
            this.modelField.getText(),
            Integer.parseInt(this.sizeField.getText()),
            this.colorField.getText(),
            this.notesField.getText().equals("") ? Optional.empty() : Optional.of(this.notesField.getText())
        );
        this.controller.addOperatingSystem(
            this.PCIDField.getText(), 
            this.OSNameField.getText(), 
            this.OSVersionField.getText(),
            this.lastOSUpdateDatePicker.getValue() 
        );
        if (!this.otherComponentIDField.getText().equals("")) {
            this.controller.associateOtherComponentToPC(this.otherComponentIDField.getText(), this.PCIDField.getText());
        }
    }

}
