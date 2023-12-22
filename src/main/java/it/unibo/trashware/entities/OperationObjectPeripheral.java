package it.unibo.trashware.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "oggetto_periferica")
public class OperationObjectPeripheral {
    @EmbeddedId
    private OperationObjectPeripheralId id;

    @MapsId("iDPeriferica")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "IDPeriferica", nullable = false)
    private it.unibo.trashware.entities.Peripheral peripheralID;

    @MapsId("iDOperazione")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "IDOperazione", nullable = false)
    private it.unibo.trashware.entities.Operation operationID;

    public OperationObjectPeripheralId getId() {
        return id;
    }

    public void setId(OperationObjectPeripheralId id) {
        this.id = id;
    }

    public it.unibo.trashware.entities.Peripheral getPeripheralID() {
        return peripheralID;
    }

    public void setPeripheralID(it.unibo.trashware.entities.Peripheral peripheralID) {
        this.peripheralID = peripheralID;
    }

    public it.unibo.trashware.entities.Operation getOperationID() {
        return operationID;
    }

    public void setOperationID(it.unibo.trashware.entities.Operation operationID) {
        this.operationID = operationID;
    }

}