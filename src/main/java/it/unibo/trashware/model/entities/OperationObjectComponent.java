package it.unibo.trashware.model.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "oggetto_componente")
public class OperationObjectComponent {
    @EmbeddedId
    private OperationObjectComponentId id;

    @MapsId("iDComponente")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "IDComponente", nullable = false)
    private it.unibo.trashware.model.entities.Component componentID;

    @MapsId("iDOperazione")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "IDOperazione", nullable = false)
    private it.unibo.trashware.model.entities.Operation operationID;

    public OperationObjectComponentId getId() {
        return id;
    }

    public void setId(OperationObjectComponentId id) {
        this.id = id;
    }

    public it.unibo.trashware.model.entities.Component getComponentID() {
        return componentID;
    }

    public void setComponentID(it.unibo.trashware.model.entities.Component componentID) {
        this.componentID = componentID;
    }

    public it.unibo.trashware.model.entities.Operation getOperationID() {
        return operationID;
    }

    public void setOperationID(it.unibo.trashware.model.entities.Operation operationID) {
        this.operationID = operationID;
    }

}