package it.unibo.trashware.view.controllers;

import it.unibo.trashware.view.View;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class MainSceneController {

    @FXML
    private AnchorPane sidePage;
    
    @FXML
    private Button insertDonationBtn;

    @FXML
    private Button showDonationsBtn;

    @FXML
    private Button insertRequestBtn;

    @FXML
    private Button showRequestsBtn;

    @FXML
    private Button insertDeviceBtn;

    @FXML
    private Button updateRequestStatusBtn;

    @FXML
    private Button showInventoryBtn;

    @FXML
    private Button linkDeviceToOperationBtn;

    public void init(final View view) {
        insertDonationBtn.setOnAction(e -> view.setPage(Pages.DONATION_FORM));
        showDonationsBtn.setOnAction(e -> view.setPage(Pages.DONATIONS_VIEW));
        insertRequestBtn.setOnAction(e -> view.setPage(Pages.REQUEST_FORM));
        showRequestsBtn.setOnAction(e -> view.setPage(Pages.REQUESTS_VIEW));
        insertDeviceBtn.setOnAction(e -> view.setPage(Pages.DEVICE_FORM));
        updateRequestStatusBtn.setOnAction(e -> view.setPage(Pages.UPDATE_REQUEST_STATUS_FORM));
        showInventoryBtn.setOnAction(e -> view.setPage(Pages.INVENTORY_VIEW));
        linkDeviceToOperationBtn.setOnAction(e -> view.setPage(Pages.ASSOCIATION_FORM));
    }

    public Pane getSidePage() {
        return this.sidePage;
    }

}
