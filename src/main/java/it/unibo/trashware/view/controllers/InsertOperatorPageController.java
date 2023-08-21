package it.unibo.trashware.view.controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

import it.unibo.trashware.controller.api.WorkShiftsController;
import it.unibo.trashware.controller.impl.WorkShiftsControllerImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class InsertOperatorPageController {

    @FXML
    private Button insertBtn;

    @FXML
    private TextField operatorBirthdayField;

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
        insertBtn.addEventHandler(ActionEvent.ACTION, e -> {
            insertOperatorData();
        });
    }

    private void insertOperatorData() {
        final String[] parsedDate = this.operatorBirthdayField.getText().split("-");
        final int year = Integer.parseInt(parsedDate[0]);
        final int month = Integer.parseInt(parsedDate[1]);
        final int day = Integer.parseInt(parsedDate[2]);
        this.controller.addOperator(
            this.operatorFiscalCodeField.getText(), 
            this.operatorNameField.getText(), 
            this.operatorSurnameField.getText(), 
            this.operatorBirthplaceField.getText(), 
            LocalDate.of(year, month, day),
            this.operatorResidenceCityField.getText(), 
            this.operatorResidenceCAPField.getText(), 
            this.operatorResidenceProvinceField.getText(), 
            this.operatorResidenceStreetField.getText(),
            Integer.parseInt(this.operatorResidenceNumberField.getText()), 
            this.operatorTelephoneNumber1Field.getText(), 
            Optional.of(this.operatorTelephoneNumber2Field.getText()),
            Optional.of(this.operatorEmailField.getText())
        );
    }

}
