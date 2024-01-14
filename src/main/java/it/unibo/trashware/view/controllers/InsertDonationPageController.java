package it.unibo.trashware.view.controllers;

import java.io.IOException;
import java.util.Optional;

import it.unibo.trashware.controller.api.OperationsController;
import it.unibo.trashware.controller.impl.OperationsControllerImpl;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class InsertDonationPageController {

    @FXML
    private TextField donationIDField;

    @FXML
    private DatePicker effectuationDatePicker;

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
    private DatePicker repBirthdayDatePicker;

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
    private CheckBox societyCheckbox;

    @FXML
    private TextField repTitleField;

    @FXML
    private TextField societyFiscalCodeField;

    @FXML
    private TextField societyNameField;

    @FXML
    private TextField societyVATNumberField;

    @FXML
    private TextArea devicesList;

    @FXML
    private TextArea notes;

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
        insertBtn.setOnAction(e -> {
            try {
                insertRepresentativeData();
                if (this.societyCheckbox.isSelected()) {
                    insertSocietyData();
                    insertRepresentation();
                }
                insertDonation();
                final Alert alert = new Alert(AlertType.INFORMATION);
                alert.setHeaderText("Info");
                alert.setContentText("Donazione registrata con successo.");
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

    private void insertRepresentativeData() {
        this.controller.addRepresentative(
            this.repFiscalCodeField.getText(), 
            this.repNameField.getText(), 
            this.repSurnameField.getText(), 
            this.repBirthplaceField.getText(), 
            this.repBirthdayDatePicker.getValue(),
            this.repResidenceCityField.getText(), 
            this.repResidenceCAPField.getText(), 
            this.repResidenceProvinceField.getText(), 
            this.repResidenceStreetField.getText(),
            Integer.parseInt(this.repResidenceNumberField.getText()), 
            this.repTelephoneNumber1Field.getText(), 
            this.repTelephoneNumber2Field.getText().equals("")
                ? Optional.empty()
                : Optional.of(this.repTelephoneNumber2Field.getText()),
            this.repFaxField.getText().equals("")
                ? Optional.empty()
                : Optional.of(this.repFaxField.getText()),
            this.repEmailField.getText().equals("")
                ? Optional.empty()
                : Optional.of(this.repEmailField.getText())
        );
    }

    private void insertSocietyData() {
        this.controller.addSociety(
            this.societyVATNumberField.getText(), 
            this.societyFiscalCodeField.getText(), 
            this.societyNameField.getText(), 
            this.officeCityField.getText(), 
            this.officeCAPField.getText(), 
            this.officeProvinceField.getText(), 
            this.officeStreetField.getText(),
            Integer.parseInt(this.officeStreetNumberField.getText())
        );
    }

    private void insertRepresentation() {
        this.controller.addRepresentation(
            this.societyVATNumberField.getText(), 
            this.repFiscalCodeField.getText(), 
            this.repTitleField.getText()
        );
    }

    private void insertDonation() {
        this.controller.addDonation(
            this.donationIDField.getText(), 
            this.effectuationDatePicker.getValue(),
            this.devicesList.getText(),
            Optional.of(this.notes.getText()),
            this.repFiscalCodeField.getText()
        );
    }

}
