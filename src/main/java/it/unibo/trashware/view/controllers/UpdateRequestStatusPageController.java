package it.unibo.trashware.view.controllers;

import java.io.IOException;

import it.unibo.trashware.controller.api.OperationsController;
import it.unibo.trashware.controller.impl.OperationsControllerImpl;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class UpdateRequestStatusPageController {

    private static final String COMPLETION = "Completamento";
    private static final String DELIVERY = "Consegna";

    @FXML
    private DatePicker date;

    @FXML
    private Button insertBtn;

    @FXML
    private TextField requestID;

    @FXML
    private ComboBox<String> updateComboBox;

    private OperationsController controller;

    @FXML
    public void initialize() {
        try {
            this.controller = new OperationsControllerImpl();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.updateComboBox.setItems(FXCollections.observableArrayList(COMPLETION, DELIVERY));
        this.insertBtn.setOnAction(e -> insertUpdate());
    }

    private void insertUpdate() {
        if (this.updateComboBox.getValue() == null) {
            final Alert alert = new Alert(AlertType.WARNING);
            alert.setHeaderText("Input non valido");
            alert.setContentText("Selezionare il tipo di aggiornamento.");
            alert.showAndWait();
        } else {
            switch (this.updateComboBox.getValue()) {
                case COMPLETION:
                    try {
                        this.controller.registerRequestCompletion(this.requestID.getText(), this.date.getValue());
                        final Alert alert = new Alert(AlertType.INFORMATION);
                        alert.setHeaderText("Info");
                        alert.setContentText("Completamento registrato con successo.");
                        alert.showAndWait();
                    } catch (IllegalArgumentException ex) {
                        final Alert alert = new Alert(AlertType.ERROR);
                        alert.setHeaderText("Input non valido");
                        alert.setContentText(ex.getMessage());
                        alert.showAndWait();
                    }
                    break;
                case DELIVERY:
                    try {
                        this.controller.registerDevicesDelivery(this.requestID.getText(), this.date.getValue());
                        final Alert alert = new Alert(AlertType.INFORMATION);
                        alert.setHeaderText("Info");
                        alert.setContentText("Consegna registrata con successo.");
                        alert.showAndWait();
                    } catch (IllegalArgumentException ex) {
                        final Alert alert = new Alert(AlertType.ERROR);
                        alert.setHeaderText("Input non valido");
                        alert.setContentText(ex.getMessage());
                        alert.showAndWait();
                    }
                    break;
                default:
                    final Alert alert = new Alert(AlertType.WARNING);
                    alert.setHeaderText("Input non valido");
                    alert.setContentText("Tipo di aggiornamento non valido.");
                    alert.showAndWait();
                    break;
            }
        }
        
    }

}
