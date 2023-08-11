package it.unibo.trashware.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class RepresentationId implements Serializable {
    private static final long serialVersionUID = 905070332300834634L;
    @Column(name = "CodiceFiscaleReferente", nullable = false, length = 16, columnDefinition = "char")
    private String representativeFiscalCode;

    @Column(name = "`PartitaIVASocietà`", nullable = false, length = 11, columnDefinition = "char")
    private String societyVATNumber;

    public String getRepresentativeFiscalCode() {
        return representativeFiscalCode;
    }

    public void setRepresentativeFiscalCode(String representativeFiscalCode) {
        this.representativeFiscalCode = representativeFiscalCode;
    }

    public String getSocietyVATNumber() {
        return societyVATNumber;
    }

    public void setSocietyVATNumber(String societyVATNumber) {
        this.societyVATNumber = societyVATNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        RepresentationId entity = (RepresentationId) o;
        return Objects.equals(this.societyVATNumber, entity.societyVATNumber) &&
                Objects.equals(this.representativeFiscalCode, entity.representativeFiscalCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(societyVATNumber, representativeFiscalCode);
    }

}