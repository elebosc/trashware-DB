package it.unibo.trashware.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "operazioni")
public class Operation {
    @Id
    @Column(name = "IDOperazione", nullable = false, length = 10)
    private String operationID;

    @Column(name = "Tipo", nullable = false, length = 35)
    private String type;

    @Column(name = "DataEffettuazione", nullable = false)
    private LocalDate date;

    @Column(name = "ElencoDispositivi", nullable = false, length = 2000)
    private String devicesList;

    @Column(name = "Note", length = 2000)
    private String notes;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CodiceFiscaleReferente", nullable = false, columnDefinition = "char")
    private it.unibo.trashware.entities.Representative representativeFiscalCode;

    public String getOperationID() {
        return operationID;
    }

    public void setOperationID(String operationID) {
        this.operationID = operationID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDevicesList() {
        return devicesList;
    }

    public void setDevicesList(String devicesList) {
        this.devicesList = devicesList;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public it.unibo.trashware.entities.Representative getRepresentativeFiscalCode() {
        return representativeFiscalCode;
    }

    public void setRepresentativeFiscalCode(it.unibo.trashware.entities.Representative representativeFiscalCode) {
        this.representativeFiscalCode = representativeFiscalCode;
    }

}