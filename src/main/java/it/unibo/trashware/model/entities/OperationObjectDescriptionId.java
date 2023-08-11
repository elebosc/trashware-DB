package it.unibo.trashware.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class OperationObjectDescriptionId implements Serializable {
    private static final long serialVersionUID = 593576048454640100L;
    @Column(name = "IDOperazione", nullable = false, length = 10)
    private String operationID;

    @Column(name = "NumLinea", nullable = false)
    private Integer lineNumber;

    public String getOperationID() {
        return operationID;
    }

    public void setOperationID(String operationID) {
        this.operationID = operationID;
    }

    public Integer getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(Integer lineNumber) {
        this.lineNumber = lineNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        OperationObjectDescriptionId entity = (OperationObjectDescriptionId) o;
        return Objects.equals(this.operationID, entity.operationID) &&
                Objects.equals(this.lineNumber, entity.lineNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(operationID, lineNumber);
    }

}