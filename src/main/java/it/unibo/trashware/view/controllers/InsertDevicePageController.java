package it.unibo.trashware.view.controllers;

import java.io.IOException;

import it.unibo.trashware.view.controllers.subpages.DeviceFormSubpages;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;

public class InsertDevicePageController {

    @FXML
    private MenuItem CPUItem;

    @FXML
    private MenuItem LaptopItem;

    @FXML
    private MenuItem RAMItem;

    @FXML
    private MenuItem chassisItem;

    @FXML
    private Menu componentTypeMenu;

    @FXML
    private MenuItem desktopPCItem;

    @FXML
    private MenuButton deviceCategoryMenu;

    @FXML
    private AnchorPane formPane;

    @FXML
    private MenuItem massMemoryItem;

    @FXML
    private MenuItem monitorItem;

    @FXML
    private MenuItem otherComponentItem;

    @FXML
    private MenuItem otherPeripheralItem;

    @FXML
    private Menu pcTypeMenu;

    @FXML
    private Menu peripheralTypeMenu;

    @FXML
    public void initialize() {
        this.monitorItem.setOnAction(e -> this.setSubpage(DeviceFormSubpages.MONITOR_FORM));
        this.otherPeripheralItem.setOnAction(e -> this.setSubpage(DeviceFormSubpages.OTHER_PERIPHERAL_FORM));
    }

    private void setSubpage(final DeviceFormSubpages page) {
        final FXMLLoader loader = new FXMLLoader();
        try {
            final Node node = loader.load(ClassLoader.getSystemResourceAsStream(page.getFXMLFilePath()));
            this.formPane.getChildren().add(node);
        } catch (final IOException ex) {
            ex.printStackTrace();
        }
    }

}
