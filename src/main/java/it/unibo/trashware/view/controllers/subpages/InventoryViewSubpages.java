package it.unibo.trashware.view.controllers.subpages;

public enum InventoryViewSubpages {

    LAPTOPS_VIEW("laptops_view.fxml"),
    MONITORS_VIEW("monitors_view.fxml"),
    OTHER_PERIPHERALS_VIEW("other_peripherals_view.fxml"),
    CPUS_VIEW("cpus_view.fxml"),
    RAM_MODULES_VIEW("ram_modules_view.fxml");
    
    private static final String DIR = "layouts/subpages/";
    private final String fxmlFileName;

    private InventoryViewSubpages(final String fxmlFileName) {
        this.fxmlFileName = fxmlFileName;
    }

    public String getFXMLFilePath() {
        return DIR + this.fxmlFileName;
    }

}
