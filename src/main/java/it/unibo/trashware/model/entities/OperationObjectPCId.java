package it.unibo.trashware.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class OperationObjectPCId implements Serializable {
    private static final long serialVersionUID = 7368082363070276928L;
    @Column(name = "IDPC", nullable = false, length = 10)
    private String pcID;

    @Column(name = "IDOperazione", nullable = false, length = 10)
    private String operationID;

    public String getPcID() {
        return pcID;
    }

    public void setPcID(String pcID) {
        this.pcID = pcID;
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
        OperationObjectPCId entity = (OperationObjectPCId) o;
        return Objects.equals(this.operationID, entity.operationID) &&
                Objects.equals(this.pcID, entity.pcID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(operationID, pcID);
    }

}