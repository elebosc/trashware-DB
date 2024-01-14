package it.unibo.trashware.view.controllers.tableItems;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class RAMModuleItem {

    private StringProperty assignedToPC;

    private StringProperty brand;

    private StringProperty id;

    private StringProperty model;

    private StringProperty size;

    private StringProperty notes;

    private StringProperty assignedToRequest;

    public RAMModuleItem(String id, String brand, String model, String size, String notes,
            String assignedToPC, String assignedToRequest) {
        this.assignedToPC = new SimpleStringProperty(assignedToPC);
        this.brand = new SimpleStringProperty(brand);
        this.id = new SimpleStringProperty(id);
        this.model = new SimpleStringProperty(model);
        this.size = new SimpleStringProperty(size);
        this.notes = new SimpleStringProperty(notes);
        this.assignedToRequest = new SimpleStringProperty(assignedToRequest);
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

    public String getNotes() {
        return notes.get();
    }

    public String getAssignedToRequest() {
        return assignedToRequest.get();
    }

}
