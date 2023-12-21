package it.unibo.trashware.controller.impl;

import java.io.IOException;
import java.sql.Time;
import java.time.LocalDate;
import java.util.Optional;

import it.unibo.trashware.controller.api.WorkShiftsController;
import it.unibo.trashware.model.dao.GenericDAO;
import it.unibo.trashware.model.dao.GenericDAOImpl;
import it.unibo.trashware.model.entities.Operation;
import it.unibo.trashware.model.entities.Operator;
import it.unibo.trashware.model.entities.Task;
import it.unibo.trashware.model.entities.TaskId;
import it.unibo.trashware.model.entities.WorkShift;
import it.unibo.trashware.model.entities.WorkShiftId;
import it.unibo.trashware.model.provider.ConnectionProvider;
import it.unibo.trashware.model.provider.ConnectionProviderImpl;
import jakarta.persistence.EntityManager;

public class WorkShiftsControllerImpl implements WorkShiftsController {

    private EntityManager em;

    private GenericDAO<Operator, String> operatorsDAO;
    private GenericDAO<WorkShift, WorkShiftId> workShiftsDAO;
    private GenericDAO<Task, TaskId> tasksDAO;
    private GenericDAO<Operation, String> operationsDAO;

    public WorkShiftsControllerImpl() throws IOException {
        // Connect to the database
        final ConnectionProvider provider = new ConnectionProviderImpl();
        final Optional<EntityManager> response = provider.getConnection();
        if (response.isEmpty()) {
            throw new IOException("Error: work shifts controller could not establish a connection with the database.");
        }
        this.em = response.get();
        this.operatorsDAO = new GenericDAOImpl<>(this.em, Operator.class);
        this.workShiftsDAO = new GenericDAOImpl<>(this.em, WorkShift.class);
        this.tasksDAO = new GenericDAOImpl<>(this.em, Task.class);
        this.operationsDAO = new GenericDAOImpl<>(this.em, Operation.class);
    }

    @Override
    public void addOperator(String fiscalCode, String name, String surname, String birthplace, LocalDate birthday,
            String residenceCity, String residenceCAP, String residenceProvince, String residenceStreet,
            int residenceStreetNumber, String telephoneNumber1, Optional<String> telephoneNumber2,
            Optional<String> email) {
        final Operator operator = new Operator();
        operator.setFiscalCode(fiscalCode);
        operator.setName(name);
        operator.setSurname(surname);
        operator.setBirthplace(birthplace);
        operator.setBirthday(birthday);
        operator.setResidenceCity(residenceCity);
        operator.setResidenceCAP(residenceCAP);
        operator.setResidenceProvince(residenceProvince);
        operator.setResidenceStreet(residenceStreet);
        operator.setResidenceStreetNumber(Integer.valueOf(residenceStreetNumber));
        operator.setTelephoneNumber1(telephoneNumber1);
        telephoneNumber2.ifPresent((number) -> operator.setTelephoneNumber2(number));
        email.ifPresent((address) -> operator.setEmail(address));
        // Operator insertion
        this.operatorsDAO.add(operator);
    }

    @Override
    public void registerWorkShift(String operatorFiscalCode, LocalDate date, Time startTime, Time endTime) {
        final WorkShift workShift = new WorkShift();
        if (this.operatorsDAO.getByID(operatorFiscalCode).isPresent()) {
            workShift.setOperatorFiscalCode(operatorFiscalCode);
        } else {
            throw new IllegalArgumentException("An operator with such fiscal code does not exist.");
        };
        workShift.setWorkShiftDate(date);
        workShift.setWorkShiftStartTime(startTime);
        workShift.setWorkShiftEndTime(endTime);
        // Work shift insertion
        this.workShiftsDAO.add(workShift);
    }

    @Override
    public void registerTask(String operatorFiscalCode, LocalDate date, Time startTime, int taskNumber,
            String description, Optional<String> operationID) {
        final Task task = new Task();
        final WorkShiftId workShiftID = new WorkShiftId();
        workShiftID.setOperatorFiscalCode(operatorFiscalCode);
        workShiftID.setWorkShiftDate(date);
        workShiftID.setWorkShiftStartTime(startTime);
        if(this.workShiftsDAO.getByID(workShiftID).isPresent()) {
            task.setOperatorFiscalCode(operatorFiscalCode);
            task.setWorkShiftDate(date);
            task.setWorkShiftStartTime(startTime);
            task.setTaskNumber(taskNumber);
        } else {
            throw new IllegalArgumentException("A work shift at the specified date and time has not been registred.");
        }
        task.setDescription(description);
        if (operationID.isPresent()) {
            final Optional<Operation> searchedOp = this.operationsDAO.getByID(operationID.get());
            searchedOp.ifPresentOrElse(
                op -> task.setOperationID(op),
                () -> new IllegalArgumentException("Error: an operation with such ID does not exist.")
            );
        }
        // Task insertion
        this.tasksDAO.add(task);
    }
    
}
