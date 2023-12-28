package it.unibo.trashware.view.controllers.tableItems;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class LaptopItem {

    private StringProperty brand;

    private StringProperty color;

    private StringProperty cpuArch;

    private StringProperty cpuBrand;

    private StringProperty cpuModel;

    private StringProperty id;

    private StringProperty isBluetoothSupported;

    private StringProperty isEthernetSupported;

    private StringProperty isWiFiSupported;

    private StringProperty model;

    private StringProperty osUpdate;

    private StringProperty osVersion;

    private StringProperty ram;

    private StringProperty screenSize;

    private StringProperty storageType;

    private StringProperty storageSize;

    private StringProperty notes;

    public LaptopItem(String id, String brand, String model, String color, String cpuBrand,
            String cpuModel, String cpuArch, String ram, String storageType, String storageSize,
            String screenSize, String isEthernetSupported, String isWiFiSupported, String isBluetoothSupported,
            String osVersion, String osUpdate, String notes) {
        this.brand = new SimpleStringProperty(brand);
        this.color = new SimpleStringProperty(color);
        this.cpuArch = new SimpleStringProperty(cpuArch);
        this.cpuBrand = new SimpleStringProperty(cpuBrand);
        this.cpuModel = new SimpleStringProperty(cpuModel);
        this.id = new SimpleStringProperty(id);
        this.isBluetoothSupported = new SimpleStringProperty(isBluetoothSupported);
        this.isEthernetSupported = new SimpleStringProperty(isEthernetSupported);
        this.isWiFiSupported = new SimpleStringProperty(isWiFiSupported);
        this.model = new SimpleStringProperty(model);
        this.osUpdate = new SimpleStringProperty(osUpdate);
        this.osVersion = new SimpleStringProperty(osVersion);
        this.ram = new SimpleStringProperty(ram);
        this.screenSize = new SimpleStringProperty(screenSize);
        this.storageType = new SimpleStringProperty(storageType);
        this.storageSize = new SimpleStringProperty(storageSize);
        this.notes = new SimpleStringProperty(notes);
    }

    public String getBrand() {
        return brand.get();
    }

    public String getColor() {
        return color.get();
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

    public String getModel() {
        return model.get();
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

    public String getScreenSize() {
        return screenSize.get();
    }

    public String getStorageType() {
        return storageType.get();
    }

    public String getStorageSize() {
        return storageSize.get();
    }

    public String getNotes() {
        return notes.get();
    }
    
}
