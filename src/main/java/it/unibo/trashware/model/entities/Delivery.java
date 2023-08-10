package it.unibo.trashware.model.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "consegna")
public class Delivery {
    @Id
    @Column(name = "IDRichiesta", nullable = false, length = 10)
    private String iDRichiesta;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "IDRichiesta", nullable = false)
    private it.unibo.trashware.model.entities.Completion completion;

    @Column(name = "Data", nullable = false)
    private LocalDate date;

    public String getIDRichiesta() {
        return iDRichiesta;
    }

    public void setIDRichiesta(String iDRichiesta) {
        this.iDRichiesta = iDRichiesta;
    }

    public it.unibo.trashware.model.entities.Completion getCompletion() {
        return completion;
    }

    public void setCompletion(it.unibo.trashware.model.entities.Completion completion) {
        this.completion = completion;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

}