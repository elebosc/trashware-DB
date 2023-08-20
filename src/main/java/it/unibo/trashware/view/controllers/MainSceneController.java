package it.unibo.trashware.view.controllers;

import java.io.IOException;

import it.unibo.trashware.controller.Controller;
import it.unibo.trashware.view.PanesConfig;
import it.unibo.trashware.view.View;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainSceneController implements SceneController {

    private View view;
    private Controller controller;
    
    @FXML
    private Button insertDonationBtn;

    @Override
    public void init(final View view, final Controller controller) {
        this.view = view;
        this.controller = controller;
        this.insertDonationBtn.addEventHandler(ActionEvent.ACTION, e -> {
            try {
                this.view.setPane(PanesConfig.DONATION_FORM);
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        });
    }

}
