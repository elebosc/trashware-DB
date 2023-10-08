package it.unibo.trashware.view.controllers.subpages;

import java.io.IOException;
import java.util.Optional;

import it.unibo.trashware.controller.api.InventoryController;
import it.unibo.trashware.controller.impl.InventoryControllerImpl;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class OtherPeripheralFormController {

    @FXML
    private TextField IDField;

    @FXML
    private TextField brandField;

    @FXML
    private TextField connectivityField;

    @FXML
    private Button insertBtn;

    @FXML
    private TextField modelField;

    @FXML
    private TextArea notesField;

    @FXML
    private TextField typeField;

    private InventoryController controller;

    @FXML
    public void initialize() {
        try {
            this.controller = new InventoryControllerImpl();
        } catch (IOException e) {
            e.printStackTrace();
        }
        insertBtn.setOnAction(e -> insertPeripheralData());
    }

    private void insertPeripheralData() {
        this.controller.addPeripheral(
            this.IDField.getText(),
            this.typeField.getText(), 
            this.brandField.getText(), 
            this.modelField.getText(), 
            this.connectivityField.getText(), 
            this.notesField.getText().equals("")
                ? Optional.empty()
                : Optional.of(this.notesField.getText())
        );
    }

}
