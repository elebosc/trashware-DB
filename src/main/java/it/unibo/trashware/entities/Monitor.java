package it.unibo.trashware.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "monitor")
public class Monitor {
    @Id
    @Column(name = "IDPeriferica", nullable = false, length = 10)
    private String peripheralID;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "IDPeriferica", nullable = false)
    private it.unibo.trashware.entities.Peripheral peripheral;

    @Column(name = "Tipologia", nullable = false, length = 35)
    private String type;

    @Column(name = "Dimensione", nullable = false)
    private Integer size;

    @Column(name = "AspectRatio", nullable = false, length = 6)
    private String aspectRatio;

    @Column(name = "VGA", nullable = false, columnDefinition = "BIT", length = 1)
    private boolean isVGASupported;

    @Column(name = "DVI", nullable = false, columnDefinition = "BIT", length = 1)
    private boolean isDVISupported;

    @Column(name = "AudioIntegrato", nullable = false, columnDefinition = "BIT", length = 1)
    private boolean hasEmbeddedAudio;

    public String getPeripheralID() {
        return peripheralID;
    }

    public void setPeripheralID(String peripheralID) {
        this.peripheralID = peripheralID;
    }

    public it.unibo.trashware.entities.Peripheral getPeripheral() {
        return peripheral;
    }

    public void setPeripheral(it.unibo.trashware.entities.Peripheral peripheral) {
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

    public boolean getIsVGASupported() {
        return isVGASupported;
    }

    public void setIsVGASupported(boolean isVGASupported) {
        this.isVGASupported = isVGASupported;
    }

    public boolean getIsDVISupported() {
        return isDVISupported;
    }

    public void setIsDVISupported(boolean isDVISupported) {
        this.isDVISupported = isDVISupported;
    }

    public boolean getHasEmbeddedAudio() {
        return hasEmbeddedAudio;
    }

    public void setHasEmbeddedAudio(boolean hasEmbeddedAudio) {
        this.hasEmbeddedAudio = hasEmbeddedAudio;
    }

}