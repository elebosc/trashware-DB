package it.unibo.trashware.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "componenti")
public class Component {
    @Id
    @Column(name = "IDComponente", nullable = false, length = 10)
    private String iDComponente;

    @Column(name = "Tipo", nullable = false, length = 35)
    private String type;

    @Column(name = "Marca", nullable = false, length = 128)
    private String brand;

    @Column(name = "Modello", nullable = false, length = 128)
    private String model;

    @Column(name = "Note", length = 2000)
    private String notes;

    public String getIDComponente() {
        return iDComponente;
    }

    public void setIDComponente(String iDComponente) {
        this.iDComponente = iDComponente;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

}