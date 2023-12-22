package it.unibo.trashware.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "`società`", indexes = {
        @Index(name = "IDSOCIETÀ_1", columnList = "CodiceFiscale", unique = true)
})
public class Society {
    @Id
    @Column(name = "PartitaIVA", nullable = false, length = 11, columnDefinition = "char")
    private String VATNumber;

    @Column(name = "CodiceFiscale", nullable = false, length = 16)
    private String fiscalCode;

    @Column(name = "Nome", nullable = false, length = 35)
    private String name;

    @Column(name = "`Città_SedeLegale`", nullable = false, length = 35)
    private String registeredOfficeCity;

    @Column(name = "CAP_SedeLegale", nullable = false, length = 5, columnDefinition = "char")
    private String registeredOfficeCAP;

    @Column(name = "Provincia_SedeLegale", nullable = false, length = 2, columnDefinition = "char")
    private String registeredOfficeProvince;

    @Column(name = "Via_SedeLegale", nullable = false, length = 35)
    private String registeredOfficeStreet;

    @Column(name = "NumCivico_SedeLegale", nullable = false)
    private Integer registeredOfficeStreetNumber;

    public String getVATNumber() {
        return VATNumber;
    }

    public void setVATNumber(String VATNumber) {
        this.VATNumber = VATNumber;
    }

    public String getFiscalCode() {
        return fiscalCode;
    }

    public void setFiscalCode(String fiscalCode) {
        this.fiscalCode = fiscalCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegisteredOfficeCity() {
        return registeredOfficeCity;
    }

    public void setRegisteredOfficeCity(String registeredOfficeCity) {
        this.registeredOfficeCity = registeredOfficeCity;
    }

    public String getRegisteredOfficeCAP() {
        return registeredOfficeCAP;
    }

    public void setRegisteredOfficeCAP(String registeredOfficeCAP) {
        this.registeredOfficeCAP = registeredOfficeCAP;
    }

    public String getRegisteredOfficeProvince() {
        return registeredOfficeProvince;
    }

    public void setRegisteredOfficeProvince(String registeredOfficeProvince) {
        this.registeredOfficeProvince = registeredOfficeProvince;
    }

    public String getRegisteredOfficeStreet() {
        return registeredOfficeStreet;
    }

    public void setRegisteredOfficeStreet(String registeredOfficeStreet) {
        this.registeredOfficeStreet = registeredOfficeStreet;
    }

    public Integer getRegisteredOfficeStreetNumber() {
        return registeredOfficeStreetNumber;
    }

    public void setRegisteredOfficeStreetNumber(Integer registeredOfficeStreetNumber) {
        this.registeredOfficeStreetNumber = registeredOfficeStreetNumber;
    }

}