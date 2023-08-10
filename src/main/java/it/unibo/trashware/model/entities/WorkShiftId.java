package it.unibo.trashware.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;

@Embeddable
public class WorkShiftId implements Serializable {
    private static final long serialVersionUID = -2512851027105748895L;
    @Column(name = "CodiceFiscaleOperatore", nullable = false, length = 16)
    private String codiceFiscaleOperatore;

    @Column(name = "Data", nullable = false)
    private LocalDate data;

    @Column(name = "OraInizio", nullable = false)
    private Instant oraInizio;

    public String getCodiceFiscaleOperatore() {
        return codiceFiscaleOperatore;
    }

    public void setCodiceFiscaleOperatore(String codiceFiscaleOperatore) {
        this.codiceFiscaleOperatore = codiceFiscaleOperatore;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Instant getOraInizio() {
        return oraInizio;
    }

    public void setOraInizio(Instant oraInizio) {
        this.oraInizio = oraInizio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        WorkShiftId entity = (WorkShiftId) o;
        return Objects.equals(this.data, entity.data) &&
                Objects.equals(this.oraInizio, entity.oraInizio) &&
                Objects.equals(this.codiceFiscaleOperatore, entity.codiceFiscaleOperatore);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data, oraInizio, codiceFiscaleOperatore);
    }

}