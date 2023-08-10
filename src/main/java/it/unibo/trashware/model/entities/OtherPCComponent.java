package it.unibo.trashware.model.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "altri_componenti_pc")
public class OtherPCComponent {
    @Id
    @Column(name = "IDComponente", nullable = false, length = 10)
    private String iDComponente;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "IDComponente", nullable = false)
    private it.unibo.trashware.model.entities.Component component;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "IDPC", nullable = false)
    private it.unibo.trashware.model.entities.PC pcID;

    public String getIDComponente() {
        return iDComponente;
    }

    public void setIDComponente(String iDComponente) {
        this.iDComponente = iDComponente;
    }

    public it.unibo.trashware.model.entities.Component getComponent() {
        return component;
    }

    public void setComponent(it.unibo.trashware.model.entities.Component component) {
        this.component = component;
    }

    public it.unibo.trashware.model.entities.PC getPcID() {
        return pcID;
    }

    public void setPcID(it.unibo.trashware.model.entities.PC pcID) {
        this.pcID = pcID;
    }

}