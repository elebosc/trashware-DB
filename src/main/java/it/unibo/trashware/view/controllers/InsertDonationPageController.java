package it.unibo.trashware.view.controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

import it.unibo.trashware.controller.api.OperationsController;
import it.unibo.trashware.controller.impl.OperationsControllerImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class InsertDonationPageController {

    @FXML
    private TextField effectuationDateField;

    @FXML
    private TextField notesField;

    @FXML
    private TextField officeCAPField;

    @FXML
    private TextField officeCityField;

    @FXML
    private TextField officeProvinceField;

    @FXML
    private TextField officeStreetField;

    @FXML
    private TextField officeStreetNumberField;

    @FXML
    private TextField repBirthdayField;

    @FXML
    private TextField repBirthplaceField;

    @FXML
    private TextField repEmailField;

    @FXML
    private TextField repFaxField;

    @FXML
    private TextField repFiscalCodeField;

    @FXML
    private TextField repNameField;

    @FXML
    private TextField repResidenceCAPField;

    @FXML
    private TextField repResidenceCityField;

    @FXML
    private TextField repResidenceNumberField;

    @FXML
    private TextField repResidenceProvinceField;

    @FXML
    private TextField repResidenceStreetField;

    @FXML
    private TextField repSurnameField;

    @FXML
    private TextField repTelephoneNumber1Field;

    @FXML
    private TextField repTelephoneNumber2Field;

    @FXML
    private TextField repTitleField;

    @FXML
    private TextField societyFiscalCodeField;

    @FXML
    private TextField societyNameField;

    @FXML
    private TextField societyVATNumberField;

    @FXML
    private Button insertBtn;

    private OperationsController controller; 

    @FXML
    public void initialize() {
        try {
            this.controller = new OperationsControllerImpl();
        } catch (IOException e) {
            e.printStackTrace();
        }
        insertBtn.addEventHandler(ActionEvent.ACTION, e -> {
            insertRepresentativeData();
            // this.controller.addSociety();
            // this.controller.addRepresentation();
            // this.controller.addObjectDescription();
        });
    }

    private void insertRepresentativeData() {
        final String[] parsedDate = this.repBirthdayField.getText().split("-");
        final int year = Integer.parseInt(parsedDate[0]);
        final int month = Integer.parseInt(parsedDate[1]);
        final int day = Integer.parseInt(parsedDate[2]);
        this.controller.addRepresentative(
            this.repFiscalCodeField.getText(), 
            this.repNameField.getText(), 
            this.repSurnameField.getText(), 
            this.repBirthplaceField.getText(), 
            LocalDate.of(year, month, day),
            this.repResidenceCityField.getText(), 
            this.repResidenceCAPField.getText(), 
            this.repResidenceProvinceField.getText(), 
            this.repResidenceStreetField.getText(),
            Integer.parseInt(this.repResidenceNumberField.getText()), 
            this.repTelephoneNumber1Field.getText(), 
            Optional.of(this.repTelephoneNumber2Field.getText()), 
            Optional.of(this.repFaxField.getText()),
            Optional.of(this.repEmailField.getText())
        );
    }

}
