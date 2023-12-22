package it.unibo.trashware.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "rappresentanza")
public class Representation {
    @EmbeddedId
    private RepresentationId id;

    @MapsId("codiceFiscaleReferente")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CodiceFiscaleReferente", nullable = false, columnDefinition = "char")
    private it.unibo.trashware.entities.Representative representativeFiscalCode;

    @MapsId("partitaIVASocietà")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "`PartitaIVASocietà`", nullable = false)
    private it.unibo.trashware.entities.Society societyVATNumber;

    @Column(name = "TitoloReferente", nullable = false, length = 35)
    private String representativeTitle;

    public RepresentationId getId() {
        return id;
    }

    public void setId(RepresentationId id) {
        this.id = id;
    }

    public it.unibo.trashware.entities.Representative getRepresentativeFiscalCode() {
        return representativeFiscalCode;
    }

    public void setRepresentativeFiscalCode(it.unibo.trashware.entities.Representative representativeFiscalCode) {
        this.representativeFiscalCode = representativeFiscalCode;
    }

    public it.unibo.trashware.entities.Society getSocietyVATNumber() {
        return societyVATNumber;
    }

    public void setSocietyVATNumber(it.unibo.trashware.entities.Society societyVATNumber) {
        this.societyVATNumber = societyVATNumber;
    }

    public String getRepresentativeTitle() {
        return representativeTitle;
    }

    public void setRepresentativeTitle(String representativeTitle) {
        this.representativeTitle = representativeTitle;
    }

}