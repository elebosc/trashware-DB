package it.unibo.trashware.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "richieste")
public class Request {
    @Id
    @Column(name = "IDRichiesta", nullable = false, length = 10)
    private String requestID;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "IDRichiesta", nullable = false)
    private it.unibo.trashware.entities.Operation operationID;

    @Column(name = "Tipo", nullable = false, length = 35)
    private String type;

    @Column(name = "Motivazione", nullable = false, length = 2000)
    private String reason;

    @Column(name = "DataLimite", nullable = false)
    private LocalDate deadlineDate;

    @Column(name = "`LivelloPriorità`", nullable = false)
    private Integer priorityLevel;

    @Column(name = "Stato", nullable = false, length = 35)
    private String status;

    public String getRequestID() {
        return requestID;
    }

    public void setRequestID(String requestID) {
        this.requestID = requestID;
    }

    public it.unibo.trashware.entities.Operation getOperationID() {
        return operationID;
    }

    public void setOperationID(it.unibo.trashware.entities.Operation operationID) {
        this.operationID = operationID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public LocalDate getDeadlineDate() {
        return deadlineDate;
    }

    public void setDeadlineDate(LocalDate deadlineDate) {
        this.deadlineDate = deadlineDate;
    }

    public Integer getPriorityLevel() {
        return priorityLevel;
    }

    public void setPriorityLevel(Integer priorityLevel) {
        this.priorityLevel = priorityLevel;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}