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
    private Button showRequestsToCompleteBtn;

    @FXML
    private Button showRequestsToDeliverBtn;

    @FXML
    private Button showDeliveredRequestsBtn;

    @FXML
    private Button insertDeviceBtn;

    @FXML
    private Button insertOperatorBtn;

    @FXML
    private Button insertWorkShiftBtn;

    public void init(final View view) {
        insertDonationBtn.setOnAction(e -> view.setPage(Pages.DONATION_FORM));
        showDonationsBtn.setOnAction(e -> view.setPage(Pages.DONATIONS_VIEW));
        insertRequestBtn.setOnAction(e -> view.setPage(Pages.REQUEST_FORM));
        showRequestsToCompleteBtn.setOnAction(e -> view.setPage(Pages.REQUESTS_VIEW));
        showRequestsToDeliverBtn.setOnAction(e -> view.setPage(Pages.REQUESTS_VIEW));
        showDeliveredRequestsBtn.setOnAction(e -> view.setPage(Pages.REQUESTS_VIEW));
        insertDeviceBtn.setOnAction(e -> view.setPage(Pages.DEVICE_FORM));
        insertOperatorBtn.setOnAction(e -> view.setPage(Pages.OPERATOR_FORM));
        insertWorkShiftBtn.setOnAction(e -> view.setPage(Pages.WORKSHIFT_FORM));
    }

    public Pane getSidePage() {
        return this.sidePage;
    }

}
