package cz.muni.fi.eventsshop.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

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
