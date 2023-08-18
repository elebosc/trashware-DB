package it.unibo.trashware.services.api;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Optional;

public interface WorkShiftsService {
    
    /**
     * Stores data about a new operator.
     * @param fiscalCode
     * @param name
     * @param surname
     * @param birthplace
     * @param birthday
     * @param residenceCity
     * @param residenceCAP
     * @param residenceProvince
     * @param residenceStreet
     * @param residenceStreetNumber
     * @param telephoneNumber1
     * @param telephoneNumber2
     * @param email
     */
    void addOperator(String fiscalCode, String name, String surname, String birthplace,
            LocalDate birthday, String residenceCity, String residenceCAP, String residenceProvince,
            String residenceStreet, int residenceStreetNumber, String telephoneNumber1,
            Optional<String> telephoneNumber2, Optional<String> email);
    
    /**
     * Stores data about a new work shift.
     * @param operatorFiscalCode
     * @param date
     * @param startTime
     * @param endTime
     */
    void registerWorkShift(String operatorFiscalCode, LocalDate date, Instant startTime, Instant endTime);

    /**
     * Stores data about a new task done in a work shift.
     * @param operatorFiscalCode
     * @param date
     * @param startTime
     * @param taskNumber
     * @param description
     * @param operationID
     */
    void registerTask(String operatorFiscalCode, LocalDate date, Instant startTime, int taskNumber,
            String description, Optional<String> operationID);

}
