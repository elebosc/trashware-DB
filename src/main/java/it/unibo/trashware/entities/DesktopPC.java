package it.unibo.trashware.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "desktop")
public class DesktopPC {
    @Id
    @Column(name = "IDPC", nullable = false, length = 10)
    private String pcID;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "IDPC", nullable = false)
    private it.unibo.trashware.entities.PC pc;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDMonitor")
    private Monitor monitorID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDCasseAudio")
    private it.unibo.trashware.entities.Peripheral audioSpeakersID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDMouse")
    private it.unibo.trashware.entities.Peripheral mouseID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDTastiera")
    private it.unibo.trashware.entities.Peripheral keyboardID;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "IDChassis", nullable = false)
    private Chassis chassisID;

    public String getPcID() {
        return pcID;
    }

    public void setPcID(String idpc) {
        this.pcID = idpc;
    }

    public it.unibo.trashware.entities.PC getPc() {
        return pc;
    }

    public void setPc(it.unibo.trashware.entities.PC pc) {
        this.pc = pc;
    }

    public Monitor getMonitorID() {
        return monitorID;
    }

    public void setMonitorID(Monitor monitorID) {
        this.monitorID = monitorID;
    }

    public it.unibo.trashware.entities.Peripheral getAudioSpeakersID() {
        return audioSpeakersID;
    }

    public void setAudioSpeakersID(it.unibo.trashware.entities.Peripheral audioSpeakersID) {
        this.audioSpeakersID = audioSpeakersID;
    }

    public it.unibo.trashware.entities.Peripheral getMouseID() {
        return mouseID;
    }

    public void setMouseID(it.unibo.trashware.entities.Peripheral mouseID) {
        this.mouseID = mouseID;
    }

    public it.unibo.trashware.entities.Peripheral getKeyboardID() {
        return keyboardID;
    }

    public void setKeyboardID(it.unibo.trashware.entities.Peripheral keyboardID) {
        this.keyboardID = keyboardID;
    }

    public Chassis getChassisID() {
        return chassisID;
    }

    public void setChassisID(Chassis chassisID) {
        this.chassisID = chassisID;
    }

}