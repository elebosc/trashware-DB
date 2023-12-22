package it.unibo.trashware.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class OperationObjectPeripheralId implements Serializable {
    private static final long serialVersionUID = -2153242838408291998L;
    @Column(name = "IDPeriferica", nullable = false, length = 10)
    private String peripheralID;

    @Column(name = "IDOperazione", nullable = false, length = 10)
    private String operationID;

    public String getPeripheralID() {
        return peripheralID;
    }

    public void setPeripheralID(String peripheralID) {
        this.peripheralID = peripheralID;
    }

    public String getOperationID() {
        return operationID;
    }

    public void setOperationID(String operationID) {
        this.operationID = operationID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        OperationObjectPeripheralId entity = (OperationObjectPeripheralId) o;
        return Objects.equals(this.peripheralID, entity.peripheralID) &&
                Objects.equals(this.operationID, entity.operationID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(peripheralID, operationID);
    }

}