package it.unibo.trashware.view.controllers.tableItems;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CPUItem {
    
    private StringProperty architecture;

    private StringProperty brand;

    private StringProperty id;

    private StringProperty model;

    private StringProperty notes;

    private StringProperty assignedToPC;

    private StringProperty assignedToRequest;

    public CPUItem(String id, String brand, String model, String architecture, String notes,
            String assignedToPC, String assignedToRequest) {
        this.architecture = new SimpleStringProperty(architecture);
        this.brand = new SimpleStringProperty(brand);
        this.id = new SimpleStringProperty(id);
        this.model = new SimpleStringProperty(model);
        this.notes = new SimpleStringProperty(notes);
        this.assignedToPC = new SimpleStringProperty(assignedToPC);
        this.assignedToRequest = new SimpleStringProperty(assignedToRequest);
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

    public String getNotes() {
        return notes.get();
    }

    public String getAssignedToPC() {
        return assignedToPC.get();
    }

    public String getAssignedToRequest() {
        return assignedToRequest.get();
    }

}
