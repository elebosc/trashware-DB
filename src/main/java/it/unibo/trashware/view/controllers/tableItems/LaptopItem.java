package it.unibo.trashware.view.controllers.tableItems;

import javafx.beans.property.StringProperty;

public class LaptopItem {

    private StringProperty brand;

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

    public String getBrand() {
        return brand.get();
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
    
}
