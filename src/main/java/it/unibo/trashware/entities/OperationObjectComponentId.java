package it.unibo.trashware.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class OperationObjectComponentId implements Serializable {
    private static final long serialVersionUID = 5652926893960953850L;
    @Column(name = "IDComponente", nullable = false, length = 10)
    private String componentID;

    @Column(name = "IDOperazione", nullable = false, length = 10)
    private String operationID;

    public String getComponentID() {
        return componentID;
    }

    public void setComponentID(String componentID) {
        this.componentID = componentID;
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
        OperationObjectComponentId entity = (OperationObjectComponentId) o;
        return Objects.equals(this.componentID, entity.componentID) &&
                Objects.equals(this.operationID, entity.operationID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(componentID, operationID);
    }

}