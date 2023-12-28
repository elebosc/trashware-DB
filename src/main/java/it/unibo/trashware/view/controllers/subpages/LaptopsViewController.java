package it.unibo.trashware.view.controllers.subpages;

import java.io.IOException;

import it.unibo.trashware.controller.api.InventoryController;
import it.unibo.trashware.controller.impl.InventoryControllerImpl;
import it.unibo.trashware.view.controllers.tableItems.LaptopItem;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class LaptopsViewController {

    @FXML
    private TableView<LaptopItem> laptopsTableView;

    @FXML
    private TableColumn<LaptopItem, String> brand;

    @FXML
    private TableColumn<LaptopItem, String> cpu;

    @FXML
    private TableColumn<LaptopItem, String> cpuArch;

    @FXML
    private TableColumn<LaptopItem, String> cpuBrand;

    @FXML
    private TableColumn<LaptopItem, String> cpuModel;

    @FXML
    private TableColumn<LaptopItem, String> id;

    @FXML
    private TableColumn<LaptopItem, String> isBluetoothSupported;

    @FXML
    private TableColumn<LaptopItem, String> isEthernetSupported;

    @FXML
    private TableColumn<LaptopItem, String> isWiFiSupported;

    @FXML
    private TableColumn<LaptopItem, String> model;

    @FXML
    private TableColumn<LaptopItem, String> os;

    @FXML
    private TableColumn<LaptopItem, String> osUpdate;

    @FXML
    private TableColumn<LaptopItem, String> osVersion;

    @FXML
    private TableColumn<LaptopItem, String> ram;

    @FXML
    private TableColumn<LaptopItem, String> screenSize;

    @FXML
    private TableColumn<LaptopItem, String> storage;

    @FXML
    private TableColumn<LaptopItem, String> storageType;

    @FXML
    private TableColumn<LaptopItem, String> storageSize;

    private InventoryController controller;

    @FXML
    public void initialize() {
        try {
            this.controller = new InventoryControllerImpl();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        this.id.setCellValueFactory(new PropertyValueFactory<LaptopItem, String>("id"));
        this.brand.setCellValueFactory(new PropertyValueFactory<LaptopItem, String>("brand"));
        this.model.setCellValueFactory(new PropertyValueFactory<LaptopItem, String>("model"));
        this.cpuBrand.setCellValueFactory(new PropertyValueFactory<LaptopItem, String>("cpuBrand"));
        this.cpuModel.setCellValueFactory(new PropertyValueFactory<LaptopItem, String>("cpuModel"));
        this.cpuArch.setCellValueFactory(new PropertyValueFactory<LaptopItem, String>("cpuArch"));
        this.ram.setCellValueFactory(new PropertyValueFactory<LaptopItem, String>("ram"));
        this.storageType.setCellValueFactory(new PropertyValueFactory<LaptopItem, String>("storageType"));
        this.storageSize.setCellValueFactory(new PropertyValueFactory<LaptopItem, String>("storageSize"));
        this.screenSize.setCellValueFactory(new PropertyValueFactory<LaptopItem, String>("screenSize"));
        this.isEthernetSupported.setCellValueFactory(new PropertyValueFactory<LaptopItem, String>("isEthernetSupported"));
        this.isWiFiSupported.setCellValueFactory(new PropertyValueFactory<LaptopItem, String>("isWiFiSupported"));
        this.isBluetoothSupported.setCellValueFactory(new PropertyValueFactory<LaptopItem, String>("isBluetoothSupported"));
        this.osVersion.setCellValueFactory(new PropertyValueFactory<LaptopItem, String>("osVersion"));
        this.osUpdate.setCellValueFactory(new PropertyValueFactory<LaptopItem, String>("osUpdate"));
    }

}