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
    private Button insertRequestBtn;

    public void init(final View view) {
        insertDonationBtn.addEventHandler(ActionEvent.ACTION, e -> setPageCommand(view, PagesConfig.DONATION_FORM));
        insertRequestBtn.addEventHandler(ActionEvent.ACTION, e -> setPageCommand(view, PagesConfig.REQUEST_FORM));
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
