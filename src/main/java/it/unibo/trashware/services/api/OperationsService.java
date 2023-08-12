package it.unibo.trashware.services.api;

import java.time.LocalDate;
import java.util.Optional;

public interface OperationsService {

    void addDonation(String id, LocalDate date, Optional<String> notes, String representativeID);

    void addRequest(String id, String type, String reason, LocalDate date,
            LocalDate deadline, int priorityLevel, Optional<String> notes, String representativeID);
    
    void addRepresentative(String fiscalCode, String name, String surname, String birthplace,
            LocalDate birthday, String residenceCity, String residenceCAP, String residenceProvince,
            String residenceStreet, int residenceStreetNumber, String telephoneNumber1,
            Optional<String> telephoneNumber2, Optional<String> faxNumber, Optional<String> email);
    
    void addSociety(String VATNumber, String fiscalCode, String name, String registeredOfficeCity,
            String registeredOfficeCAP, String registeredOfficeProvince, String registeredOfficeStreet,
            int registeredOfficeStreetNumber);

    void addObjectDescription(String operationID, int lineNumber, String type, int quantity, Optional<String> notes);

}