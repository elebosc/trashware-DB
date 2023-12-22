package it.unibo.trashware.entities;

import org.hibernate.Hibernate;

import java.io.Serializable;
import java.sql.Time;
import java.time.LocalDate;
import java.util.Objects;

public class WorkShiftId implements Serializable {
    private static final long serialVersionUID = -2512851027105748895L;

    private String operatorFiscalCode;

    private LocalDate workShiftDate;

    private Time workShiftStartTime;

    public String getOperatorFiscalCode() {
        return operatorFiscalCode;
    }

    public void setOperatorFiscalCode(String operatorFiscalCode) {
        this.operatorFiscalCode = operatorFiscalCode;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        WorkShiftId entity = (WorkShiftId) o;
        return Objects.equals(this.workShiftDate, entity.workShiftDate) &&
                Objects.equals(this.workShiftStartTime, entity.workShiftStartTime) &&
                Objects.equals(this.operatorFiscalCode, entity.operatorFiscalCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(workShiftDate, workShiftStartTime, operatorFiscalCode);
    }

}