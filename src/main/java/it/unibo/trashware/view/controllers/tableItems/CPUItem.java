package it.unibo.trashware.view.controllers.tableItems;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CPUItem {
    
    private StringProperty architecture;

    private StringProperty brand;

    private StringProperty id;

    private StringProperty model;

    private StringProperty assignedToPC;

    public CPUItem(String id, String brand, String model, String architecture, String assignedToPC) {
        this.architecture = new SimpleStringProperty(architecture);
        this.brand = new SimpleStringProperty(brand);
        this.id = new SimpleStringProperty(id);
        this.model = new SimpleStringProperty(model);
        this.assignedToPC = new SimpleStringProperty(assignedToPC);
    }

    public String getArchitecture() {
        return architecture.get();
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

    public String getAssignedToPC() {
        return assignedToPC.get();
    }

}
