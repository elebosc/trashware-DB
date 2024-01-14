package it.unibo.trashware.view.controllers.tableItems;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ComponentItem {

    private StringProperty brand;

    private StringProperty id;

    private StringProperty model;

    private StringProperty notes;

    private StringProperty type;

    private StringProperty assignedToPC;

    private StringProperty assignedToRequest;

    public ComponentItem(String id, String type, String brand, String model, String notes,
            String assignedToPC, String assignedToRequest) {
        this.brand = new SimpleStringProperty(brand);
        this.id = new SimpleStringProperty(id);
        this.model = new SimpleStringProperty(model);
        this.notes = new SimpleStringProperty(notes);
        this.type = new SimpleStringProperty(type);
        this.assignedToPC = new SimpleStringProperty(assignedToPC);
        this.assignedToRequest = new SimpleStringProperty(assignedToRequest);
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

    public String getNotes() {
        return notes.get();
    }

    public String getType() {
        return type.get();
    }

    public String getAssignedToPC() {
        return assignedToPC.get();
    }

    public String getAssignedToRequest() {
        return assignedToRequest.get();
    }
    
}
