package it.unibo.trashware.view.controllers.tableItems;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class DesktopItem {
    
    private StringProperty caseBrand;

    private StringProperty caseColor;

    private StringProperty caseModel;

    private StringProperty cpuArch;

    private StringProperty cpuBrand;

    private StringProperty cpuModel;

    private StringProperty id;

    private StringProperty isBluetoothSupported;

    private StringProperty isEthernetSupported;

    private StringProperty isWiFiSupported;

    private StringProperty keyboardID;

    private StringProperty monitorID;

    private StringProperty mouseID;

    private StringProperty notes;

    private StringProperty osUpdate;

    private StringProperty osVersion;

    private StringProperty ram;

    private StringProperty speakersID;

    private StringProperty storageSize;

    private StringProperty storageType;

    public DesktopItem(String id, String caseBrand, String caseModel, String caseColor, String cpuBrand,
            String cpuModel, String cpuArch, String ram, String storageType, String storageSize,
            String isEthernetSupported, String isWiFiSupported, String isBluetoothSupported,
            String osVersion, String osUpdate, String monitorID, String keyboardID, String mouseID, String speakersID,
            String notes) {
        this.caseBrand = new SimpleStringProperty(caseBrand);
        this.caseColor = new SimpleStringProperty(caseColor);
        this.caseModel = new SimpleStringProperty(caseModel);
        this.cpuArch = new SimpleStringProperty(cpuArch);
        this.cpuBrand = new SimpleStringProperty(cpuBrand);
        this.cpuModel = new SimpleStringProperty(cpuModel);
        this.id = new SimpleStringProperty(id);
        this.isBluetoothSupported = new SimpleStringProperty(isBluetoothSupported);
        this.isEthernetSupported = new SimpleStringProperty(isEthernetSupported);
        this.isWiFiSupported = new SimpleStringProperty(isWiFiSupported);
        this.keyboardID = new SimpleStringProperty(keyboardID);
        this.monitorID = new SimpleStringProperty(monitorID);
        this.mouseID = new SimpleStringProperty(mouseID);
        this.notes = new SimpleStringProperty(notes);
        this.osUpdate = new SimpleStringProperty(osUpdate);
        this.osVersion = new SimpleStringProperty(osVersion);
        this.ram = new SimpleStringProperty(ram);
        this.speakersID = new SimpleStringProperty(speakersID);
        this.storageSize = new SimpleStringProperty(storageSize);
        this.storageType = new SimpleStringProperty(storageType);
    }

    public String getCaseBrand() {
        return caseBrand.get();
    }

    public String getCaseColor() {
        return caseColor.get();
    }

    public String getCaseModel() {
        return caseModel.get();
    }

    public String getCpuArch() {
        return cpuArch.get();
    }

    public String getCpuBrand() {
        return cpuBrand.get();
    }

    public String getCpuModel() {
        return cpuModel.get();
    }

    public String getId() {
        return id.get();
    }

    public String getIsBluetoothSupported() {
        return isBluetoothSupported.get();
    }

    public String getIsEthernetSupported() {
        return isEthernetSupported.get();
    }

    public String getIsWiFiSupported() {
        return isWiFiSupported.get();
    }

    public String getKeyboardID() {
        return keyboardID.get();
    }

    public String getMonitorID() {
        return monitorID.get();
    }

    public String getMouseID() {
        return mouseID.get();
    }

    public String getNotes() {
        return notes.get();
    }

    public String getOsUpdate() {
        return osUpdate.get();
    }

    public String getOsVersion() {
        return osVersion.get();
    }

    public String getRam() {
        return ram.get();
    }

    public String getSpeakersID() {
        return speakersID.get();
    }

    public String getStorageSize() {
        return storageSize.get();
    }

    public String getStorageType() {
        return storageType.get();
    }

}
