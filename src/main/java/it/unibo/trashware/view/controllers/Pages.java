package it.unibo.trashware.view.controllers;

public enum Pages {
    
    DONATION_FORM("donation_form.fxml"),
    REQUEST_FORM("request_form.fxml"),
    OPERATOR_FORM("operator_form.fxml"),
    WORKSHIFT_FORM("workshift_form.fxml"),
    DONATIONS_VIEW("donations_view.fxml"),
    REQUESTS_VIEW("requests_view.fxml"),
    UPDATE_REQUEST_STATUS_FORM("update_request_status_form.fxml"),
    DEVICE_FORM("device_form.fxml"),
    INVENTORY_VIEW("inventory_view.fxml"),
    DEVICE_TO_OPERATION_ASSOCIATION_FORM("device_to_operation_association_form.fxml"),
    DEVICE_TO_PC_ASSOCIATION_FORM("device_to_pc_association_form.fxml");

    private static final String DIR = "it/unibo/trashware/layouts/";
    private final String fxmlFileName;

    private Pages(final String fxmlFileName) {
        this.fxmlFileName = fxmlFileName;
    }

    public String getFXMLFilePath() {
        return DIR + this.fxmlFileName;
    }

}
