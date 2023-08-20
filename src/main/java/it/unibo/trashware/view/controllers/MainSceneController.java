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

    public void init(final View view) {
        insertDonationBtn.addEventHandler(ActionEvent.ACTION, e -> {
            try {
                view.setPane(PagesConfig.DONATION_FORM);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
    }

    public Pane getSidePage() {
        return this.sidePage;
    }

}
