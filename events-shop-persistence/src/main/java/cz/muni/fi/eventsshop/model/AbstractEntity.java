package cz.muni.fi.eventsshop.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by peter on 5/26/17.
 */
@MappedSuperclass
public abstract class AbstractEntity implements Serializable {

    public static final long serialVersionUID = 42L;

    @GeneratedValue
    @Id
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return this.getClass() + " "
                + "id=" + id
                + '}';
    }
}
