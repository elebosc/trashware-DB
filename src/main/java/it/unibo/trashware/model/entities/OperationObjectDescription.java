package it.unibo.trashware.model.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "riassunti_dispositivi")
public class OperationObjectDescription {
    @EmbeddedId
    private OperationObjectDescriptionId id;

    @MapsId("iDOperazione")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "IDOperazione", nullable = false)
    private it.unibo.trashware.model.entities.Operation operationID;

    @Column(name = "Tipologia", nullable = false, length = 256)
    private String type;

    @Column(name = "`Quantità`", nullable = false)
    private Integer quantity;

    @Column(name = "Note", length = 2000)
    private String notes;

    public OperationObjectDescriptionId getId() {
        return id;
    }

    public void setId(OperationObjectDescriptionId id) {
        this.id = id;
    }

    public it.unibo.trashware.model.entities.Operation getOperationID() {
        return operationID;
    }

    public void setOperationID(it.unibo.trashware.model.entities.Operation operationID) {
        this.operationID = operationID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

}