package it.unibo.trashware.view.controllers.subpages;

public enum DeviceFormSubpages {
    
    MONITOR_FORM("monitor_form.fxml"),
    OTHER_PERIPHERAL_FORM("other_peripheral_form.fxml");

    private static final String DIR = "layouts/subpages/";
    private final String fxmlFileName;

    private DeviceFormSubpages(final String fxmlFileName) {
        this.fxmlFileName = fxmlFileName;
    }

    public String getFXMLFilePath() {
        return DIR + this.fxmlFileName;
    }

}
