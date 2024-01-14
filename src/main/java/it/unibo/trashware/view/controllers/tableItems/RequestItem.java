package it.unibo.trashware.view.controllers.tableItems;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class RequestItem {

    private StringProperty requestID;

    private StringProperty representativeName;

    private StringProperty societyName;

    private StringProperty requestType;

    private StringProperty motivation;

    private StringProperty devicesList;

    private StringProperty date;

    private StringProperty deadline;

    private StringProperty priority;

    private StringProperty telephoneNumbers;

    private StringProperty faxNumber;

    private StringProperty email;

    private StringProperty notes;

    public RequestItem(String requestID, String representativeName, String societyName,
            String requestType, String motivation, String devicesList, String date,
            String deadline, String priority, String telephoneNumbers, String faxNumber, String email, String notes
        ) {
        this.requestID = new SimpleStringProperty(requestID);
        this.representativeName = new SimpleStringProperty(representativeName);
        this.societyName = new SimpleStringProperty(societyName);
        this.requestType = new SimpleStringProperty(requestType);
        this.motivation = new SimpleStringProperty(motivation);
        this.devicesList = new SimpleStringProperty(devicesList);
        this.date = new SimpleStringProperty(date);
        this.deadline = new SimpleStringProperty(deadline);
        this.priority = new SimpleStringProperty(priority);
        this.telephoneNumbers = new SimpleStringProperty(telephoneNumbers);
        this.faxNumber = new SimpleStringProperty(faxNumber);
        this.email = new SimpleStringProperty(email);
        this.notes = new SimpleStringProperty(notes);
    }

    public String getRequestID() {
        return requestID.get();
    }

    public String getRepresentativeName() {
        return representativeName.get();
    }

    public String getSocietyName() {
        return societyName.get();
    }

    public String getRequestType() {
        return requestType.get();
    }

    public String getMotivation() {
        return motivation.get();
    }

    public String getDevicesList() {
        return devicesList.get();
    }

    public String getDate() {
        return date.get();
    }

    public String getDeadline() {
        return deadline.get();
    }

    public String getPriority() {
        return priority.get();
    }

    public String getTelephoneNumbers() {
        return telephoneNumbers.get();
    }

    public String getFaxNumber() {
        return faxNumber.get();
    }

    public String getEmail() {
        return email.get();
    }

    public String getNotes() {
        return notes.get();
    }

}
