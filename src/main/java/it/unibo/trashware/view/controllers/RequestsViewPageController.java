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
import javafx.scene.control.Menu;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class RequestsViewPageController {

    private static final String IN_PROGRESS = "In lavorazione";
    private static final String COMPLETED = "Pronto";
    private static final String DELIVERED = "Evaso";

    @FXML
    private MenuButton requestStatusMenuBtn;

    @FXML
    private Menu requestStatusMenu;

    @FXML
    private MenuItem inProgress;
    
    @FXML
    private MenuItem completed;

    @FXML
    private MenuItem delivered;

    @FXML
    private TableView<RequestItem> requestsTableView;

    @FXML
    private TableColumn<RequestItem, String> date;

    @FXML
    private TableColumn<RequestItem, String> deadline;

    @FXML
    private TableColumn<RequestItem, String> devicesList;

    @FXML
    private TableColumn<RequestItem, String> requestID;

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

    @FXML
    private TableColumn<RequestItem, String> notes;

    private OperationsController controller;

    @FXML
    public void initialize() {
        try {
            this.controller = new OperationsControllerImpl();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.requestID.setCellValueFactory(new PropertyValueFactory<RequestItem, String>("requestID"));
        this.representativeName.setCellValueFactory(new PropertyValueFactory<RequestItem, String>("representativeName"));
        this.societyName.setCellValueFactory(new PropertyValueFactory<RequestItem, String>("societyName"));
        this.requestType.setCellValueFactory(new PropertyValueFactory<RequestItem, String>("requestType"));
        this.motivation.setCellValueFactory(new PropertyValueFactory<RequestItem, String>("motivation"));
        this.devicesList.setCellValueFactory(new PropertyValueFactory<RequestItem, String>("devicesList"));
        this.date.setCellValueFactory(new PropertyValueFactory<RequestItem, String>("date"));
        this.deadline.setCellValueFactory(new PropertyValueFactory<RequestItem, String>("deadline"));
        this.priority.setCellValueFactory(new PropertyValueFactory<RequestItem, String>("priority"));
        this.telephoneNumbers.setCellValueFactory(new PropertyValueFactory<RequestItem, String>("telephoneNumbers"));
        this.fax.setCellValueFactory(new PropertyValueFactory<RequestItem, String>("faxNumber"));
        this.email.setCellValueFactory(new PropertyValueFactory<RequestItem, String>("email"));
        this.notes.setCellValueFactory(new PropertyValueFactory<RequestItem, String>("notes"));
        
        this.inProgress.setOnAction(e -> fillTable(IN_PROGRESS));
        this.completed.setOnAction(e -> fillTable(COMPLETED));
        this.delivered.setOnAction(e -> fillTable(DELIVERED));
    }

    private void fillTable(final String requestStatus) {
        final ObservableList<RequestItem> list = FXCollections.observableArrayList();
        List<Map<FieldTags, String>> result = this.controller.getRequestsList(requestStatus);
        for (var map : result) {
            list.add(new RequestItem(
                map.get(FieldTags.OPERATION_ID),
                map.get(FieldTags.REPRESENTATIVE),
                map.get(FieldTags.SOCIETY),
                map.get(FieldTags.REQUEST_TYPE),
                map.get(FieldTags.REASON),
                map.get(FieldTags.DEVICES_LIST),
                map.get(FieldTags.EFFECTUATION_DATE),
                map.get(FieldTags.DEADLINE),
                map.get(FieldTags.PRIORITY),
                map.get(FieldTags.PHONE_CONTACTS),
                map.get(FieldTags.FAX),
                map.get(FieldTags.EMAIL),
                map.get(FieldTags.NOTES)
            ));
        }
        this.requestsTableView.setItems(list);
    }

}