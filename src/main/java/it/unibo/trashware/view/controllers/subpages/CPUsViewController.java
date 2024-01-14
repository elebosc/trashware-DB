package it.unibo.trashware.view.controllers.subpages;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import it.unibo.trashware.commons.FieldTags;
import it.unibo.trashware.controller.api.InventoryController;
import it.unibo.trashware.controller.impl.InventoryControllerImpl;
import it.unibo.trashware.view.controllers.tableItems.CPUItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class CPUsViewController {

    @FXML
    private TableView<CPUItem> cpusTableView;

    @FXML
    private TableColumn<CPUItem, String> architecture;

    @FXML
    private TableColumn<CPUItem, String> brand;

    @FXML
    private TableColumn<CPUItem, String> id;

    @FXML
    private TableColumn<CPUItem, String> model;

    @FXML
    private TableColumn<CPUItem, String> notes;

    @FXML
    private TableColumn<CPUItem, String> assignedToPC;

    @FXML
    private TableColumn<CPUItem, String> assignedToRequest;

    private InventoryController controller;

    @FXML
    public void initialize() {
        try {
            this.controller = new InventoryControllerImpl();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        this.id.setCellValueFactory(new PropertyValueFactory<CPUItem, String>("id"));
        this.brand.setCellValueFactory(new PropertyValueFactory<CPUItem, String>("brand"));
        this.model.setCellValueFactory(new PropertyValueFactory<CPUItem, String>("model"));
        this.architecture.setCellValueFactory(new PropertyValueFactory<CPUItem, String>("architecture"));
        this.notes.setCellValueFactory(new PropertyValueFactory<CPUItem, String>("notes"));
        this.assignedToPC.setCellValueFactory(new PropertyValueFactory<CPUItem, String>("assignedToPC"));
        this.assignedToRequest.setCellValueFactory(new PropertyValueFactory<CPUItem, String>("assignedToRequest"));

        fillTable();
    }

    private void fillTable() {
        final ObservableList<CPUItem> list = FXCollections.observableArrayList();
        List<Map<FieldTags, String>> result = this.controller.getCPUsList();
        for (var map : result) {
            list.add(new CPUItem(
                map.get(FieldTags.CPU_ID), 
                map.get(FieldTags.CPU_BRAND), 
                map.get(FieldTags.CPU_MODEL), 
                map.get(FieldTags.CPU_ARC), 
                map.get(FieldTags.NOTES),
                map.get(FieldTags.ASSIGNED_TO_PC),
                map.get(FieldTags.ASSIGNED_TO_REQUEST)
            ));
        }
        this.cpusTableView.setItems(list);
    }

}
