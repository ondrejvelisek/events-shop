package cz.muni.fi.eventsshop.facade.DTO;

import cz.muni.fi.eventsshop.model.Event;
import cz.muni.fi.eventsshop.model.EventService;
import cz.muni.fi.eventsshop.model.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by pato on 1.6.2017.
 */
public class EventDTO {
    private long id;

    private String name;

    private List<EventService> eventServices = new ArrayList<>();

//	@DecimalMin("0.00")
//    @Column(nullable = false)
//	private BigDecimal price = BigDecimal.ZERO;

    private String city;

    private String address;

    private Date date;

    private User client;

    private Event.EventState state;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Event.EventState getState() {
        return state;
    }

    public void setState(Event.EventState state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "EventDTO{" +
                "name='" + name + '\'' +
                ", eventServices=" + eventServices +
                ", city='" + city + '\'' +
                ", address='" + address + '\'' +
                ", date=" + date +
                ", client=" + client +
                ", state=" + state +
                '}';
    }
}
