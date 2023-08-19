package it.unibo.trashware.view;

public enum ScenesConfig {
    
    MAIN("main.fxml");

    private static final String DIR = "layouts/";
    private final String fxmlFileName;

    private ScenesConfig(final String fxmlFileName) {
        this.fxmlFileName = fxmlFileName;
    }

    public String getFXMLFilePath() {
        return DIR + this.fxmlFileName;
    }

}
