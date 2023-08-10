package it.unibo.trashware.model.entities;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "turni_lavoro")
public class WorkShift {
    @EmbeddedId
    private WorkShiftId id;

    @MapsId("codiceFiscaleOperatore")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CodiceFiscaleOperatore", nullable = false)
    private it.unibo.trashware.model.entities.Operator operatorFiscalCode;

    @Column(name = "OraFine", nullable = false)
    private Instant endTime;

    public WorkShiftId getId() {
        return id;
    }

    public void setId(WorkShiftId id) {
        this.id = id;
    }

    public it.unibo.trashware.model.entities.Operator getOperatorFiscalCode() {
        return operatorFiscalCode;
    }

    public void setOperatorFiscalCode(it.unibo.trashware.model.entities.Operator operatorFiscalCode) {
        this.operatorFiscalCode = operatorFiscalCode;
    }

    public Instant getEndTime() {
        return endTime;
    }

    public void setEndTime(Instant endTime) {
        this.endTime = endTime;
    }

}