package it.unibo.trashware.view.controllers.tableItems;

import javafx.beans.property.StringProperty;

public class DonationItem {
   
    private StringProperty donationID;

    private StringProperty representativeName;

    private StringProperty societyName;

    private StringProperty articlesDescription;

    private StringProperty date;

    private StringProperty telephoneNumbers;

    private StringProperty faxNumber;

    private StringProperty email;

    public DonationItem(String donationID, String representativeName, String societyName,
            String articlesDescription, String date, String telephoneNumbers,
            String faxNumber, String email) {
        this.donationID.setValue(donationID);
        this.representativeName.setValue(representativeName);
        this.societyName.setValue(societyName);
        this.articlesDescription.setValue(articlesDescription);
        this.date.setValue(date);
        this.telephoneNumbers.setValue(telephoneNumbers);
        this.faxNumber.setValue(faxNumber);
        this.email.setValue(email);
    }


}
