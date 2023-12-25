package it.unibo.trashware.view.controllers.tableItems;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class DonationItem {
   
    private StringProperty donationID;

    private StringProperty representativeName;

    private StringProperty societyName;

    private StringProperty numComponents;

    private StringProperty numDesktops;

    private StringProperty numKeyboards;

    private StringProperty numLaptops;

    private StringProperty numMonitors;

    private StringProperty numMouse;

    private StringProperty numOtherPeripherals;

    private StringProperty date;

    private StringProperty telephoneNumbers;

    private StringProperty faxNumber;

    private StringProperty email;

    public DonationItem(String donationID, String representativeName, String societyName,
            String numDesktops, String numLaptops, String numMonitors, String numKeyboards,
            String numMouse, String numOtherPeripherals, String numComponents,
            String date, String telephoneNumbers, String faxNumber, String email
        ) {
        this.donationID = new SimpleStringProperty(donationID);
        this.representativeName = new SimpleStringProperty(representativeName);
        this.societyName = new SimpleStringProperty(societyName);
        this.numComponents = new SimpleStringProperty(numComponents);
        this.numDesktops = new SimpleStringProperty(numDesktops);
        this.numKeyboards = new SimpleStringProperty(numKeyboards);
        this.numLaptops = new SimpleStringProperty(numLaptops);
        this.numMonitors = new SimpleStringProperty(numMonitors);
        this.numMouse = new SimpleStringProperty(numMouse);
        this.numOtherPeripherals = new SimpleStringProperty(numOtherPeripherals);
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

    public String getNumComponents() {
        return numComponents.get();
    }

    public void setNumComponents(StringProperty numComponents) {
        this.numComponents = numComponents;
    }

    public String getNumDesktops() {
        return numDesktops.get();
    }

    public void setNumDesktops(StringProperty numDesktops) {
        this.numDesktops = numDesktops;
    }

    public String getNumKeyboards() {
        return numKeyboards.get();
    }

    public void setNumKeyboards(StringProperty numKeyboards) {
        this.numKeyboards = numKeyboards;
    }

    public String getNumLaptops() {
        return numLaptops.get();
    }

    public void setNumLaptops(StringProperty numLaptops) {
        this.numLaptops = numLaptops;
    }

    public String getNumMonitors() {
        return numMonitors.get();
    }

    public void setNumMonitors(StringProperty numMonitors) {
        this.numMonitors = numMonitors;
    }

    public String getNumMouse() {
        return numMouse.get();
    }

    public void setNumMouse(StringProperty numMouse) {
        this.numMouse = numMouse;
    }

    public String getNumOtherPeripherals() {
        return numOtherPeripherals.get();
    }

    public void setNumOtherPeripherals(StringProperty numOtherPeripherals) {
        this.numOtherPeripherals = numOtherPeripherals;
    }


}
