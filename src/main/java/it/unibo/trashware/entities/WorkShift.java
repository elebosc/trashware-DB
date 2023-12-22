package it.unibo.trashware.entities;

import jakarta.persistence.*;

import java.sql.Time;
import java.time.LocalDate;

@Entity
@Table(name = "turni_lavoro")
@IdClass(WorkShiftId.class)
public class WorkShift {

    @Id
    @Column(name = "CodiceFiscaleOperatore", nullable = false, length = 10, columnDefinition = "char")
    private String operatorFiscalCode;

    @Id
    @Column(name = "Data", nullable = false)
    private LocalDate workShiftDate;

    @Id
    @Column(name = "OraInizio", nullable = false)
    private Time workShiftStartTime;

    @Column(name = "OraFine", nullable = false)
    private Time workShiftEndTime;

    public String getOperatorFiscalCode() {
        return operatorFiscalCode;
    }

    public void setOperatorFiscalCode(String operator) {
        this.operatorFiscalCode = operator;
    }

    public LocalDate getWorkShiftDate() {
        return workShiftDate;
    }

    public void setWorkShiftDate(LocalDate date) {
        this.workShiftDate = date;
    }

    public Time getWorkShiftStartTime() {
        return workShiftStartTime;
    }

    public void setWorkShiftStartTime(Time startTime) {
        this.workShiftStartTime = startTime;
    }

    public Time getWorkShiftEndTime() {
        return workShiftEndTime;
    }

    public void setWorkShiftEndTime(Time endTime) {
        this.workShiftEndTime = endTime;
    }

}