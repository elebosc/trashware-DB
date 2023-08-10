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
    private String iDOperazione;

    @Column(name = "NumLinea", nullable = false)
    private Integer numLinea;

    public String getIDOperazione() {
        return iDOperazione;
    }

    public void setIDOperazione(String iDOperazione) {
        this.iDOperazione = iDOperazione;
    }

    public Integer getNumLinea() {
        return numLinea;
    }

    public void setNumLinea(Integer numLinea) {
        this.numLinea = numLinea;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        OperationObjectDescriptionId entity = (OperationObjectDescriptionId) o;
        return Objects.equals(this.iDOperazione, entity.iDOperazione) &&
                Objects.equals(this.numLinea, entity.numLinea);
    }

    @Override
    public int hashCode() {
        return Objects.hash(iDOperazione, numLinea);
    }

}