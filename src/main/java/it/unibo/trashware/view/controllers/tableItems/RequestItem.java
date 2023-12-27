package it.unibo.trashware.view.controllers.tableItems;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class RequestItem {

    private StringProperty requestID;

    private StringProperty representativeName;

    private StringProperty societyName;

    private StringProperty requestType;

    private StringProperty motivation;

    private StringProperty details;

    private StringProperty date;

    private StringProperty deadline;

    private StringProperty priority;

    private StringProperty telephoneNumbers;

    private StringProperty faxNumber;

    private StringProperty email;

    public RequestItem(String requestID, String representativeName, String societyName,
            String requestType, String motivation, String details, String date,
            String deadline, String priority, String telephoneNumbers, String faxNumber, String email 
        ) {
        this.requestID = new SimpleStringProperty(requestID);
        this.representativeName = new SimpleStringProperty(representativeName);
        this.societyName = new SimpleStringProperty(societyName);
        this.requestType = new SimpleStringProperty(requestType);
        this.motivation = new SimpleStringProperty(motivation);
        this.details = new SimpleStringProperty(details);
        this.date = new SimpleStringProperty(date);
        this.deadline = new SimpleStringProperty(deadline);
        this.priority = new SimpleStringProperty(priority);
        this.telephoneNumbers = new SimpleStringProperty(telephoneNumbers);
        this.faxNumber = new SimpleStringProperty(faxNumber);
        this.email = new SimpleStringProperty(email);
    }

    public String getRequestID() {
        return requestID.get();
    }

    public void setRequestID(StringProperty requestID) {
        this.requestID = requestID;
    }

    public String getRepresentativeName() {
        return representativeName.get();
    }

    public void setRepresentativeName(StringProperty representativeName) {
        this.representativeName = representativeName;
    }

    public String getSocietyName() {
        return societyName.get();
    }

    public void setSocietyName(StringProperty societyName) {
        this.societyName = societyName;
    }

    public String getRequestType() {
        return requestType.get();
    }

    public void setRequestType(StringProperty requestType) {
        this.requestType = requestType;
    }

    public String getMotivation() {
        return motivation.get();
    }

    public void setMotivation(StringProperty motivation) {
        this.motivation = motivation;
    }

    public String getDetails() {
        return details.get();
    }

    public void setDetails(StringProperty details) {
        this.details = details;
    }

    public String getDate() {
        return date.get();
    }

    public void setDate(StringProperty date) {
        this.date = date;
    }

    public String getDeadline() {
        return deadline.get();
    }

    public void setDeadline(StringProperty deadline) {
        this.deadline = deadline;
    }

    public String getPriority() {
        return priority.get();
    }

    public void setPriority(StringProperty priority) {
        this.priority = priority;
    }

    public String getTelephoneNumbers() {
        return telephoneNumbers.get();
    }

    public void setTelephoneNumbers(StringProperty telephoneNumbers) {
        this.telephoneNumbers = telephoneNumbers;
    }

    public String getFaxNumber() {
        return faxNumber.get();
    }

    public void setFaxNumber(StringProperty faxNumber) {
        this.faxNumber = faxNumber;
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(StringProperty email) {
        this.email = email;
    }
}
