package it.unibo.trashware.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "ram")
public class RAMModule {
    @Id
    @Column(name = "IDComponente", nullable = false, length = 10)
    private String componentID;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "IDComponente", nullable = false)
    private it.unibo.trashware.entities.Component component;

    @Column(name = "Dimensione", nullable = false)
    private Integer size;

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

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

}