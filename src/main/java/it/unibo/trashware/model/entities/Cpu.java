package it.unibo.trashware.model.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "cpu")
public class Cpu {
    @Id
    @Column(name = "IDComponente", nullable = false, length = 10)
    private String componentID;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "IDComponente", nullable = false)
    private it.unibo.trashware.model.entities.Component component;

    @Column(name = "Architettura", nullable = false)
    private Integer architecture;

    public String getComponentID() {
        return componentID;
    }

    public void setComponentID(String componentID) {
        this.componentID = componentID;
    }

    public it.unibo.trashware.model.entities.Component getComponent() {
        return component;
    }

    public void setComponent(it.unibo.trashware.model.entities.Component component) {
        this.component = component;
    }

    public Integer getArchitecture() {
        return architecture;
    }

    public void setArchitecture(Integer architecture) {
        this.architecture = architecture;
    }

}