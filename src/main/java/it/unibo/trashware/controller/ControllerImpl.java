package it.unibo.trashware.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

import it.unibo.trashware.services.api.OperationsService;
import it.unibo.trashware.services.impl.OperationsServiceImpl;

/**
 * This class implements the controller of the application.
 */
public class ControllerImpl implements Controller {
    
    private OperationsService opService;

    /**
     * Creates a new controller.
     * @throws IOException
     */
    public ControllerImpl() throws IOException {
        this.opService = new OperationsServiceImpl();
    }

    @Override
    public void addRepresentative(String fiscalCode, String name, String surname, String birthplace, LocalDate birthday,
            String residenceCity, String residenceCAP, String residenceProvince, String residenceStreet,
            int residenceStreetNumber, String telephoneNumber1, Optional<String> telephoneNumber2,
            Optional<String> faxNumber, Optional<String> email) {
        opService.addRepresentative(
            fiscalCode, name, surname, birthplace, birthday, residenceCity, residenceCAP, residenceProvince,
            residenceStreet, residenceStreetNumber, telephoneNumber1, telephoneNumber2, faxNumber, email
        );
    }
}
