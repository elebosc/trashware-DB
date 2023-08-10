package it.unibo.trashware.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class OperationObjectComponentId implements Serializable {
    private static final long serialVersionUID = 5652926893960953850L;
    @Column(name = "IDComponente", nullable = false, length = 10)
    private String iDComponente;

    @Column(name = "IDOperazione", nullable = false, length = 10)
    private String iDOperazione;

    public String getIDComponente() {
        return iDComponente;
    }

    public void setIDComponente(String iDComponente) {
        this.iDComponente = iDComponente;
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
        OperationObjectComponentId entity = (OperationObjectComponentId) o;
        return Objects.equals(this.iDComponente, entity.iDComponente) &&
                Objects.equals(this.iDOperazione, entity.iDOperazione);
    }

    @Override
    public int hashCode() {
        return Objects.hash(iDComponente, iDOperazione);
    }

}