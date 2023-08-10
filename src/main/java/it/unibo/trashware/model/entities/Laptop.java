package it.unibo.trashware.model.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "portatili")
public class Laptop {
    @Id
    @Column(name = "IDPC", nullable = false, length = 10)
    private String idpc;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "IDPC", nullable = false)
    private it.unibo.trashware.model.entities.PC pc;

    @Column(name = "Marca", nullable = false, length = 128)
    private String brand;

    @Column(name = "Modello", nullable = false, length = 128)
    private String model;

    @Column(name = "Dimensione", nullable = false)
    private Integer size;

    @Column(name = "Colore", nullable = false, length = 15)
    private String color;

    public String getIdpc() {
        return idpc;
    }

    public void setIdpc(String idpc) {
        this.idpc = idpc;
    }

    public it.unibo.trashware.model.entities.PC getPc() {
        return pc;
    }

    public void setPc(it.unibo.trashware.model.entities.PC pc) {
        this.pc = pc;
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

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

}