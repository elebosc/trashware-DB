package it.unibo.trashware.entities;

import java.sql.Time;
import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name = "mansioni")
@IdClass(TaskId.class)
public class Task {

    @Id
    @Column(name = "CodiceFiscaleOperatore", nullable = false, length = 16, columnDefinition = "char")
    private String operatorFiscalCode;

    @Id
    @Column(name = "DataTurno", nullable = false)
    private LocalDate workShiftDate;

    @Id
    @Column(name = "OraInizioTurno", nullable = false)
    private Time workShiftStartTime;

    @Id
    @Column(name = "NumElenco", nullable = false)
    private Integer taskNumber;

    @Column(name = "Descrizione", nullable = false, length = 2000)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDOperazione")
    private it.unibo.trashware.entities.Operation operationID;

    public String getOperatorFiscalCode() {
        return operatorFiscalCode;
    }

    public void setOperatorFiscalCode(String opeatorFiscalCode) {
        this.operatorFiscalCode = opeatorFiscalCode;
    }

    public LocalDate getWorkShiftDate() {
        return workShiftDate;
    }

    public void setWorkShiftDate(LocalDate workShiftDate) {
        this.workShiftDate = workShiftDate;
    }

    public Time getWorkShiftStartTime() {
        return workShiftStartTime;
    }

    public void setWorkShiftStartTime(Time workShiftStartTime) {
        this.workShiftStartTime = workShiftStartTime;
    }

    public Integer getTaskNumber() {
        return taskNumber;
    }

    public void setTaskNumber(Integer taskNumber) {
        this.taskNumber = taskNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public it.unibo.trashware.entities.Operation getOperationID() {
        return operationID;
    }

    public void setOperationID(it.unibo.trashware.entities.Operation operationID) {
        this.operationID = operationID;
    }

}