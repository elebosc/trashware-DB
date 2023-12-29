package it.unibo.trashware.view.controllers.tableItems;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class RAMModuleItem {

    private StringProperty assignedToPC;

    private StringProperty brand;

    private StringProperty id;

    private StringProperty model;

    private StringProperty size;

    public RAMModuleItem(String id, String brand, String model, String size, String assignedToPC) {
        this.assignedToPC = new SimpleStringProperty(assignedToPC);
        this.brand = new SimpleStringProperty(brand);
        this.id = new SimpleStringProperty(id);
        this.model = new SimpleStringProperty(model);
        this.size = new SimpleStringProperty(size);
    }

    public String getAssignedToPC() {
        return assignedToPC.get();
    }

    public String getBrand() {
        return brand.get();
    }

    public String getId() {
        return id.get();
    }

    public String getModel() {
        return model.get();
    }

    public String getSize() {
        return size.get();
    }

}
