package cz.muni.fi.eventsshop.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
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

    @NotNull
    @Column(nullable = false)
    @DecimalMin("0.00")
    private BigDecimal price;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EventService that = (EventService) o;

        if (getCount() != that.getCount()) {
            return false;
        }
        return getPrice().equals(that.getPrice());
    }

    @Override
    public int hashCode() {
        int hash = 27;
        hash = 67 * hash + Objects.hashCode(this.service);
        hash = 67 * hash + this.count;
        hash = 67 * hash + Objects.hashCode(this.price);
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
