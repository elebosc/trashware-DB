package it.unibo.trashware.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;

@Embeddable
public class TaskId implements Serializable {
    private static final long serialVersionUID = 7935673349876242184L;
    @Column(name = "CodiceFiscaleOperatore", nullable = false, length = 16)
    private String operatorFiscalCode;

    @Column(name = "DataTurno", nullable = false)
    private LocalDate workShiftDate;

    @Column(name = "OraInizioTurno", nullable = false)
    private Instant workShiftStartTime;

    @Column(name = "NumElenco", nullable = false)
    private Integer taskNumber;

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

    public Instant getWorkShiftStartTime() {
        return workShiftStartTime;
    }

    public void setWorkShiftStartTime(Instant workShiftStartTime) {
        this.workShiftStartTime = workShiftStartTime;
    }

    public Integer getTaskNumber() {
        return taskNumber;
    }

    public void setTaskNumber(Integer taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        TaskId entity = (TaskId) o;
        return Objects.equals(this.workShiftStartTime, entity.workShiftStartTime) &&
                Objects.equals(this.workShiftDate, entity.workShiftDate) &&
                Objects.equals(this.taskNumber, entity.taskNumber) &&
                Objects.equals(this.operatorFiscalCode, entity.operatorFiscalCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(workShiftStartTime, workShiftDate, taskNumber, operatorFiscalCode);
    }

}