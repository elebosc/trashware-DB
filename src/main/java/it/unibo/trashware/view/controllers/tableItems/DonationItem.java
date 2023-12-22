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

    public DonationItem(String donationID, String representativeName, String societyName,
            String date, String telephoneNumbers,
            String faxNumber, String email) {
        this.donationID = new SimpleStringProperty(donationID);
        this.representativeName = new SimpleStringProperty(representativeName);
        this.societyName = new SimpleStringProperty(societyName);
        this.date = new SimpleStringProperty(date);
        this.telephoneNumbers = new SimpleStringProperty(telephoneNumbers);
        this.faxNumber = new SimpleStringProperty(faxNumber);
        this.email = new SimpleStringProperty(email);
    }

    public String getDonationID() {
        return donationID.get();
    }

    public void setDonationID(StringProperty donationID) {
        this.donationID = donationID;
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

    public String getDate() {
        return date.get();
    }

    public void setDate(StringProperty date) {
        this.date = date;
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
