package it.unibo.trashware.view.controllers.subpages;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import it.unibo.trashware.commons.FieldTags;
import it.unibo.trashware.controller.api.InventoryController;
import it.unibo.trashware.controller.impl.InventoryControllerImpl;
import it.unibo.trashware.view.controllers.tableItems.RAMModuleItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class RAMModulesViewController {

    @FXML
    private TableView<RAMModuleItem> ramTableView;

    @FXML
    private TableColumn<RAMModuleItem, String> assignedToPC;

    @FXML
    private TableColumn<RAMModuleItem, String> brand;

    @FXML
    private TableColumn<RAMModuleItem, String> id;

    @FXML
    private TableColumn<RAMModuleItem, String> model;

    @FXML
    private TableColumn<RAMModuleItem, String> size;

    @FXML
    private TableColumn<RAMModuleItem, String> notes;

    private InventoryController controller;

    @FXML
    public void initialize() {
        try {
            this.controller = new InventoryControllerImpl();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        this.id.setCellValueFactory(new PropertyValueFactory<RAMModuleItem, String>("id"));
        this.brand.setCellValueFactory(new PropertyValueFactory<RAMModuleItem, String>("brand"));
        this.model.setCellValueFactory(new PropertyValueFactory<RAMModuleItem, String>("model"));
        this.size.setCellValueFactory(new PropertyValueFactory<RAMModuleItem, String>("size"));
        this.notes.setCellValueFactory(new PropertyValueFactory<RAMModuleItem, String>("notes"));
        this.assignedToPC.setCellValueFactory(new PropertyValueFactory<RAMModuleItem, String>("assignedToPC"));

        fillTable();
    }

    private void fillTable() {
        final ObservableList<RAMModuleItem> list = FXCollections.observableArrayList();
        List<Map<FieldTags, String>> result = this.controller.getRAMModulesList();
        for (var map : result) {
            list.add(new RAMModuleItem(
                map.get(FieldTags.RAM_ID),
                map.get(FieldTags.BRAND),
                map.get(FieldTags.MODEL),
                map.get(FieldTags.RAM_SIZE),
                map.get(FieldTags.NOTES),
                map.get(FieldTags.ASSIGNED_TO_PC)
            ));
        }
        this.ramTableView.setItems(list);
    }

}
