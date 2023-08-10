package it.unibo.trashware.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Società")
public class Society {
    
    @Id
    @Column(name = "PartivaIVA", nullable = false)
    private String VATNumber;

    @Column(name = "CodiceFiscale", unique = true, nullable = false)
    private String fiscalCode;

    @Column(name = "Nome", nullable = false)
    private String name;

    @Column(name = "Città_SedeLegale", nullable = false)
    private String registeredOfficeCity;

    @Column(name = "CAP_SedeLegale", nullable = false)
    private String registeredOfficeCAP;

    @Column(name = "Provincia_SedeLegale", nullable = false)
    private String registeredOfficeProvince;

    @Column(name = "Via_SedeLegale", nullable = false)
    private String registeredOfficeStreet;

    @Column(name = "NumCivico_SedeLegale", nullable = false)
    private int registeredOfficeStreetNumber;

    /* 
     * Generated getters, hashCode(), equals() and toString()
     */

    public Society(
        final String vATNumber, 
        final String fiscalCode, 
        final String name, 
        final String registeredAddressCity,
        final String registeredAddressCAP,
        final String registeredAddressProvince,
        final String registeredAddressStreet,
        final int registeredAddressHouseNumber
    ) {
        this.VATNumber = vATNumber;
        this.fiscalCode = fiscalCode;
        this.name = name;
        this.registeredOfficeCity = registeredAddressCity;
        this.registeredOfficeCAP = registeredAddressCAP;
        this.registeredOfficeProvince = registeredAddressProvince;
        this.registeredOfficeStreet = registeredAddressStreet;
        this.registeredOfficeStreetNumber = registeredAddressHouseNumber;
    }

    public String getVATNumber() {
        return VATNumber;
    }

    public String getFiscalCode() {
        return fiscalCode;
    }

    public String getName() {
        return name;
    }

    public String getRegisteredOfficeCity() {
        return registeredOfficeCity;
    }

    public String getRegisteredOfficeCAP() {
        return registeredOfficeCAP;
    }

    public String getRegisteredOfficeProvince() {
        return registeredOfficeProvince;
    }

    public String getRegisteredOfficeStreet() {
        return registeredOfficeStreet;
    }

    public int getRegisteredOfficeStreetNumber() {
        return registeredOfficeStreetNumber;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((VATNumber == null) ? 0 : VATNumber.hashCode());
        result = prime * result + ((fiscalCode == null) ? 0 : fiscalCode.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((registeredOfficeCity == null) ? 0 : registeredOfficeCity.hashCode());
        result = prime * result + ((registeredOfficeCAP == null) ? 0 : registeredOfficeCAP.hashCode());
        result = prime * result + ((registeredOfficeProvince == null) ? 0 : registeredOfficeProvince.hashCode());
        result = prime * result + ((registeredOfficeStreet == null) ? 0 : registeredOfficeStreet.hashCode());
        result = prime * result + registeredOfficeStreetNumber;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Society other = (Society) obj;
        if (VATNumber == null) {
            if (other.VATNumber != null)
                return false;
        } else if (!VATNumber.equals(other.VATNumber))
            return false;
        if (fiscalCode == null) {
            if (other.fiscalCode != null)
                return false;
        } else if (!fiscalCode.equals(other.fiscalCode))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (registeredOfficeCity == null) {
            if (other.registeredOfficeCity != null)
                return false;
        } else if (!registeredOfficeCity.equals(other.registeredOfficeCity))
            return false;
        if (registeredOfficeCAP == null) {
            if (other.registeredOfficeCAP != null)
                return false;
        } else if (!registeredOfficeCAP.equals(other.registeredOfficeCAP))
            return false;
        if (registeredOfficeProvince == null) {
            if (other.registeredOfficeProvince != null)
                return false;
        } else if (!registeredOfficeProvince.equals(other.registeredOfficeProvince))
            return false;
        if (registeredOfficeStreet == null) {
            if (other.registeredOfficeStreet != null)
                return false;
        } else if (!registeredOfficeStreet.equals(other.registeredOfficeStreet))
            return false;
        if (registeredOfficeStreetNumber != other.registeredOfficeStreetNumber)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Society [VATNumber=" + VATNumber + ", fiscalCode=" + fiscalCode + ", name=" + name
                + ", registeredAddressCity=" + registeredOfficeCity + ", registeredAddressCAP=" + registeredOfficeCAP
                + ", registeredAddressProvince=" + registeredOfficeProvince + ", registeredAddressStreet="
                + registeredOfficeStreet + ", registeredAddressHouseNumber=" + registeredOfficeStreetNumber + "]";
    }
    
}
