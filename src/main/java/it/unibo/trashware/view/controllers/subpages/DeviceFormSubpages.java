package it.unibo.trashware.view.controllers.subpages;

public enum DeviceFormSubpages {
    
    MONITOR_FORM("monitor_form.fxml"),
    OTHER_PERIPHERAL_FORM("other_peripheral_form.fxml"),
    CPU_FORM("cpu_form.fxml"),
    RAM_FORM("ram_form.fxml"),
    MASS_STORAGE_FORM("mass_storage_form.fxml"),
    CHASSIS_FORM("chassis_form.fxml"),
    OTHER_COMPONENT_FORM("other_component_form.fxml"),
    DESKTOP_PC_FORM("desktop_pc_form.fxml"),
    LAPTOP_FORM("laptop_form.fxml");

    private static final String DIR = "it/unibo/trashware/layouts/subpages/";
    private final String fxmlFileName;

    private DeviceFormSubpages(final String fxmlFileName) {
        this.fxmlFileName = fxmlFileName;
    }

    public String getFXMLFilePath() {
        return DIR + this.fxmlFileName;
    }

}
