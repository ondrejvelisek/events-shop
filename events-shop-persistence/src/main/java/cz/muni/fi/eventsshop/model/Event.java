package cz.muni.fi.eventsshop.model;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Event extends AbstractEntity {

    @NotNull
    @Column(nullable = false)
    private String name;

    @OneToMany(fetch = FetchType.EAGER)
	private List<EventService> eventServices = new ArrayList<>();

//	@DecimalMin("0.00")
//    @Column(nullable = false)
//	private BigDecimal price = BigDecimal.ZERO;

    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
	private Date date;

    @NotNull
    @ManyToOne(optional = false)
    private User client;

    @NotNull
    @Enumerated
    private EventState state = EventState.NEW;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Event event = (Event) o;


        if (!getName().equals(event.getName())) return false;
        //if (!getPrice().equals(event.getPrice())) return false;
        if (!getDate().equals(event.getDate())) return false;
        return getState() == event.getState();
    }

    @Override
    public int hashCode() {
        int result = getName().hashCode();
        //result = 31 * result + getPrice().hashCode();
        result = 31 * result + getDate().hashCode();
        result = 31 * result + getState().hashCode();
        return result;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<EventService> getEventServices() {
        return eventServices;
    }

    public void setEventServices(List<EventService> eventServices) {
        this.eventServices = eventServices;
    }

//    public BigDecimal getPrice() {
//        return price;
//    }
//
//    public void setPrice(BigDecimal price) {
//        this.price = price;
//    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public EventState getState() {
        return state;
    }

    public void setState(EventState state) {
        this.state = state;
    }

    public enum EventState {
        NEW,
        APPROVED,
        PAID,
        FINISHED,
        CANCELED
    }
}
