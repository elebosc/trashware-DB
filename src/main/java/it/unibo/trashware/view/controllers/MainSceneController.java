package it.unibo.trashware.view.controllers;

import java.io.IOException;

import it.unibo.trashware.view.PagesConfig;
import it.unibo.trashware.view.View;
import javafx.event.ActionEvent;
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
    private Button insertOperatorBtn;

    @FXML
    private Button insertWorkShiftBtn;

    public void init(final View view) {
        insertDonationBtn.addEventHandler(ActionEvent.ACTION, e -> setPageCommand(view, PagesConfig.DONATION_FORM));
        showDonationsBtn.addEventHandler(ActionEvent.ACTION, e -> setPageCommand(view, PagesConfig.DONATIONS_VIEW));
        insertRequestBtn.addEventHandler(ActionEvent.ACTION, e -> setPageCommand(view, PagesConfig.REQUEST_FORM));
        insertOperatorBtn.addEventHandler(ActionEvent.ACTION, e -> setPageCommand(view, PagesConfig.OPERATOR_FORM));
        insertWorkShiftBtn.addEventHandler(ActionEvent.ACTION, e -> setPageCommand(view, PagesConfig.WORKSHIFT_FORM));
    }

    private void setPageCommand(final View view, final PagesConfig page) {
        try {
            view.setPage(page);
        } catch (final IOException ex) {
            ex.printStackTrace();
        }
    }

    public Pane getSidePage() {
        return this.sidePage;
    }

}
