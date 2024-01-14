package it.unibo.trashware.view.controllers.tableItems;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class DonationItem {
   
    private StringProperty donationID;

    private StringProperty representativeName;

    private StringProperty societyName;

    private StringProperty date;

    private StringProperty telephoneNumbers;

    private StringProperty faxNumber;

    private StringProperty email;

    private StringProperty devicesList;

    private StringProperty notes;

    public DonationItem(String donationID, String representativeName, String societyName,
            String date, String devicesList, String telephoneNumbers, String faxNumber, String email, String notes
        ) {
        this.donationID = new SimpleStringProperty(donationID);
        this.representativeName = new SimpleStringProperty(representativeName);
        this.societyName = new SimpleStringProperty(societyName);
        this.date = new SimpleStringProperty(date);
        this.devicesList = new SimpleStringProperty(devicesList);
        this.telephoneNumbers = new SimpleStringProperty(telephoneNumbers);
        this.faxNumber = new SimpleStringProperty(faxNumber);
        this.email = new SimpleStringProperty(email);
        this.notes = new SimpleStringProperty(notes);
    }

    public String getDonationID() {
        return donationID.get();
    }

    public String getRepresentativeName() {
        return representativeName.get();
    }

    public String getSocietyName() {
        return societyName.get();
    }

    public String getDate() {
        return date.get();
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

    public String getDevicesList() {
        return devicesList.get();
    }

    public String getNotes() {
        return notes.get();
    }

}
