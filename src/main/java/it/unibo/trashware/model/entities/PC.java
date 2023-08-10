package it.unibo.trashware.model.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "pc")
public class PC {
    @Id
    @Column(name = "IDPC", nullable = false, length = 10)
    private String idpc;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDMemMassa_02")
    private it.unibo.trashware.model.entities.MassStorageDevice massStorage02ID;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "IDMemMassa_01", nullable = false)
    private it.unibo.trashware.model.entities.MassStorageDevice massStorage01ID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDRAM_02")
    private it.unibo.trashware.model.entities.RAMModule RAMModule02ID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDRAM_03")
    private it.unibo.trashware.model.entities.RAMModule RAMModule03ID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDRAM_04")
    private it.unibo.trashware.model.entities.RAMModule RAMModule04ID;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "IDRAM_01", nullable = false)
    private it.unibo.trashware.model.entities.RAMModule RAMModule01ID;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "IDCPU", nullable = false)
    private Cpu cpuID;

    @Column(name = "Tipo", nullable = false, length = 35)
    private String type;

    @Column(name = "Ethernet", nullable = false)
    private Byte isEthernetSupported;

    @Column(name = "WiFi", nullable = false)
    private Byte isWiFiSupported;

    @Column(name = "Bluetooth", nullable = false)
    private Byte isBluetoothSupported;

    @Column(name = "Note", length = 2000)
    private String notes;

    public String getIdpc() {
        return idpc;
    }

    public void setIdpc(String idpc) {
        this.idpc = idpc;
    }

    public it.unibo.trashware.model.entities.MassStorageDevice getMassStorage02ID() {
        return massStorage02ID;
    }

    public void setMassStorage02ID(it.unibo.trashware.model.entities.MassStorageDevice massStorage02ID) {
        this.massStorage02ID = massStorage02ID;
    }

    public it.unibo.trashware.model.entities.MassStorageDevice getMassStorage01ID() {
        return massStorage01ID;
    }

    public void setMassStorage01ID(it.unibo.trashware.model.entities.MassStorageDevice massStorage01ID) {
        this.massStorage01ID = massStorage01ID;
    }

    public it.unibo.trashware.model.entities.RAMModule getRAMModule02ID() {
        return RAMModule02ID;
    }

    public void setRAMModule02ID(it.unibo.trashware.model.entities.RAMModule RAMModule02ID) {
        this.RAMModule02ID = RAMModule02ID;
    }

    public it.unibo.trashware.model.entities.RAMModule getRAMModule03ID() {
        return RAMModule03ID;
    }

    public void setRAMModule03ID(it.unibo.trashware.model.entities.RAMModule RAMModule03ID) {
        this.RAMModule03ID = RAMModule03ID;
    }

    public it.unibo.trashware.model.entities.RAMModule getRAMModule04ID() {
        return RAMModule04ID;
    }

    public void setRAMModule04ID(it.unibo.trashware.model.entities.RAMModule RAMModule04ID) {
        this.RAMModule04ID = RAMModule04ID;
    }

    public it.unibo.trashware.model.entities.RAMModule getRAMModule01ID() {
        return RAMModule01ID;
    }

    public void setRAMModule01ID(it.unibo.trashware.model.entities.RAMModule RAMModule01ID) {
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

    public Byte getIsEthernetSupported() {
        return isEthernetSupported;
    }

    public void setIsEthernetSupported(Byte isEthernetSupported) {
        this.isEthernetSupported = isEthernetSupported;
    }

    public Byte getIsWiFiSupported() {
        return isWiFiSupported;
    }

    public void setIsWiFiSupported(Byte isWiFiSupported) {
        this.isWiFiSupported = isWiFiSupported;
    }

    public Byte getIsBluetoothSupported() {
        return isBluetoothSupported;
    }

    public void setIsBluetoothSupported(Byte isBluetoothSupported) {
        this.isBluetoothSupported = isBluetoothSupported;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

}