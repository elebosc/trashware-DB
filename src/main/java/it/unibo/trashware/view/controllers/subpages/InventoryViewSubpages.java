package it.unibo.trashware.view.controllers.subpages;

public enum InventoryViewSubpages {

    LAPTOPS_VIEW("laptops_view.fxml");
    
    private static final String DIR = "layouts/subpages/";
    private final String fxmlFileName;

    private InventoryViewSubpages(final String fxmlFileName) {
        this.fxmlFileName = fxmlFileName;
    }

    public String getFXMLFilePath() {
        return DIR + this.fxmlFileName;
    }

}
