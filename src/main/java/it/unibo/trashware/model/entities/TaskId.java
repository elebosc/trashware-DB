package it.unibo.trashware.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;

@Embeddable
public class TaskId implements Serializable {
    private static final long serialVersionUID = 7935673349876242184L;
    @Column(name = "CodiceFiscaleOperatore", nullable = false, length = 16)
    private String codiceFiscaleOperatore;

    @Column(name = "DataTurno", nullable = false)
    private LocalDate dataTurno;

    @Column(name = "OraInizioTurno", nullable = false)
    private Instant oraInizioTurno;

    @Column(name = "NumElenco", nullable = false)
    private Integer numElenco;

    public String getCodiceFiscaleOperatore() {
        return codiceFiscaleOperatore;
    }

    public void setCodiceFiscaleOperatore(String codiceFiscaleOperatore) {
        this.codiceFiscaleOperatore = codiceFiscaleOperatore;
    }

    public LocalDate getDataTurno() {
        return dataTurno;
    }

    public void setDataTurno(LocalDate dataTurno) {
        this.dataTurno = dataTurno;
    }

    public Instant getOraInizioTurno() {
        return oraInizioTurno;
    }

    public void setOraInizioTurno(Instant oraInizioTurno) {
        this.oraInizioTurno = oraInizioTurno;
    }

    public Integer getNumElenco() {
        return numElenco;
    }

    public void setNumElenco(Integer numElenco) {
        this.numElenco = numElenco;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        TaskId entity = (TaskId) o;
        return Objects.equals(this.oraInizioTurno, entity.oraInizioTurno) &&
                Objects.equals(this.dataTurno, entity.dataTurno) &&
                Objects.equals(this.numElenco, entity.numElenco) &&
                Objects.equals(this.codiceFiscaleOperatore, entity.codiceFiscaleOperatore);
    }

    @Override
    public int hashCode() {
        return Objects.hash(oraInizioTurno, dataTurno, numElenco, codiceFiscaleOperatore);
    }

}