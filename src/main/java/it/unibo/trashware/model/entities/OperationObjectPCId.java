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
    private String idpc;

    @Column(name = "IDOperazione", nullable = false, length = 10)
    private String iDOperazione;

    public String getIdpc() {
        return idpc;
    }

    public void setIdpc(String idpc) {
        this.idpc = idpc;
    }

    public String getIDOperazione() {
        return iDOperazione;
    }

    public void setIDOperazione(String iDOperazione) {
        this.iDOperazione = iDOperazione;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        OperationObjectPCId entity = (OperationObjectPCId) o;
        return Objects.equals(this.iDOperazione, entity.iDOperazione) &&
                Objects.equals(this.idpc, entity.idpc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(iDOperazione, idpc);
    }

}