package it.unibo.trashware.view.controllers;

import java.io.IOException;

import it.unibo.trashware.controller.api.InventoryController;
import it.unibo.trashware.controller.impl.InventoryControllerImpl;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;

public class AssociateDeviceToPCPageController {

    private static final String DESKTOP = "Desktop";
    private static final String LAPTOP = "Portatile";
    private static final String CPU = "CPU";
    private static final String RAM_01 = "RAM 01";
    private static final String RAM_02 = "RAM 02";
    private static final String RAM_03 = "RAM 03";
    private static final String RAM_04 = "RAM 04";
    private static final String STORAGE_01 = "Memoria di massa 01";
    private static final String STORAGE_02 = "Memoria di massa 02";
    private static final String CHASSIS = "Chassis";
    private static final String OTH_COMP = "Altro componente";
    private static final String MONITOR = "Monitor";
    private static final String KEYBOARD = "Tastiera";
    private static final String MOUSE = "Mouse";
    private static final String SPEAKERS = "Casse audio";

    @FXML
    private TextField deviceID;

    @FXML
    private ComboBox<String> deviceTypeComboBox;

    @FXML
    private Button insertBtn;

    @FXML
    private TextField pcID;

    @FXML
    private ComboBox<String> pcTypeComboBox;

    private InventoryController controller;

    @FXML
    public void initialize() {
        try {
            this.controller = new InventoryControllerImpl();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.pcTypeComboBox.setItems(FXCollections.observableArrayList(DESKTOP, LAPTOP));
        // Change device type combobox items according to the selected PC type
        this.pcTypeComboBox.setCellFactory(lv -> {
            ListCell<String> cell = new ListCell<String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    setText(empty ? null : item);
                }
            };
            cell.setOnMousePressed(e -> {
                switch (this.pcTypeComboBox.getValue()) {
                    case DESKTOP:
                        this.deviceTypeComboBox.setItems(FXCollections.observableArrayList(
                            CPU, RAM_01, RAM_02, RAM_03, RAM_04, STORAGE_01, STORAGE_02, CHASSIS, OTH_COMP, MONITOR, KEYBOARD, MOUSE, SPEAKERS
                        ));
                        break;
                    case LAPTOP:
                        this.deviceTypeComboBox.setItems(FXCollections.observableArrayList(
                            CPU, RAM_01, RAM_02, RAM_03, RAM_04, STORAGE_01, STORAGE_02, OTH_COMP
                        ));
                        break;
                    default:
                        break;
                }
            });
            return cell;
        });
        // When the "Insert" button is pressed
        this.insertBtn.setOnAction(e -> {
            if (this.pcID.getText().equals("") || this.deviceID.getText().equals("")) {
                final Alert alert = new Alert(AlertType.ERROR);
                alert.setHeaderText("Errore");
                alert.setContentText("Specificare sia l'ID del PC, sia l'ID del dispositivo da associare.");
                alert.showAndWait();
            } else if (this.pcTypeComboBox.getValue() == null || this.deviceTypeComboBox.getValue() == null) {
                final Alert alert = new Alert(AlertType.ERROR);
                alert.setHeaderText("Errore");
                alert.setContentText("Specificare sia il tipo di PC, sia il tipo di dispositivo da associare.");
                alert.showAndWait();
            } else {
                try {
                    switch (this.deviceTypeComboBox.getValue()) {
                        case CPU:
                            this.controller.associateCPUToPC(this.deviceID.getText(), this.pcID.getText());
                            break;
                        case RAM_01:
                            break;
                        case RAM_02:
                            break;
                        case RAM_03:
                            break;
                        case RAM_04:
                            break;
                        case STORAGE_01:
                            break;
                        case STORAGE_02:
                            break;
                        case CHASSIS:
                            break;
                        case OTH_COMP:
                            break;
                        case MONITOR:
                            break;
                        case KEYBOARD:
                            break;
                        case MOUSE:
                            break;
                        case SPEAKERS:
                            break;
                        default:
                            break;
                    }
                    final Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setHeaderText("Info");
                    alert.setContentText("Associazione registrata con successo.");
                    alert.showAndWait();
                } catch (final Exception ex) {
                    final Alert alert = new Alert(AlertType.ERROR);
                    alert.setHeaderText("Errore");
                    alert.setContentText(ex.getMessage());
                    alert.showAndWait();
                }
            }
        });
    }

}
