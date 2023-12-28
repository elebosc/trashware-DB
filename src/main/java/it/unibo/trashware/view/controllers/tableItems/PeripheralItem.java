package it.unibo.trashware.view.controllers.tableItems;

import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;

public class PeripheralItem {

    private StringProperty brand;

    private StringProperty connectivity;

    private StringProperty id;

    private StringProperty model;

    private StringProperty notes;

    private StringProperty type;

    private StringProperty assignedToPC;

    public PeripheralItem(String id, String type, String brand, String model, 
            String connectivity, String notes, String assignedToPC) {
        this.brand = new SimpleStringProperty(brand);
        this.connectivity = new SimpleStringProperty(connectivity);
        this.id = new SimpleStringProperty(id);
        this.model = new SimpleStringProperty(model);
        this.notes = new SimpleStringProperty(notes);
        this.type = new SimpleStringProperty(type);
        this.assignedToPC = new SimpleStringProperty(assignedToPC);
    }

    public String getBrand() {
        return brand.get();
    }

    public String getConnectivity() {
        return connectivity.get();
    }

    public String getId() {
        return id.get();
    }

    public String getModel() {
        return model.get();
    }

    public String getNotes() {
        return notes.get();
    }

    public String getType() {
        return type.get();
    }

    public String getAssignedToPC() {
        return assignedToPC.get();
    }
    
}
