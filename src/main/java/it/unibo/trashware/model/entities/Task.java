package it.unibo.trashware.model.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "mansioni")
public class Task {
    @EmbeddedId
    private TaskId id;

    @MapsId
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumns({
            @JoinColumn(name = "CodiceFiscaleOperatore", referencedColumnName = "CodiceFiscaleOperatore", nullable = false),
            @JoinColumn(name = "DataTurno", referencedColumnName = "Data", nullable = false),
            @JoinColumn(name = "OraInizioTurno", referencedColumnName = "OraInizio", nullable = false)
    })
    private it.unibo.trashware.model.entities.WorkShift workShift;

    @Column(name = "Descrizione", nullable = false, length = 2000)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDOperazione")
    private it.unibo.trashware.model.entities.Operation operationID;

    public TaskId getId() {
        return id;
    }

    public void setId(TaskId id) {
        this.id = id;
    }

    public it.unibo.trashware.model.entities.WorkShift getWorkShift() {
        return workShift;
    }

    public void setWorkShift(it.unibo.trashware.model.entities.WorkShift workShift) {
        this.workShift = workShift;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public it.unibo.trashware.model.entities.Operation getOperationID() {
        return operationID;
    }

    public void setOperationID(it.unibo.trashware.model.entities.Operation operationID) {
        this.operationID = operationID;
    }

}