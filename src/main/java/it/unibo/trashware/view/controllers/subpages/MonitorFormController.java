package it.unibo.trashware.view.controllers.subpages;

import java.io.IOException;
import java.util.Optional;

import it.unibo.trashware.controller.api.InventoryController;
import it.unibo.trashware.controller.impl.InventoryControllerImpl;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class MonitorFormController {

    @FXML
    private CheckBox DVICheckbox;

    @FXML
    private TextField IDField;

    @FXML
    private CheckBox VGACheckbox;

    @FXML
    private TextField aspectRatioField;

    @FXML
    private TextField brandField;

    @FXML
    private TextField connectivityField;

    @FXML
    private CheckBox embeddedAudioCheckbox;

    @FXML
    private Button insertBtn;

    @FXML
    private TextField modelField;

    @FXML
    private TextField monitorTypeField;

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
        insertBtn.setOnAction(e -> insertMonitorData());
    }

    private void insertMonitorData() {
        this.controller.addMonitor(
            this.IDField.getText(), 
            this.brandField.getText(), 
            this.modelField.getText(), 
            this.connectivityField.getText(), 
            this.notesField.getText().equals("")
                ? Optional.empty()
                : Optional.of(this.notesField.getText()), 
            this.monitorTypeField.getText(), 
            Integer.parseInt(this.sizeField.getText()), 
            this.aspectRatioField.getText(),
            this.VGACheckbox.isSelected(), 
            this.DVICheckbox.isSelected(), 
            this.embeddedAudioCheckbox.isSelected()
        );
    }

}
