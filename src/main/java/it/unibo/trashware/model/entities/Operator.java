package it.unibo.trashware.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "operatore")
public class Operator {
    @Id
    @Column(name = "CodiceFiscale", nullable = false, length = 16, columnDefinition = "char")
    private String fiscalCode;

    @Column(name = "Nome", nullable = false, length = 35)
    private String name;

    @Column(name = "Cognome", nullable = false, length = 35)
    private String surname;

    @Column(name = "Luogo_Nascita", nullable = false, length = 35)
    private String birthplace;

    @Column(name = "Data_Nascita", nullable = false)
    private LocalDate birthday;

    @Column(name = "`Città_Residenza`", nullable = false, length = 35)
    private String residenceCity;

    @Column(name = "CAP_Residenza", nullable = false, length = 5, columnDefinition = "char")
    private String residenceCAP;

    @Column(name = "Provincia_Residenza", nullable = false, length = 2, columnDefinition = "char")
    private String residenceProvince;

    @Column(name = "Via_Residenza", nullable = false, length = 35)
    private String residenceStreet;

    @Column(name = "NumCivico_Residenza", nullable = false)
    private Integer residenceStreetNumber;

    @Column(name = "NumTelefono1", nullable = false, length = 15)
    private String telephoneNumber1;

    @Column(name = "NumTelefono2", length = 15)
    private String telephoneNumber2;

    @Column(name = "Email", length = 256)
    private String email;

    public String getFiscalCode() {
        return fiscalCode;
    }

    public void setFiscalCode(String codiceFiscale) {
        this.fiscalCode = codiceFiscale;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getBirthplace() {
        return birthplace;
    }

    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getResidenceCity() {
        return residenceCity;
    }

    public void setResidenceCity(String residenceCity) {
        this.residenceCity = residenceCity;
    }

    public String getResidenceCAP() {
        return residenceCAP;
    }

    public void setResidenceCAP(String residenceCAP) {
        this.residenceCAP = residenceCAP;
    }

    public String getResidenceProvince() {
        return residenceProvince;
    }

    public void setResidenceProvince(String residenceProvince) {
        this.residenceProvince = residenceProvince;
    }

    public String getResidenceStreet() {
        return residenceStreet;
    }

    public void setResidenceStreet(String residenceStreet) {
        this.residenceStreet = residenceStreet;
    }

    public Integer getResidenceStreetNumber() {
        return residenceStreetNumber;
    }

    public void setResidenceStreetNumber(Integer residenceStreetNumber) {
        this.residenceStreetNumber = residenceStreetNumber;
    }

    public String getTelephoneNumber1() {
        return telephoneNumber1;
    }

    public void setTelephoneNumber1(String telephoneNumber1) {
        this.telephoneNumber1 = telephoneNumber1;
    }

    public String getTelephoneNumber2() {
        return telephoneNumber2;
    }

    public void setTelephoneNumber2(String telephoneNumber2) {
        this.telephoneNumber2 = telephoneNumber2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}