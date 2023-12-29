package it.unibo.trashware.view.controllers.tableItems;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ChassisItem {

    private StringProperty assignedToPC;

    private StringProperty brand;

    private StringProperty color;

    private StringProperty id;

    private StringProperty model;

    public ChassisItem(String id, String brand, String model, String color, String assignedToPC) {
        this.assignedToPC = new SimpleStringProperty(assignedToPC);
        this.brand = new SimpleStringProperty(brand);
        this.color = new SimpleStringProperty(color);
        this.id = new SimpleStringProperty(id);
        this.model = new SimpleStringProperty(model);
    }

    public String getAssignedToPC() {
        return assignedToPC.get();
    }

    public String getBrand() {
        return brand.get();
    }

    public String getColor() {
        return color.get();
    }

    public String getId() {
        return id.get();
    }

    public String getModel() {
        return model.get();
    }

}
