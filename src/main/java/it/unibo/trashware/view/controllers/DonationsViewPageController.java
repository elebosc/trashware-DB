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
    private TableColumn<DonationItem, String> date;

    @FXML
    private TableColumn<DonationItem, String> donationID;

    @FXML
    private TableColumn<DonationItem, String> email;

    @FXML
    private TableColumn<DonationItem, String> fax;

    @FXML
    private TableColumn<DonationItem, String> numComponents;

    @FXML
    private TableColumn<DonationItem, String> numDesktops;

    @FXML
    private TableColumn<DonationItem, String> numKeyboards;

    @FXML
    private TableColumn<DonationItem, String> numLaptops;

    @FXML
    private TableColumn<DonationItem, String> numMonitors;

    @FXML
    private TableColumn<DonationItem, String> numMouse;

    @FXML
    private TableColumn<DonationItem, String> numOtherPeripherals;

    @FXML
    private TableColumn<DonationItem, String> representativeName;

    @FXML
    private TableColumn<DonationItem, String> societyName;

    @FXML
    private TableColumn<DonationItem, String> telephoneNumbers;

    private OperationsController controller;

    @FXML
    public void initialize() {
        try {
            this.controller = new OperationsControllerImpl();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.donationID.setCellValueFactory(new PropertyValueFactory<DonationItem, String>("donationID"));
        this.representativeName.setCellValueFactory(new PropertyValueFactory<DonationItem, String>("representativeName"));
        this.societyName.setCellValueFactory(new PropertyValueFactory<DonationItem, String>("societyName"));
        this.numDesktops.setCellValueFactory(new PropertyValueFactory<DonationItem, String>("numDesktops"));
        this.numLaptops.setCellValueFactory(new PropertyValueFactory<DonationItem, String>("numLaptops"));
        this.numMonitors.setCellValueFactory(new PropertyValueFactory<DonationItem, String>("numMonitors"));
        this.numKeyboards.setCellValueFactory(new PropertyValueFactory<DonationItem, String>("numKeyboards"));
        this.numMouse.setCellValueFactory(new PropertyValueFactory<DonationItem, String>("numMouse"));
        this.numOtherPeripherals.setCellValueFactory(new PropertyValueFactory<DonationItem, String>("numOtherPeripherals"));
        this.numComponents.setCellValueFactory(new PropertyValueFactory<DonationItem, String>("numComponents"));
        this.date.setCellValueFactory(new PropertyValueFactory<DonationItem, String>("date"));
        this.telephoneNumbers.setCellValueFactory(new PropertyValueFactory<DonationItem, String>("telephoneNumbers"));
        this.fax.setCellValueFactory(new PropertyValueFactory<DonationItem, String>("faxNumber"));
        this.email.setCellValueFactory(new PropertyValueFactory<DonationItem, String>("email"));
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
                map.get(FieldTags.NUM_DESKTOPS),
                map.get(FieldTags.NUM_LAPTOPS),
                map.get(FieldTags.NUM_MONITORS),
                map.get(FieldTags.NUM_KEYBOARDS),
                map.get(FieldTags.NUM_MOUSE),
                map.get(FieldTags.NUM_OTHER_PERIPHERALS),
                map.get(FieldTags.NUM_COMPONENTS),
                map.get(FieldTags.EFFECTUATION_DATE), 
                map.get(FieldTags.PHONE_CONTACTS), 
                map.get(FieldTags.FAX),
                map.get(FieldTags.EMAIL))
            );
        }
        this.donationsTableView.setItems(list);
    }

}
