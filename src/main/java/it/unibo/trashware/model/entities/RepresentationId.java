package it.unibo.trashware.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class RepresentationId implements Serializable {
    private static final long serialVersionUID = 905070332300834634L;
    @Column(name = "CodiceFiscaleReferente", nullable = false, length = 16)
    private String codiceFiscaleReferente;

    @Column(name = "`PartitaIVASocietà`", nullable = false, length = 11)
    private String partitaIVASocietà;

    public String getCodiceFiscaleReferente() {
        return codiceFiscaleReferente;
    }

    public void setCodiceFiscaleReferente(String codiceFiscaleReferente) {
        this.codiceFiscaleReferente = codiceFiscaleReferente;
    }

    public String getPartitaIVASocietà() {
        return partitaIVASocietà;
    }

    public void setPartitaIVASocietà(String partitaIVASocietà) {
        this.partitaIVASocietà = partitaIVASocietà;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        RepresentationId entity = (RepresentationId) o;
        return Objects.equals(this.partitaIVASocietà, entity.partitaIVASocietà) &&
                Objects.equals(this.codiceFiscaleReferente, entity.codiceFiscaleReferente);
    }

    @Override
    public int hashCode() {
        return Objects.hash(partitaIVASocietà, codiceFiscaleReferente);
    }

}