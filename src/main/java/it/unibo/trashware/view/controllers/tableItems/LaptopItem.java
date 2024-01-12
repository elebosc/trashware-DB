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

    private StringProperty storage01Type;

    private StringProperty storage01Size;

    private StringProperty storage02Type;

    private StringProperty storage02Size;

    private StringProperty notes;

    public LaptopItem(String id, String brand, String model, String color, String cpuBrand,
            String cpuModel, String cpuArch, String ram, String storage01Type, String storage01Size, String storage02Type, String storage02Size,
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
        this.storage01Type = new SimpleStringProperty(storage01Type);
        this.storage01Size = new SimpleStringProperty(storage01Size);
        this.storage02Type = new SimpleStringProperty(storage02Type);
        this.storage02Size = new SimpleStringProperty(storage02Size);
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

    public String getStorage01Type() {
        return storage01Type.get();
    }

    public String getStorage01Size() {
        return storage01Size.get();
    }

    public String getStorage02Type() {
        return storage02Type.get();
    }

    public String getStorage02Size() {
        return storage02Size.get();
    }

    public String getNotes() {
        return notes.get();
    }
    
}
