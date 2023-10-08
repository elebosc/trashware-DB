package it.unibo.trashware.view.controllers;

import java.io.IOException;
import java.util.Optional;

import it.unibo.trashware.controller.api.WorkShiftsController;
import it.unibo.trashware.controller.impl.WorkShiftsControllerImpl;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class InsertOperatorPageController {

    @FXML
    private Button insertBtn;

    @FXML
    private DatePicker operatorBirthdayDataPicker;

    @FXML
    private TextField operatorBirthplaceField;

    @FXML
    private TextField operatorEmailField;

    @FXML
    private TextField operatorFiscalCodeField;

    @FXML
    private TextField operatorNameField;

    @FXML
    private TextField operatorResidenceCAPField;

    @FXML
    private TextField operatorResidenceCityField;

    @FXML
    private TextField operatorResidenceNumberField;

    @FXML
    private TextField operatorResidenceProvinceField;

    @FXML
    private TextField operatorResidenceStreetField;

    @FXML
    private TextField operatorSurnameField;

    @FXML
    private TextField operatorTelephoneNumber1Field;

    @FXML
    private TextField operatorTelephoneNumber2Field;

    private WorkShiftsController controller;

    @FXML
    public void initialize() {
        try {
            this.controller = new WorkShiftsControllerImpl();
        } catch (IOException e) {
            e.printStackTrace();
        }
        insertBtn.setOnAction(e -> insertOperatorData());
    }

    private void insertOperatorData() {
        this.controller.addOperator(
            this.operatorFiscalCodeField.getText(), 
            this.operatorNameField.getText(), 
            this.operatorSurnameField.getText(), 
            this.operatorBirthplaceField.getText(), 
            this.operatorBirthdayDataPicker.getValue(),
            this.operatorResidenceCityField.getText(), 
            this.operatorResidenceCAPField.getText(), 
            this.operatorResidenceProvinceField.getText(), 
            this.operatorResidenceStreetField.getText(),
            Integer.parseInt(this.operatorResidenceNumberField.getText()), 
            this.operatorTelephoneNumber1Field.getText(), 
            this.operatorTelephoneNumber2Field.getText().equals("")
                ? Optional.empty()
                : Optional.of(this.operatorTelephoneNumber2Field.getText()),
            this.operatorEmailField.getText().equals("")
                ? Optional.empty()
                : Optional.of(this.operatorEmailField.getText())
        );
    }

}
