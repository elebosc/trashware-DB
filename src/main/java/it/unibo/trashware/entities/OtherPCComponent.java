package it.unibo.trashware.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "altri_componenti_pc")
public class OtherPCComponent {

    @Id
    @Column(name = "IDComponente", nullable = false, length = 10)
    private String componentID;

    @Id
    @Column(name = "IDPC", nullable = false)
    private String pcID;

    public String getComponentID() {
        return componentID;
    }

    public void setComponentID(String componentID) {
        this.componentID = componentID;
    }

    public String getPcID() {
        return pcID;
    }

    public void setPcID(String pcID) {
        this.pcID = pcID;
    }

}