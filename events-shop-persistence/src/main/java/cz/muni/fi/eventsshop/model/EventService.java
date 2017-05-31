package cz.muni.fi.eventsshop.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
public class EventService extends AbstractEntity {

    @NotNull
    @ManyToOne
    private Service service;

    @NotNull
    @Column(nullable = false)
    @Min(1)
    private int count;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final EventService other = (EventService) obj;
        if (this.count != other.count) {
            return false;
        }
        if (!Objects.equals(this.service, other.service)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 27;
        hash = 67 * hash + Objects.hashCode(this.service);
        hash = 67 * hash + this.count;
        return hash;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

}
