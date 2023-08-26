package it.unibo.trashware.view.controllers;

import java.io.IOException;

import it.unibo.trashware.controller.api.OperationsController;
import it.unibo.trashware.controller.impl.OperationsControllerImpl;
import it.unibo.trashware.view.controllers.tableItems.DonationItem;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class DonationsViewPageController {

    @FXML
    private TableView<DonationItem> donationsTableView;

    @FXML
    private TableColumn<?, ?> dateColumn;

    @FXML
    private TableColumn<?, ?> donationIDColumn;

    @FXML
    private TableColumn<?, ?> emailColumn;

    @FXML
    private TableColumn<?, ?> faxColumn;

    @FXML
    private TableColumn<?, ?> representativeNameColumn;

    @FXML
    private TableColumn<?, ?> societyNameColumn;

    @FXML
    private TableColumn<?, ?> telephoneNumbersColumn;

    private OperationsController controller;

    @FXML
    public void initialize() {
        try {
            this.controller = new OperationsControllerImpl();
        } catch (IOException e) {
            e.printStackTrace();
        }
        fillTable();
    }

    private void fillTable() {
        // TODO
    }

}
