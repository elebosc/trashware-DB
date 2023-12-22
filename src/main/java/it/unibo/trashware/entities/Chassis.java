package it.unibo.trashware.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "chassis")
public class Chassis {
    @Id
    @Column(name = "IDComponente", nullable = false, length = 10)
    private String componentID;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "IDComponente", nullable = false)
    private it.unibo.trashware.entities.Component component;

    @Column(name = "Colore", nullable = false, length = 15)
    private String color;

    public String getComponentID() {
        return componentID;
    }

    public void setComponentID(String componentID) {
        this.componentID = componentID;
    }

    public it.unibo.trashware.entities.Component getComponent() {
        return component;
    }

    public void setComponent(it.unibo.trashware.entities.Component component) {
        this.component = component;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

}