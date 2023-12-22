package it.unibo.trashware.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class OperatingSystemId implements Serializable {
    private static final long serialVersionUID = 2827388438042316998L;
    @Column(name = "IDPC", nullable = false, length = 10)
    private String pcID;

    @Column(name = "Nome", nullable = false, length = 35)
    private String name;

    @Column(name = "Versione", nullable = false, length = 10)
    private String version;

    public String getPcID() {
        return pcID;
    }

    public void setPcID(String idpc) {
        this.pcID = idpc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        OperatingSystemId entity = (OperatingSystemId) o;
        return Objects.equals(this.version, entity.version) &&
                Objects.equals(this.name, entity.name) &&
                Objects.equals(this.pcID, entity.pcID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(version, name, pcID);
    }

}