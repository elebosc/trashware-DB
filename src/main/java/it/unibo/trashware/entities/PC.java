package it.unibo.trashware.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "pc")
public class PC {
    @Id
    @Column(name = "IDPC", nullable = false, length = 10)
    private String pcID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDMemMassa_02")
    private it.unibo.trashware.entities.MassStorageDevice massStorage02ID;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "IDMemMassa_01", nullable = false)
    private it.unibo.trashware.entities.MassStorageDevice massStorage01ID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDRAM_02")
    private it.unibo.trashware.entities.RAMModule RAMModule02ID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDRAM_03")
    private it.unibo.trashware.entities.RAMModule RAMModule03ID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDRAM_04")
    private it.unibo.trashware.entities.RAMModule RAMModule04ID;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "IDRAM_01", nullable = false)
    private it.unibo.trashware.entities.RAMModule RAMModule01ID;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "IDCPU", nullable = false)
    private Cpu cpuID;

    @Column(name = "Tipo", nullable = false, length = 35)
    private String type;

    @Column(name = "Ethernet", nullable = false, columnDefinition = "BIT", length = 1)
    private boolean isEthernetSupported;

    @Column(name = "WiFi", nullable = false, columnDefinition = "BIT", length = 1)
    private boolean isWiFiSupported;

    @Column(name = "Bluetooth", nullable = false, columnDefinition = "BIT", length = 1)
    private boolean isBluetoothSupported;

    @Column(name = "Note", length = 2000)
    private String notes;

    public String getPcID() {
        return pcID;
    }

    public void setPcID(String idpc) {
        this.pcID = idpc;
    }

    public it.unibo.trashware.entities.MassStorageDevice getMassStorage02ID() {
        return massStorage02ID;
    }

    public void setMassStorage02ID(it.unibo.trashware.entities.MassStorageDevice massStorage02ID) {
        this.massStorage02ID = massStorage02ID;
    }

    public it.unibo.trashware.entities.MassStorageDevice getMassStorage01ID() {
        return massStorage01ID;
    }

    public void setMassStorage01ID(it.unibo.trashware.entities.MassStorageDevice massStorage01ID) {
        this.massStorage01ID = massStorage01ID;
    }

    public it.unibo.trashware.entities.RAMModule getRAMModule02ID() {
        return RAMModule02ID;
    }

    public void setRAMModule02ID(it.unibo.trashware.entities.RAMModule RAMModule02ID) {
        this.RAMModule02ID = RAMModule02ID;
    }

    public it.unibo.trashware.entities.RAMModule getRAMModule03ID() {
        return RAMModule03ID;
    }

    public void setRAMModule03ID(it.unibo.trashware.entities.RAMModule RAMModule03ID) {
        this.RAMModule03ID = RAMModule03ID;
    }

    public it.unibo.trashware.entities.RAMModule getRAMModule04ID() {
        return RAMModule04ID;
    }

    public void setRAMModule04ID(it.unibo.trashware.entities.RAMModule RAMModule04ID) {
        this.RAMModule04ID = RAMModule04ID;
    }

    public it.unibo.trashware.entities.RAMModule getRAMModule01ID() {
        return RAMModule01ID;
    }

    public void setRAMModule01ID(it.unibo.trashware.entities.RAMModule RAMModule01ID) {
        this.RAMModule01ID = RAMModule01ID;
    }

    public Cpu getCpuID() {
        return cpuID;
    }

    public void setCpuID(Cpu cpuID) {
        this.cpuID = cpuID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean getIsEthernetSupported() {
        return isEthernetSupported;
    }

    public void setIsEthernetSupported(boolean isEthernetSupported) {
        this.isEthernetSupported = isEthernetSupported;
    }

    public boolean getIsWiFiSupported() {
        return isWiFiSupported;
    }

    public void setIsWiFiSupported(boolean isWiFiSupported) {
        this.isWiFiSupported = isWiFiSupported;
    }

    public boolean getIsBluetoothSupported() {
        return isBluetoothSupported;
    }

    public void setIsBluetoothSupported(boolean isBluetoothSupported) {
        this.isBluetoothSupported = isBluetoothSupported;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

}