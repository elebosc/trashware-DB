package it.unibo.trashware.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class OperatingSystemId implements Serializable {
    private static final long serialVersionUID = 2827388438042316998L;
    @Column(name = "IDPC", nullable = false, length = 10)
    private String idpc;

    @Column(name = "Nome", nullable = false, length = 35)
    private String nome;

    @Column(name = "Versione", nullable = false, length = 10)
    private String versione;

    public String getIdpc() {
        return idpc;
    }

    public void setIdpc(String idpc) {
        this.idpc = idpc;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getVersione() {
        return versione;
    }

    public void setVersione(String versione) {
        this.versione = versione;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        OperatingSystemId entity = (OperatingSystemId) o;
        return Objects.equals(this.versione, entity.versione) &&
                Objects.equals(this.nome, entity.nome) &&
                Objects.equals(this.idpc, entity.idpc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(versione, nome, idpc);
    }

}