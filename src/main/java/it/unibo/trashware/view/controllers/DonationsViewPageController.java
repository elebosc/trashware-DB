package it.unibo.trashware.view.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import it.unibo.trashware.commons.FieldTags;
import it.unibo.trashware.controller.api.OperationsController;
import it.unibo.trashware.controller.impl.OperationsControllerImpl;
import it.unibo.trashware.view.controllers.tableItems.DonationItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class DonationsViewPageController {

    @FXML
    private TableView<DonationItem> donationsTableView;

    @FXML
    private TableColumn<DonationItem, String> dateColumn;

    @FXML
    private TableColumn<DonationItem, String> donationIDColumn;

    @FXML
    private TableColumn<DonationItem, String> emailColumn;

    @FXML
    private TableColumn<DonationItem, String> faxColumn;

    @FXML
    private TableColumn<DonationItem, String> representativeNameColumn;

    @FXML
    private TableColumn<DonationItem, String> societyNameColumn;

    @FXML
    private TableColumn<DonationItem, String> telephoneNumbersColumn;

    private OperationsController controller;

    @FXML
    public void initialize() {
        try {
            this.controller = new OperationsControllerImpl();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.donationIDColumn.setCellValueFactory(new PropertyValueFactory<DonationItem, String>("donationID"));
        this.representativeNameColumn.setCellValueFactory(new PropertyValueFactory<DonationItem, String>("representativeName"));
        this.societyNameColumn.setCellValueFactory(new PropertyValueFactory<DonationItem, String>("societyName"));
        this.dateColumn.setCellValueFactory(new PropertyValueFactory<DonationItem, String>("date"));
        this.telephoneNumbersColumn.setCellValueFactory(new PropertyValueFactory<DonationItem, String>("telephoneNumbers"));
        this.faxColumn.setCellValueFactory(new PropertyValueFactory<DonationItem, String>("faxNumber"));
        this.emailColumn.setCellValueFactory(new PropertyValueFactory<DonationItem, String>("email"));
        fillTable();
    }

    private void fillTable() {
        final ObservableList<DonationItem> list = FXCollections.observableArrayList();
        List<Map<FieldTags, String>> result = this.controller.getDonationsList();
        for (var map : result) {
            list.add(new DonationItem(
                map.get(FieldTags.OPERATION_ID), 
                map.get(FieldTags.REPRESENTATIVE),
                map.get(FieldTags.SOCIETY), 
                map.get(FieldTags.EFFECTUATION_DATE), 
                map.get(FieldTags.PHONE_CONTACTS), 
                map.get(FieldTags.FAX),
                map.get(FieldTags.EMAIL))
            );
        }
        this.donationsTableView.setItems(list);
    }

}
