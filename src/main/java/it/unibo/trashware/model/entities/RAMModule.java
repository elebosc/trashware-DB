package it.unibo.trashware.model.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "ram")
public class RAMModule {
    @Id
    @Column(name = "IDComponente", nullable = false, length = 10)
    private String iDComponente;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "IDComponente", nullable = false)
    private it.unibo.trashware.model.entities.Component component;

    @Column(name = "Dimensione", nullable = false)
    private Integer size;

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

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

}