package it.unibo.trashware.view.controllers;

import java.io.IOException;

import it.unibo.trashware.controller.api.InventoryController;
import it.unibo.trashware.controller.impl.InventoryControllerImpl;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class AssociateDeviceToPCPageController {

    private static final String DESKTOP = "Desktop";
    private static final String LAPTOP = "Portatile";

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
    }

}
