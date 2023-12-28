package it.unibo.trashware.view.controllers.subpages;

import java.io.IOException;

import it.unibo.trashware.controller.api.InventoryController;
import it.unibo.trashware.controller.impl.InventoryControllerImpl;
import it.unibo.trashware.view.controllers.tableItems.MonitorItem;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class MonitorsViewController {

    @FXML
    private TableView<MonitorItem> monitorsTableView;

    @FXML
    private TableColumn<MonitorItem, String> notes;

    @FXML
    private TableColumn<MonitorItem, String> assignedToPC;

    @FXML
    private TableColumn<MonitorItem, String> brand;

    @FXML
    private TableColumn<MonitorItem, String> connectivity;

    @FXML
    private TableColumn<MonitorItem, String> hasEmbeddedAudio;

    @FXML
    private TableColumn<MonitorItem, String> id;

    @FXML
    private TableColumn<MonitorItem, String> isDVISupported;

    @FXML
    private TableColumn<MonitorItem, String> isVGASupported;

    @FXML
    private TableColumn<MonitorItem, String> model;

    @FXML
    private TableColumn<MonitorItem, String> monitorType;

    @FXML
    private TableColumn<MonitorItem, String> ratio;

    @FXML
    private TableColumn<MonitorItem, String> size;

    private InventoryController controller;

    @FXML
    public void initialize() {
        try {
            this.controller = new InventoryControllerImpl();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        this.id.setCellValueFactory(new PropertyValueFactory<MonitorItem, String>("id"));
        this.brand.setCellValueFactory(new PropertyValueFactory<MonitorItem, String>("brand"));
        this.model.setCellValueFactory(new PropertyValueFactory<MonitorItem, String>("model"));
        this.connectivity.setCellValueFactory(new PropertyValueFactory<MonitorItem, String>("connectivity"));
        this.monitorType.setCellValueFactory(new PropertyValueFactory<MonitorItem, String>("monitorType"));
        this.size.setCellValueFactory(new PropertyValueFactory<MonitorItem, String>("size"));
        this.ratio.setCellValueFactory(new PropertyValueFactory<MonitorItem, String>("ratio"));
        this.isVGASupported.setCellValueFactory(new PropertyValueFactory<MonitorItem, String>("isVGASupported"));
        this.isDVISupported.setCellValueFactory(new PropertyValueFactory<MonitorItem, String>("isDVISupported"));
        this.hasEmbeddedAudio.setCellValueFactory(new PropertyValueFactory<MonitorItem, String>("hasEmbeddedAudio"));
        this.notes.setCellValueFactory(new PropertyValueFactory<MonitorItem, String>("notes"));
        this.assignedToPC.setCellValueFactory(new PropertyValueFactory<MonitorItem, String>("assignedToPC"));
    }

}
