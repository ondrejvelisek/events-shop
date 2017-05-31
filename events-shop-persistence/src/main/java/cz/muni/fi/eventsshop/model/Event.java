package cz.muni.fi.eventsshop.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.validation.constraints.Size;

@Entity
public class Event extends AbstractEntity {

    @NotNull
    @Size(min = 1, max = 32)
    @Column(nullable = false)
    private String name;

    @OneToMany(fetch = FetchType.EAGER)
    private List<EventService> eventServices = new ArrayList<>();

//	@DecimalMin("0.00")
//    @Column(nullable = false)
//	private BigDecimal price = BigDecimal.ZERO;

    @NotNull
    @Column(nullable = false)
    private String city;

    @NotNull
    @Column(nullable = false)
    private String address;

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

        if (!getName().equals(event.getName())) {
            return false;
        }
        //if (!getPrice().equals(event.getPrice())) return false;
        if (!getDate().equals(event.getDate())) {
            return false;
        }
        return getState() == event.getState();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.name);
        hash = 97 * hash + Objects.hashCode(this.eventServices);
        hash = 97 * hash + Objects.hashCode(this.date);
        hash = 97 * hash + Objects.hashCode(this.client);
        hash = 97 * hash + Objects.hashCode(this.state);
        return hash;
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


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

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
