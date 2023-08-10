package it.unibo.trashware.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class OperationObjectPeripheralId implements Serializable {
    private static final long serialVersionUID = -2153242838408291998L;
    @Column(name = "IDPeriferica", nullable = false, length = 10)
    private String iDPeriferica;

    @Column(name = "IDOperazione", nullable = false, length = 10)
    private String iDOperazione;

    public String getIDPeriferica() {
        return iDPeriferica;
    }

    public void setIDPeriferica(String iDPeriferica) {
        this.iDPeriferica = iDPeriferica;
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
        OperationObjectPeripheralId entity = (OperationObjectPeripheralId) o;
        return Objects.equals(this.iDPeriferica, entity.iDPeriferica) &&
                Objects.equals(this.iDOperazione, entity.iDOperazione);
    }

    @Override
    public int hashCode() {
        return Objects.hash(iDPeriferica, iDOperazione);
    }

}