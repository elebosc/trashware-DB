package it.unibo.trashware.view.controllers.subpages;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import it.unibo.trashware.commons.FieldTags;
import it.unibo.trashware.controller.api.InventoryController;
import it.unibo.trashware.controller.impl.InventoryControllerImpl;
import it.unibo.trashware.view.controllers.tableItems.LaptopItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    private TableColumn<LaptopItem, String> color;

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
    private TableColumn<LaptopItem, String> osUpdate;

    @FXML
    private TableColumn<LaptopItem, String> osVersion;

    @FXML
    private TableColumn<LaptopItem, String> ram;

    @FXML
    private TableColumn<LaptopItem, String> screenSize;

    @FXML
    private TableColumn<LaptopItem, String> storage01Type;

    @FXML
    private TableColumn<LaptopItem, String> storage01Size;

    @FXML
    private TableColumn<LaptopItem, String> storage02Type;

    @FXML
    private TableColumn<LaptopItem, String> storage02Size;

    @FXML
    private TableColumn<LaptopItem, String> notes;

    @FXML
    private TableColumn<LaptopItem, String> assignedToRequest;

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
        this.color.setCellValueFactory(new PropertyValueFactory<LaptopItem, String>("color"));
        this.cpuBrand.setCellValueFactory(new PropertyValueFactory<LaptopItem, String>("cpuBrand"));
        this.cpuModel.setCellValueFactory(new PropertyValueFactory<LaptopItem, String>("cpuModel"));
        this.cpuArch.setCellValueFactory(new PropertyValueFactory<LaptopItem, String>("cpuArch"));
        this.ram.setCellValueFactory(new PropertyValueFactory<LaptopItem, String>("ram"));
        this.storage01Type.setCellValueFactory(new PropertyValueFactory<LaptopItem, String>("storage01Type"));
        this.storage01Size.setCellValueFactory(new PropertyValueFactory<LaptopItem, String>("storage01Size"));
        this.storage02Type.setCellValueFactory(new PropertyValueFactory<LaptopItem, String>("storage02Type"));
        this.storage02Size.setCellValueFactory(new PropertyValueFactory<LaptopItem, String>("storage02Size"));
        this.screenSize.setCellValueFactory(new PropertyValueFactory<LaptopItem, String>("screenSize"));
        this.isEthernetSupported.setCellValueFactory(new PropertyValueFactory<LaptopItem, String>("isEthernetSupported"));
        this.isWiFiSupported.setCellValueFactory(new PropertyValueFactory<LaptopItem, String>("isWiFiSupported"));
        this.isBluetoothSupported.setCellValueFactory(new PropertyValueFactory<LaptopItem, String>("isBluetoothSupported"));
        this.osVersion.setCellValueFactory(new PropertyValueFactory<LaptopItem, String>("osVersion"));
        this.osUpdate.setCellValueFactory(new PropertyValueFactory<LaptopItem, String>("osUpdate"));
        this.notes.setCellValueFactory(new PropertyValueFactory<LaptopItem, String>("notes"));
        this.assignedToRequest.setCellValueFactory(new PropertyValueFactory<LaptopItem, String>("assignedToRequest"));

        fillTable();
    }

    private void fillTable() {
        final ObservableList<LaptopItem> list = FXCollections.observableArrayList();
        List<Map<FieldTags, String>> result = this.controller.getLaptopsList();
        for (var map : result) {
            list.add(new LaptopItem(
                map.get(FieldTags.PCID),
                map.get(FieldTags.BRAND),
                map.get(FieldTags.MODEL),
                map.get(FieldTags.COLOR),
                map.get(FieldTags.CPU_BRAND),
                map.get(FieldTags.CPU_MODEL),
                map.get(FieldTags.CPU_ARC),
                map.get(FieldTags.RAM_SIZE),
                map.get(FieldTags.STORAGE_01_TYPE),
                map.get(FieldTags.STORAGE_01_SIZE),
                map.get(FieldTags.STORAGE_02_TYPE),
                map.get(FieldTags.STORAGE_02_SIZE),
                map.get(FieldTags.SCREENSIZE),
                map.get(FieldTags.ETH),
                map.get(FieldTags.WIFI),
                map.get(FieldTags.BLUETOOTH),
                map.get(FieldTags.OS_VERSION),
                map.get(FieldTags.OS_UPDATE),
                map.get(FieldTags.NOTES),
                map.get(FieldTags.ASSIGNED_TO_REQUEST)
            ));
        }
        this.laptopsTableView.setItems(list);
    }

}