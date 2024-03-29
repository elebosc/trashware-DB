package it.unibo.trashware.view.controllers;

import java.io.IOException;

import it.unibo.trashware.view.controllers.subpages.InventoryViewSubpages;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;

public class InventoryViewPageController {

    @FXML
    private MenuItem CPUItem;

    @FXML
    private MenuItem laptopItem;

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

    private Node currentSubpage;

    @FXML
    public void initialize() {
        this.desktopPCItem.setOnAction(e -> this.setSubpage(InventoryViewSubpages.DESKTOPS_VIEW));
        this.laptopItem.setOnAction(e -> this.setSubpage(InventoryViewSubpages.LAPTOPS_VIEW));
        this.monitorItem.setOnAction(e -> this.setSubpage(InventoryViewSubpages.MONITORS_VIEW));
        this.otherPeripheralItem.setOnAction(e -> this.setSubpage(InventoryViewSubpages.OTHER_PERIPHERALS_VIEW));
        this.CPUItem.setOnAction(e -> this.setSubpage(InventoryViewSubpages.CPUS_VIEW));
        this.RAMItem.setOnAction(e -> this.setSubpage(InventoryViewSubpages.RAM_MODULES_VIEW));
        this.massMemoryItem.setOnAction(e -> this.setSubpage(InventoryViewSubpages.STORAGE_DEVICES_VIEW));
        this.chassisItem.setOnAction(e -> this.setSubpage(InventoryViewSubpages.CHASSIS_VIEW));
        this.otherComponentItem.setOnAction(e -> this.setSubpage(InventoryViewSubpages.OTHER_COMPONENTS_VIEW));
    }

    private void setSubpage(final InventoryViewSubpages page) {
        try {
            final Node node = FXMLLoader.load(ClassLoader.getSystemResource(page.getFXMLFilePath()));
            if (this.currentSubpage != null) {
                this.formPane.getChildren().remove(this.currentSubpage);
            }
            this.formPane.getChildren().add(node);
            this.currentSubpage = node;
        } catch (final IOException ex) {
            ex.printStackTrace();
            final Alert alert = new Alert(AlertType.ERROR);
            alert.setHeaderText("Errore: impossibile aprire la sottopagina.");
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }
    }

}