package it.unibo.trashware.view;

public enum PagesConfig {
    
    DONATION_FORM("donation_form.fxml"),
    REQUEST_FORM("request_form.fxml");

    private static final String DIR = "layouts/";
    private final String fxmlFileName;

    private PagesConfig(final String fxmlFileName) {
        this.fxmlFileName = fxmlFileName;
    }

    public String getFXMLFilePath() {
        return DIR + this.fxmlFileName;
    }

}
