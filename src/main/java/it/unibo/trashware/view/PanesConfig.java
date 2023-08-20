package it.unibo.trashware.view;

public enum PanesConfig {
    
    DONATION_FORM("donation_form.fxml");

    private static final String DIR = "layouts/";
    private final String fxmlFileName;

    private PanesConfig(final String fxmlFileName) {
        this.fxmlFileName = fxmlFileName;
    }

    public String getFXMLFilePath() {
        return DIR + this.fxmlFileName;
    }

}
