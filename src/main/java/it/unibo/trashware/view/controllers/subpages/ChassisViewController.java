package it.unibo.trashware.view.controllers.subpages;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import it.unibo.trashware.commons.FieldTags;
import it.unibo.trashware.controller.api.InventoryController;
import it.unibo.trashware.controller.impl.InventoryControllerImpl;
import it.unibo.trashware.view.controllers.tableItems.ChassisItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ChassisViewController {

    @FXML
    private TableView<ChassisItem> chassisTableView;

    @FXML
    private TableColumn<ChassisItem, String> assignedToPC;

    @FXML
    private TableColumn<ChassisItem, String> brand;

    @FXML
    private TableColumn<ChassisItem, String> color;

    @FXML
    private TableColumn<ChassisItem, String> id;

    @FXML
    private TableColumn<ChassisItem, String> model;

    private InventoryController controller;

    @FXML
    public void initialize() {
        try {
            this.controller = new InventoryControllerImpl();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        this.id.setCellValueFactory(new PropertyValueFactory<ChassisItem, String>("id"));
        this.brand.setCellValueFactory(new PropertyValueFactory<ChassisItem, String>("brand"));
        this.model.setCellValueFactory(new PropertyValueFactory<ChassisItem, String>("model"));
        this.color.setCellValueFactory(new PropertyValueFactory<ChassisItem, String>("color"));
        this.assignedToPC.setCellValueFactory(new PropertyValueFactory<ChassisItem, String>("assignedToPC"));

        fillTable();
    }

    private void fillTable() {
        final ObservableList<ChassisItem> list = FXCollections.observableArrayList();
        List<Map<FieldTags, String>> result = this.controller.getChassisList();
        for (var map : result) {
            list.add(new ChassisItem(
                map.get(FieldTags.CHASSIS_ID),
                map.get(FieldTags.BRAND),
                map.get(FieldTags.MODEL),
                map.get(FieldTags.COLOR),
                map.get(FieldTags.ASSIGNED_TO_PC)
            ));
        }
        this.chassisTableView.setItems(list);
    }

}
