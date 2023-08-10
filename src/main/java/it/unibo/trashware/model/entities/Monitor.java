package it.unibo.trashware.model.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "monitor")
public class Monitor {
    @Id
    @Column(name = "IDPeriferica", nullable = false, length = 10)
    private String iDPeriferica;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "IDPeriferica", nullable = false)
    private it.unibo.trashware.model.entities.Peripheral peripheral;

    @Column(name = "Tipologia", nullable = false, length = 35)
    private String type;

    @Column(name = "Dimensione", nullable = false)
    private Integer size;

    @Column(name = "AspectRatio", nullable = false, length = 6)
    private String aspectRatio;

    @Column(name = "VGA", nullable = false)
    private Byte isVGASupported;

    @Column(name = "DVI", nullable = false)
    private Byte isDVISupported;

    @Column(name = "AudioIntegrato", nullable = false)
    private Byte hasEmbeddedAudio;

    public String getIDPeriferica() {
        return iDPeriferica;
    }

    public void setIDPeriferica(String iDPeriferica) {
        this.iDPeriferica = iDPeriferica;
    }

    public it.unibo.trashware.model.entities.Peripheral getPeripheral() {
        return peripheral;
    }

    public void setPeripheral(it.unibo.trashware.model.entities.Peripheral peripheral) {
        this.peripheral = peripheral;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getAspectRatio() {
        return aspectRatio;
    }

    public void setAspectRatio(String aspectRatio) {
        this.aspectRatio = aspectRatio;
    }

    public Byte getIsVGASupported() {
        return isVGASupported;
    }

    public void setIsVGASupported(Byte isVGASupported) {
        this.isVGASupported = isVGASupported;
    }

    public Byte getIsDVISupported() {
        return isDVISupported;
    }

    public void setIsDVISupported(Byte isDVISupported) {
        this.isDVISupported = isDVISupported;
    }

    public Byte getHasEmbeddedAudio() {
        return hasEmbeddedAudio;
    }

    public void setHasEmbeddedAudio(Byte hasEmbeddedAudio) {
        this.hasEmbeddedAudio = hasEmbeddedAudio;
    }

}