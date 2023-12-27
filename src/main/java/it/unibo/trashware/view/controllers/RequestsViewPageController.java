package it.unibo.trashware.view.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import it.unibo.trashware.commons.FieldTags;
import it.unibo.trashware.controller.api.OperationsController;
import it.unibo.trashware.controller.impl.OperationsControllerImpl;
import it.unibo.trashware.view.controllers.tableItems.RequestItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class RequestsViewPageController {

    @FXML
    private TableView<RequestItem> requestsTableView;

    @FXML
    private TableColumn<RequestItem, String> date;

    @FXML
    private TableColumn<RequestItem, String> deadline;

    @FXML
    private TableColumn<RequestItem, String> details;

    @FXML
    private TableColumn<RequestItem, String> donationID;

    @FXML
    private TableColumn<RequestItem, String> email;

    @FXML
    private TableColumn<RequestItem, String> fax;

    @FXML
    private TableColumn<RequestItem, String> motivation;

    @FXML
    private TableColumn<RequestItem, String> priority;

    @FXML
    private TableColumn<RequestItem, String> representativeName;

    @FXML
    private TableColumn<RequestItem, String> requestType;

    @FXML
    private TableColumn<RequestItem, String> societyName;

    @FXML
    private TableColumn<RequestItem, String> telephoneNumbers;

    private OperationsController controller;

    @FXML
    public void initialize() {
        try {
            this.controller = new OperationsControllerImpl();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.donationID.setCellValueFactory(new PropertyValueFactory<RequestItem, String>("donationID"));
        this.representativeName.setCellValueFactory(new PropertyValueFactory<RequestItem, String>("representativeName"));
        this.societyName.setCellValueFactory(new PropertyValueFactory<RequestItem, String>("societyName"));
        this.requestType.setCellValueFactory(new PropertyValueFactory<RequestItem, String>("requestType"));
        this.motivation.setCellValueFactory(new PropertyValueFactory<RequestItem, String>("motivation"));
        this.details.setCellValueFactory(new PropertyValueFactory<RequestItem, String>("details"));
        this.date.setCellValueFactory(new PropertyValueFactory<RequestItem, String>("date"));
        this.deadline.setCellValueFactory(new PropertyValueFactory<RequestItem, String>("deadline"));
        this.priority.setCellValueFactory(new PropertyValueFactory<RequestItem, String>("priority"));
        this.telephoneNumbers.setCellValueFactory(new PropertyValueFactory<RequestItem, String>("telephoneNumbers"));
        this.fax.setCellValueFactory(new PropertyValueFactory<RequestItem, String>("faxNumber"));
        this.email.setCellValueFactory(new PropertyValueFactory<RequestItem, String>("email"));
        fillTable();
    }

    private void fillTable() {
        final ObservableList<RequestItem> list = FXCollections.observableArrayList();
        List<Map<FieldTags, String>> result = this.controller.getDonationsList();
        for (var map : result) {
            // list.add();
        }
        this.requestsTableView.setItems(list);
    }

}