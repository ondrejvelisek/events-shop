package cz.muni.fi.eventsshop.model;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
public class Event extends AbstractEntity {

    @NotNull
    @Column(nullable = false)
	private String name;

    @ManyToMany
	private List<EventService> eventServices;

	@DecimalMin("0.00")
    @Column(nullable = false)
	private BigDecimal price = BigDecimal.ZERO;

	@NotNull
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
	private Date dateStart;

	@NotNull
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
	private Date dateEnd;

	@NotNull
    @ManyToOne(optional = false)
	private User client;

	@NotNull
    @Enumerated
	private EventState state = EventState.NEW;

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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
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
