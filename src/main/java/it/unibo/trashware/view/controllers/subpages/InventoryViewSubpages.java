package it.unibo.trashware.view.controllers.subpages;

public enum InventoryViewSubpages {

    DESKTOPS_VIEW("desktop_pc_view.fxml"),
    LAPTOPS_VIEW("laptops_view.fxml"),
    MONITORS_VIEW("monitors_view.fxml"),
    OTHER_PERIPHERALS_VIEW("other_peripherals_view.fxml"),
    CPUS_VIEW("cpus_view.fxml"),
    RAM_MODULES_VIEW("ram_modules_view.fxml"),
    STORAGE_DEVICES_VIEW("storage_devices_view.fxml"),
    CHASSIS_VIEW("chassis_view.fxml"),
    OTHER_COMPONENTS_VIEW("other_components_view.fxml");
    
    private static final String DIR = "it/unibo/trashware/layouts/subpages/";
    private final String fxmlFileName;

    private InventoryViewSubpages(final String fxmlFileName) {
        this.fxmlFileName = fxmlFileName;
    }

    public String getFXMLFilePath() {
        return DIR + this.fxmlFileName;
    }

}
