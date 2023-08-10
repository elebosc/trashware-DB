package it.unibo.trashware.model;

import java.util.Date;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Operatore")
public final class Operator {
    
    @Id
    @Column(name = "CodiceFiscale", nullable = false)
    private String fiscalCode;

    @Column(name = "Nome", nullable = false)
    private String name;

    @Column(name = "Cognome", nullable = false)
    private String surname;

    @Column(name = "Luogo_Nascita", nullable = false)
    private String birthPlace;

    @Column(name = "Data_Nascita", nullable = false)
    private Date birthday;

    @Column(name = "Città_Residenza", nullable = false)
    private String residenceCity;

    @Column(name = "CAP_Residenza", nullable = false)
    private String residenceCAP;

    @Column(name = "Provincia_Residenza", nullable = false)
    private String residenceProvince;

    @Column(name = "Via_Residenza", nullable = false)
    private String residenceStreet;

    @Column(name = "NumCivico_Residenza", nullable = false)
    private int residenceHouseNumber;

    @Column(name = "NumTelefono1", nullable = false)
    private String telephoneNumber1;

    @Column(name = "NumTelefono2")
    private String telephoneNumber2;

    @Column(name = "Email")
    private String email;

    public Operator() {
        // Empty
    }

    public Operator(
        final String fiscalCode,
        final String name,
        final String surname,
        final String birthPlace,
        final Date birthday,
        final String residenceCity,
        final String residenceCAP,
        final String residenceProvince,
        final String residenceStreet,
        final int residenceHouseNumber,
        final String telephoneNumber1,
        final String telephoneNumber2,
        final String email
    ) {
        this.fiscalCode = Objects.requireNonNull(fiscalCode);
        this.name = Objects.requireNonNull(name);
        this.surname = Objects.requireNonNull(surname);
        this.birthPlace = Objects.requireNonNull(birthPlace);
        this.birthday = Objects.requireNonNull(birthday);
        this.residenceCity = Objects.requireNonNull(residenceCity);
        this.residenceCAP = Objects.requireNonNull(residenceCAP);
        this.residenceProvince = Objects.requireNonNull(residenceProvince);
        this.residenceStreet = Objects.requireNonNull(residenceStreet);
        this.residenceHouseNumber = residenceHouseNumber;
        this.telephoneNumber1 = Objects.requireNonNull(telephoneNumber1);
        this.telephoneNumber2 = telephoneNumber2;
        this.email = email;
    }

    public Operator(
        final String fiscalCode,
        final String name,
        final String surname,
        final String birthPlace,
        final Date birthday,
        final String residenceCity,
        final String residenceCAP,
        final String residenceProvince,
        final String residenceStreet,
        final int residenceHouseNumber,
        final String telephoneNumber1
    ) {
        this(
            fiscalCode,
            name,
            surname,
            birthPlace,
            birthday,
            residenceCity,
            residenceCAP,
            residenceProvince,
            residenceStreet,
            residenceHouseNumber,
            telephoneNumber1,
            null,
            null
        );
    }

    /* 
     * Generated getters, hashCode(), equals() and toString()
     */

    public String getFiscalCode() {
        return fiscalCode;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public Date getBirthday() {
        return birthday;
    }

    public String getResidenceCity() {
        return residenceCity;
    }

    public String getResidenceCAP() {
        return residenceCAP;
    }

    public String getResidenceProvince() {
        return residenceProvince;
    }

    public String getResidenceStreet() {
        return residenceStreet;
    }

    public int getResidenceHouseNumber() {
        return residenceHouseNumber;
    }

    public String getTelephoneNumber1() {
        return telephoneNumber1;
    }

    public String getTelephoneNumber2() {
        return telephoneNumber2;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((fiscalCode == null) ? 0 : fiscalCode.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((surname == null) ? 0 : surname.hashCode());
        result = prime * result + ((birthPlace == null) ? 0 : birthPlace.hashCode());
        result = prime * result + ((birthday == null) ? 0 : birthday.hashCode());
        result = prime * result + ((residenceCity == null) ? 0 : residenceCity.hashCode());
        result = prime * result + ((residenceCAP == null) ? 0 : residenceCAP.hashCode());
        result = prime * result + ((residenceProvince == null) ? 0 : residenceProvince.hashCode());
        result = prime * result + ((residenceStreet == null) ? 0 : residenceStreet.hashCode());
        result = prime * result + residenceHouseNumber;
        result = prime * result + ((telephoneNumber1 == null) ? 0 : telephoneNumber1.hashCode());
        result = prime * result + ((telephoneNumber2 == null) ? 0 : telephoneNumber2.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
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
        Operator other = (Operator) obj;
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
        if (surname == null) {
            if (other.surname != null)
                return false;
        } else if (!surname.equals(other.surname))
            return false;
        if (birthPlace == null) {
            if (other.birthPlace != null)
                return false;
        } else if (!birthPlace.equals(other.birthPlace))
            return false;
        if (birthday == null) {
            if (other.birthday != null)
                return false;
        } else if (!birthday.equals(other.birthday))
            return false;
        if (residenceCity == null) {
            if (other.residenceCity != null)
                return false;
        } else if (!residenceCity.equals(other.residenceCity))
            return false;
        if (residenceCAP == null) {
            if (other.residenceCAP != null)
                return false;
        } else if (!residenceCAP.equals(other.residenceCAP))
            return false;
        if (residenceProvince == null) {
            if (other.residenceProvince != null)
                return false;
        } else if (!residenceProvince.equals(other.residenceProvince))
            return false;
        if (residenceStreet == null) {
            if (other.residenceStreet != null)
                return false;
        } else if (!residenceStreet.equals(other.residenceStreet))
            return false;
        if (residenceHouseNumber != other.residenceHouseNumber)
            return false;
        if (telephoneNumber1 == null) {
            if (other.telephoneNumber1 != null)
                return false;
        } else if (!telephoneNumber1.equals(other.telephoneNumber1))
            return false;
        if (telephoneNumber2 == null) {
            if (other.telephoneNumber2 != null)
                return false;
        } else if (!telephoneNumber2.equals(other.telephoneNumber2))
            return false;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Operator [fiscalCode=" + fiscalCode + ", name=" + name + ", surname=" + surname + ", birthPlace="
                + birthPlace + ", birthday=" + birthday + ", residenceCity=" + residenceCity + ", residenceCAP="
                + residenceCAP + ", residenceProvince=" + residenceProvince + ", residenceStreet=" + residenceStreet
                + ", residenceHouseNumber=" + residenceHouseNumber + ", telephoneNumber1=" + telephoneNumber1
                + ", telephoneNumber2=" + telephoneNumber2 + ", email=" + email + "]";
    }

}
