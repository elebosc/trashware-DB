package it.unibo.trashware.view.controllers.subpages;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import it.unibo.trashware.commons.FieldTags;
import it.unibo.trashware.controller.api.InventoryController;
import it.unibo.trashware.controller.impl.InventoryControllerImpl;
import it.unibo.trashware.view.controllers.tableItems.StorageDeviceItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class StorageDevicesViewController {

    @FXML
    private TableView<StorageDeviceItem> storageDevicesTableView;

    @FXML
    private TableColumn<StorageDeviceItem, String> assignedToPC;

    @FXML
    private TableColumn<StorageDeviceItem, String> brand;

    @FXML
    private TableColumn<StorageDeviceItem, String> id;

    @FXML
    private TableColumn<StorageDeviceItem, String> model;

    @FXML
    private TableColumn<StorageDeviceItem, String> size;

    @FXML
    private TableColumn<StorageDeviceItem, String> type;

    @FXML
    private TableColumn<StorageDeviceItem, String> notes;

    private InventoryController controller;

    @FXML
    public void initialize() {
        try {
            this.controller = new InventoryControllerImpl();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        this.id.setCellValueFactory(new PropertyValueFactory<StorageDeviceItem, String>("id"));
        this.brand.setCellValueFactory(new PropertyValueFactory<StorageDeviceItem, String>("brand"));
        this.model.setCellValueFactory(new PropertyValueFactory<StorageDeviceItem, String>("model"));
        this.type.setCellValueFactory(new PropertyValueFactory<StorageDeviceItem, String>("type"));
        this.size.setCellValueFactory(new PropertyValueFactory<StorageDeviceItem, String>("size"));
        this.notes.setCellValueFactory(new PropertyValueFactory<StorageDeviceItem, String>("notes"));
        this.assignedToPC.setCellValueFactory(new PropertyValueFactory<StorageDeviceItem, String>("assignedToPC"));

        fillTable();
    }

    private void fillTable() {
        final ObservableList<StorageDeviceItem> list = FXCollections.observableArrayList();
        List<Map<FieldTags, String>> result = this.controller.getStorageDevicesList();
        for (var map : result) {
            list.add(new StorageDeviceItem(
                map.get(FieldTags.STORAGE_ID),
                map.get(FieldTags.BRAND),
                map.get(FieldTags.MODEL),
                map.get(FieldTags.STORAGE_01_TYPE),
                map.get(FieldTags.STORAGE_01_SIZE),
                map.get(FieldTags.NOTES),
                map.get(FieldTags.ASSIGNED_TO_PC)
            ));
        }
        this.storageDevicesTableView.setItems(list);
    }

}
