package it.unibo.trashware.model.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "oggetto_pc")
public class OperationObjectPC {
    @EmbeddedId
    private OperationObjectPCId id;

    @MapsId("idpc")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "IDPC", nullable = false)
    private it.unibo.trashware.model.entities.PC pcID;

    @MapsId("iDOperazione")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "IDOperazione", nullable = false)
    private it.unibo.trashware.model.entities.Operation operationID;

    public OperationObjectPCId getId() {
        return id;
    }

    public void setId(OperationObjectPCId id) {
        this.id = id;
    }

    public it.unibo.trashware.model.entities.PC getPcID() {
        return pcID;
    }

    public void setPcID(it.unibo.trashware.model.entities.PC pcID) {
        this.pcID = pcID;
    }

    public it.unibo.trashware.model.entities.Operation getOperationID() {
        return operationID;
    }

    public void setOperationID(it.unibo.trashware.model.entities.Operation operationID) {
        this.operationID = operationID;
    }

}