package it.unibo.trashware.model.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "completamento")
public class Completion {
    @Id
    @Column(name = "IDRichiesta", nullable = false, length = 10)
    private String requestID;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "IDRichiesta", nullable = false)
    private it.unibo.trashware.model.entities.Request request;

    @Column(name = "Data", nullable = false)
    private LocalDate date;

    public String getRequestID() {
        return requestID;
    }

    public void setRequestID(String requestID) {
        this.requestID = requestID;
    }

    public it.unibo.trashware.model.entities.Request getRequest() {
        return request;
    }

    public void setRequest(it.unibo.trashware.model.entities.Request request) {
        this.request = request;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

}