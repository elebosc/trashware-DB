package it.unibo.trashware.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "sistema_operativo")
public class OperatingSystem {
    @EmbeddedId
    private OperatingSystemId id;

    @MapsId("idpc")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "IDPC", nullable = false)
    private it.unibo.trashware.entities.PC pcID;

    @Column(name = "DataUltimoAggiornamento", nullable = false)
    private LocalDate lastUpdateDate;

    public OperatingSystemId getId() {
        return id;
    }

    public void setId(OperatingSystemId id) {
        this.id = id;
    }

    public it.unibo.trashware.entities.PC getPcID() {
        return pcID;
    }

    public void setPcID(it.unibo.trashware.entities.PC pcID) {
        this.pcID = pcID;
    }

    public LocalDate getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(LocalDate lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

}