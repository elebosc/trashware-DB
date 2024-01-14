package it.unibo.trashware.view.controllers.subpages;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import it.unibo.trashware.commons.FieldTags;
import it.unibo.trashware.controller.api.InventoryController;
import it.unibo.trashware.controller.impl.InventoryControllerImpl;
import it.unibo.trashware.view.controllers.tableItems.ComponentItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class OtherComponentsViewController {

    @FXML
    private TableView<ComponentItem> otherComponentsTableView;

    @FXML
    private TableColumn<ComponentItem, String> brand;

    @FXML
    private TableColumn<ComponentItem, String> id;

    @FXML
    private TableColumn<ComponentItem, String> model;

    @FXML
    private TableColumn<ComponentItem, String> notes;

    @FXML
    private TableColumn<ComponentItem, String> type;

    @FXML
    private TableColumn<ComponentItem, String> assignedToPC;

    @FXML
    private TableColumn<ComponentItem, String> assignedToRequest;

    private InventoryController controller;

    @FXML
    public void initialize() {
        try {
            this.controller = new InventoryControllerImpl();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        this.id.setCellValueFactory(new PropertyValueFactory<ComponentItem, String>("id"));
        this.type.setCellValueFactory(new PropertyValueFactory<ComponentItem, String>("type"));
        this.brand.setCellValueFactory(new PropertyValueFactory<ComponentItem, String>("brand"));
        this.model.setCellValueFactory(new PropertyValueFactory<ComponentItem, String>("model"));
        this.notes.setCellValueFactory(new PropertyValueFactory<ComponentItem, String>("notes"));
        this.assignedToPC.setCellValueFactory(new PropertyValueFactory<ComponentItem, String>("assignedToPC"));
        this.assignedToRequest.setCellValueFactory(new PropertyValueFactory<ComponentItem, String>("assignedToRequest"));

        fillTable();
    }

    private void fillTable() {
        final ObservableList<ComponentItem> list = FXCollections.observableArrayList();
        List<Map<FieldTags, String>> result = this.controller.getOtherComponentsList();
        for (var map : result) {
            list.add(new ComponentItem(
                map.get(FieldTags.COMPONENT_ID),
                map.get(FieldTags.COMPONENT_TYPE),
                map.get(FieldTags.BRAND),
                map.get(FieldTags.MODEL),
                map.get(FieldTags.NOTES),
                map.get(FieldTags.ASSIGNED_TO_PC),
                map.get(FieldTags.ASSIGNED_TO_REQUEST)
            ));
        }
        this.otherComponentsTableView.setItems(list);
    }

}
