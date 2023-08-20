package it.unibo.trashware.controller.impl;

import java.io.IOException;
import java.time.Instant;
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

public class WorkShiftsControllerImpl implements WorkShiftsController {

    private GenericDAO<Operator, String> operatorsDAO;
    private GenericDAO<WorkShift, WorkShiftId> workShiftsDAO;
    private GenericDAO<Task, TaskId> tasksDAO;
    private GenericDAO<Operation, String> operationsDAO;

    public WorkShiftsControllerImpl() throws IOException {
        this.operatorsDAO = new GenericDAOImpl<>(Operator.class);
        this.workShiftsDAO = new GenericDAOImpl<>(WorkShift.class);
        this.tasksDAO = new GenericDAOImpl<>(Task.class);
        this.operationsDAO = new GenericDAOImpl<>(Operation.class);
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
    public void registerWorkShift(String operatorFiscalCode, LocalDate date, Instant startTime, Instant endTime) {
        final WorkShiftId id = new WorkShiftId();
        id.setOperatorFiscalCode(operatorFiscalCode);
        id.setWorkShiftDate(date);
        id.setWorkShiftStartTime(startTime);
        final WorkShift workShift = new WorkShift();
        workShift.setId(id);
        workShift.setEndTime(endTime);
        // Work shift insertion
        this.workShiftsDAO.add(workShift);
    }

    @Override
    public void registerTask(String operatorFiscalCode, LocalDate date, Instant startTime, int taskNumber,
            String description, Optional<String> operationID) {
        final TaskId id = new TaskId();
        id.setOperatorFiscalCode(operatorFiscalCode);
        id.setWorkShiftDate(date);
        id.setWorkShiftStartTime(startTime);
        id.setTaskNumber(taskNumber);
        final Task task = new Task();
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
