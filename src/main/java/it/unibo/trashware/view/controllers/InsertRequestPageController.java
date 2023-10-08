package it.unibo.trashware.view.controllers;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
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

public class InsertRequestPageController {

    @FXML
    private TextField articleField1;

    @FXML
    private TextField articleField2;

    @FXML
    private TextField articleField3;

    @FXML
    private TextField articleField4;

    @FXML
    private TextField articleField5;

    @FXML
    private TextField articleNotesField1;

    @FXML
    private TextField articleNotesField2;

    @FXML
    private TextField articleNotesField3;

    @FXML
    private TextField articleNotesField4;

    @FXML
    private TextField articleNotesField5;

    @FXML
    private TextField articleQuantityField1;

    @FXML
    private TextField articleQuantityField2;

    @FXML
    private TextField articleQuantityField3;

    @FXML
    private TextField articleQuantityField4;

    @FXML
    private TextField articleQuantityField5;

    @FXML
    private DatePicker deadlineDataPicker;

    @FXML
    private DatePicker effectuationDatePicker;

    @FXML
    private Button insertBtn;

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
    private TextField priorityLevelField;

    @FXML
    private TextArea reasonField;

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
    private TextField repTitleField;

    @FXML
    private TextField requestIDField;

    @FXML
    private TextField requestTypeField;

    @FXML
    private CheckBox societyCheckbox;

    @FXML
    private TextField societyFiscalCodeField;

    @FXML
    private TextField societyNameField;

    @FXML
    private TextField societyVATNumberField;

    private OperationsController controller; 

    @FXML
    public void initialize() {
        try {
            this.controller = new OperationsControllerImpl();
        } catch (IOException e) {
            e.printStackTrace();
        }
        insertBtn.setOnAction(e -> {
            insertRepresentativeData();
            if (this.societyCheckbox.isSelected()) {
                insertSocietyData();
                insertRepresentation();
            }
            insertRequest();
            insertObjectDescriptions();
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

    private void insertRequest() {
        this.controller.addRequest(
            this.requestIDField.getText(),
            this.requestTypeField.getText(),
            this.reasonField.getText(),
            this.effectuationDatePicker.getValue(),
            this.deadlineDataPicker.getValue(),
            Integer.parseInt(this.priorityLevelField.getText()),
            this.notesField.getText().equals("")
                ? Optional.empty()
                : Optional.of(this.notesField.getText()),
            this.repFiscalCodeField.getText()
        );
    }

    private void insertObjectDescriptions() {
        // Get articles descriptions
        final List<String> articles = new LinkedList<>();
        articles.add(this.articleField1.getText());
        articles.add(this.articleField2.getText());
        articles.add(this.articleField3.getText());
        articles.add(this.articleField4.getText());
        articles.add(this.articleField5.getText());
        // Get articles quantities
        final List<String> articlesQuantity = new LinkedList<>();
        articlesQuantity.add(this.articleQuantityField1.getText());
        articlesQuantity.add(this.articleQuantityField2.getText());
        articlesQuantity.add(this.articleQuantityField3.getText());
        articlesQuantity.add(this.articleQuantityField4.getText());
        articlesQuantity.add(this.articleQuantityField5.getText());
        // Get articles notes
        final List<Optional<String>> articlesNotes = new LinkedList<>();
        articlesNotes.add(
            this.articleNotesField1.getText().equals("")
            ? Optional.empty()
            : Optional.of(this.articleNotesField1.getText())
        );
        articlesNotes.add(
            this.articleNotesField2.getText().equals("")
            ? Optional.empty()
            : Optional.of(this.articleNotesField2.getText())
        );
        articlesNotes.add(
            this.articleNotesField3.getText().equals("")
            ? Optional.empty()
            : Optional.of(this.articleNotesField3.getText())
        );
        articlesNotes.add(
            this.articleNotesField4.getText().equals("")
            ? Optional.empty()
            : Optional.of(this.articleNotesField4.getText())
        );
        articlesNotes.add(
            this.articleNotesField5.getText().equals("")
            ? Optional.empty()
            : Optional.of(this.articleNotesField5.getText())
        );
        // Check list entries and, if filled and valid, insert them
        for (int i = 0; i < articles.size(); i++) {
            if (articles.get(i).equals("") && articlesQuantity.get(i).equals("")) {
                // Empty line
                continue;
            }
            if (articles.get(i).equals("") || articlesQuantity.get(i).equals("")) {
                // Not all the required fields for the article have been filled
                final Alert errorAlert = new Alert(AlertType.ERROR);
                errorAlert.setHeaderText("Input non valido");
                errorAlert.setContentText("Non tutti i campi obbligatori per l'articolo " + i + " sono stati riempiti.");
                errorAlert.showAndWait();
            }
            this.controller.addObjectDescription(
                this.requestIDField.getText(),
                i + 1, 
                articles.get(i), 
                Integer.parseInt(articlesQuantity.get(i)), 
                articlesNotes.get(i)
            );
        }
    }

}