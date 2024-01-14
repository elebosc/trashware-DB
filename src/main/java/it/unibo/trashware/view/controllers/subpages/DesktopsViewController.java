package it.unibo.trashware.view.controllers.subpages;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import it.unibo.trashware.commons.FieldTags;
import it.unibo.trashware.controller.api.InventoryController;
import it.unibo.trashware.controller.impl.InventoryControllerImpl;
import it.unibo.trashware.view.controllers.tableItems.DesktopItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class DesktopsViewController {

    @FXML
    private TableView<DesktopItem> desktopsTableView;

    @FXML
    private TableColumn<DesktopItem, String> caseBrand;

    @FXML
    private TableColumn<DesktopItem, String> caseColor;

    @FXML
    private TableColumn<DesktopItem, String> caseModel;

    @FXML
    private TableColumn<DesktopItem, String> cpuArch;

    @FXML
    private TableColumn<DesktopItem, String> cpuBrand;

    @FXML
    private TableColumn<DesktopItem, String> cpuModel;

    @FXML
    private TableColumn<DesktopItem, String> id;

    @FXML
    private TableColumn<DesktopItem, String> isBluetoothSupported;

    @FXML
    private TableColumn<DesktopItem, String> isEthernetSupported;

    @FXML
    private TableColumn<DesktopItem, String> isWiFiSupported;

    @FXML
    private TableColumn<DesktopItem, String> keyboardID;

    @FXML
    private TableColumn<DesktopItem, String> monitorID;

    @FXML
    private TableColumn<DesktopItem, String> mouseID;

    @FXML
    private TableColumn<DesktopItem, String> notes;

    @FXML
    private TableColumn<DesktopItem, String> osUpdate;

    @FXML
    private TableColumn<DesktopItem, String> osVersion;

    @FXML
    private TableColumn<DesktopItem, String> ram;

    @FXML
    private TableColumn<DesktopItem, String> speakersID;

    @FXML
    private TableColumn<DesktopItem, String> storage01Size;

    @FXML
    private TableColumn<DesktopItem, String> storage01Type;

    @FXML
    private TableColumn<DesktopItem, String> storage02Size;

    @FXML
    private TableColumn<DesktopItem, String> storage02Type;

    @FXML
    private TableColumn<DesktopItem, String> assignedToRequest;

    private InventoryController controller;

    @FXML
    public void initialize() {
        try {
            this.controller = new InventoryControllerImpl();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        this.id.setCellValueFactory(new PropertyValueFactory<DesktopItem, String>("id"));
        this.caseBrand.setCellValueFactory(new PropertyValueFactory<DesktopItem, String>("caseBrand"));
        this.caseModel.setCellValueFactory(new PropertyValueFactory<DesktopItem, String>("caseModel"));
        this.caseColor.setCellValueFactory(new PropertyValueFactory<DesktopItem, String>("caseColor"));
        this.cpuBrand.setCellValueFactory(new PropertyValueFactory<DesktopItem, String>("cpuBrand"));
        this.cpuModel.setCellValueFactory(new PropertyValueFactory<DesktopItem, String>("cpuModel"));
        this.cpuArch.setCellValueFactory(new PropertyValueFactory<DesktopItem, String>("cpuArch"));
        this.ram.setCellValueFactory(new PropertyValueFactory<DesktopItem, String>("ram"));
        this.storage01Type.setCellValueFactory(new PropertyValueFactory<DesktopItem, String>("storage01Type"));
        this.storage01Size.setCellValueFactory(new PropertyValueFactory<DesktopItem, String>("storage01Size"));
        this.storage02Type.setCellValueFactory(new PropertyValueFactory<DesktopItem, String>("storage02Type"));
        this.storage02Size.setCellValueFactory(new PropertyValueFactory<DesktopItem, String>("storage02Size"));
        this.isEthernetSupported.setCellValueFactory(new PropertyValueFactory<DesktopItem, String>("isEthernetSupported"));
        this.isWiFiSupported.setCellValueFactory(new PropertyValueFactory<DesktopItem, String>("isWiFiSupported"));
        this.isBluetoothSupported.setCellValueFactory(new PropertyValueFactory<DesktopItem, String>("isBluetoothSupported"));
        this.osVersion.setCellValueFactory(new PropertyValueFactory<DesktopItem, String>("osVersion"));
        this.osUpdate.setCellValueFactory(new PropertyValueFactory<DesktopItem, String>("osUpdate"));
        this.monitorID.setCellValueFactory(new PropertyValueFactory<DesktopItem, String>("monitorID"));
        this.keyboardID.setCellValueFactory(new PropertyValueFactory<DesktopItem, String>("keyboardID"));
        this.mouseID.setCellValueFactory(new PropertyValueFactory<DesktopItem, String>("mouseID"));
        this.speakersID.setCellValueFactory(new PropertyValueFactory<DesktopItem, String>("speakersID"));
        this.notes.setCellValueFactory(new PropertyValueFactory<DesktopItem, String>("notes"));
        this.assignedToRequest.setCellValueFactory(new PropertyValueFactory<DesktopItem, String>("assignedToRequest"));

        fillTable();
    }

    private void fillTable() {
        final ObservableList<DesktopItem> list = FXCollections.observableArrayList();
        List<Map<FieldTags, String>> result = this.controller.getDesktopsList();
        for (var map : result) {
            list.add(new DesktopItem(
                map.get(FieldTags.PCID),
                map.get(FieldTags.CHASSIS_BRAND),
                map.get(FieldTags.CHASSIS_MODEL),
                map.get(FieldTags.CHASSIS_COLOR),
                map.get(FieldTags.CPU_BRAND),
                map.get(FieldTags.CPU_MODEL),
                map.get(FieldTags.CPU_ARC),
                map.get(FieldTags.RAM_SIZE),
                map.get(FieldTags.STORAGE_01_TYPE),
                map.get(FieldTags.STORAGE_01_SIZE),
                map.get(FieldTags.STORAGE_02_TYPE),
                map.get(FieldTags.STORAGE_02_SIZE),
                map.get(FieldTags.ETH),
                map.get(FieldTags.WIFI),
                map.get(FieldTags.BLUETOOTH),
                map.get(FieldTags.OS_VERSION),
                map.get(FieldTags.OS_UPDATE),
                map.get(FieldTags.MONITOR_ID),
                map.get(FieldTags.KEYBOARD_ID),
                map.get(FieldTags.MOUSE_ID),
                map.get(FieldTags.SPEAKERS_ID),
                map.get(FieldTags.NOTES),
                map.get(FieldTags.ASSIGNED_TO_REQUEST)
            ));
        }
        this.desktopsTableView.setItems(list);
    }

}
