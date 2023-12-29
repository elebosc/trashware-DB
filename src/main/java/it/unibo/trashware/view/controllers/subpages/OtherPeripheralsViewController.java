package it.unibo.trashware.view.controllers.subpages;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import it.unibo.trashware.commons.FieldTags;
import it.unibo.trashware.controller.api.InventoryController;
import it.unibo.trashware.controller.impl.InventoryControllerImpl;
import it.unibo.trashware.view.controllers.tableItems.PeripheralItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class OtherPeripheralsViewController {

    @FXML
    private TableView<PeripheralItem> otherPeripheralsTableView;

    @FXML
    private TableColumn<PeripheralItem, String> brand;

    @FXML
    private TableColumn<PeripheralItem, String> connectivity;

    @FXML
    private TableColumn<PeripheralItem, String> id;

    @FXML
    private TableColumn<PeripheralItem, String> model;

    @FXML
    private TableColumn<PeripheralItem, String> notes;

    @FXML
    private TableColumn<PeripheralItem, String> type;

    @FXML
    private TableColumn<PeripheralItem, String> assignedToPC;

    private InventoryController controller;

    @FXML
    public void initialize() {
        try {
            this.controller = new InventoryControllerImpl();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        this.id.setCellValueFactory(new PropertyValueFactory<PeripheralItem, String>("id"));
        this.brand.setCellValueFactory(new PropertyValueFactory<PeripheralItem, String>("brand"));
        this.model.setCellValueFactory(new PropertyValueFactory<PeripheralItem, String>("model"));
        this.type.setCellValueFactory(new PropertyValueFactory<PeripheralItem, String>("type"));
        this.connectivity.setCellValueFactory(new PropertyValueFactory<PeripheralItem, String>("connectivity"));
        this.notes.setCellValueFactory(new PropertyValueFactory<PeripheralItem, String>("notes"));
        this.assignedToPC.setCellValueFactory(new PropertyValueFactory<PeripheralItem, String>("assignedToPC"));

        fillTable();
    }

    private void fillTable() {
        final ObservableList<PeripheralItem> list = FXCollections.observableArrayList();
        List<Map<FieldTags, String>> result = this.controller.getOtherPeripheralsList();
        for (var map : result) {
            list.add(new PeripheralItem(
                map.get(FieldTags.PERIPHERAL_ID),
                map.get(FieldTags.PERIPHERAL_TYPE), 
                map.get(FieldTags.BRAND), 
                map.get(FieldTags.MODEL), 
                map.get(FieldTags.CONNECTIVITY), 
                map.get(FieldTags.NOTES), 
                map.get(FieldTags.ASSIGNED_TO_PC)
            ));
        }
        this.otherPeripheralsTableView.setItems(list);
    }

}
