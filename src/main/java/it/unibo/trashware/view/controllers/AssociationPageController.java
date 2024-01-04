package it.unibo.trashware.view.controllers;

import java.io.IOException;
import java.util.function.BiConsumer;

import it.unibo.trashware.controller.api.OperationsController;
import it.unibo.trashware.controller.impl.OperationsControllerImpl;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class AssociationPageController {

    private static final String PC = "PC";
    private static final String PERIPHERAL = "Periferica";
    private static final String COMPONENT = "Componente";

    @FXML
    private TextField deviceIDField;

    @FXML
    private ComboBox<String> deviceTypeComboBox;

    @FXML
    private Button insertBtn;

    @FXML
    private TextField operationIDField;

    private OperationsController controller;

     @FXML
    public void initialize() {
        try {
            this.controller = new OperationsControllerImpl();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.deviceTypeComboBox.setItems(FXCollections.observableArrayList(PC, PERIPHERAL, COMPONENT));
        this.insertBtn.setOnAction(e -> {
            try {
                insertAssociationData();
                final Alert alert = new Alert(AlertType.INFORMATION);
                alert.setHeaderText("Info");
                alert.setContentText("Associazione registrata con successo.");
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

    private void insertAssociationData() {
        BiConsumer<String, String> association = switch (this.deviceTypeComboBox.getValue()) {
            case PC -> this.controller::associatePCToOperation;
            case PERIPHERAL -> this.controller::associatePeripheralToOperation;
            case COMPONENT -> this.controller::associateComponentToOperation;
            default -> throw new IllegalArgumentException("Invalid device type");
        };
        association.accept(this.deviceIDField.getText(), this.operationIDField.getText());
    }

}

