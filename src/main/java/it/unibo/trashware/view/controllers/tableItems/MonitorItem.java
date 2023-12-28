package it.unibo.trashware.view.controllers.tableItems;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class MonitorItem {

    private StringProperty notes;

    private StringProperty assignedToPC;

    private StringProperty brand;

    private StringProperty connectivity;

    private StringProperty hasEmbeddedAudio;

    private StringProperty id;

    private StringProperty isDVISupported;

    private StringProperty isVGASupported;

    private StringProperty model;

    private StringProperty monitorType;

    private StringProperty ratio;

    private StringProperty size;

    public MonitorItem(String id, String brand, String model, String connectivity,
            String monitorType, String size, String ratio,
            String isVGASupported, String isDVISupported,  String hasEmbeddedAudio,
            String notes, String assignedToPC) {
        this.notes = new SimpleStringProperty(notes);
        this.assignedToPC = new SimpleStringProperty(assignedToPC);
        this.brand = new SimpleStringProperty(brand);
        this.connectivity = new SimpleStringProperty(connectivity);
        this.hasEmbeddedAudio = new SimpleStringProperty(hasEmbeddedAudio);
        this.id = new SimpleStringProperty(id);
        this.isDVISupported = new SimpleStringProperty(isDVISupported);
        this.isVGASupported = new SimpleStringProperty(isVGASupported);
        this.model = new SimpleStringProperty(model);
        this.monitorType = new SimpleStringProperty(monitorType);
        this.ratio = new SimpleStringProperty(ratio);
        this.size = new SimpleStringProperty(size);
    }

    public String getNotes() {
        return notes.get();
    }

    public String getAssignedToPC() {
        return assignedToPC.get();
    }

    public String getBrand() {
        return brand.get();
    }

    public String getConnectivity() {
        return connectivity.get();
    }

    public String getHasEmbeddedAudio() {
        return hasEmbeddedAudio.get();
    }

    public String getId() {
        return id.get();
    }

    public String getIsDVISupported() {
        return isDVISupported.get();
    }

    public String getIsVGASupported() {
        return isVGASupported.get();
    }

    public String getModel() {
        return model.get();
    }

    public String getMonitorType() {
        return monitorType.get();
    }

    public String getRatio() {
        return ratio.get();
    }

    public String getSize() {
        return size.get();
    }
    
}
