package cz.muni.fi.eventsshop.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class ShoppingCart implements HasId {

	private long id;
	private List<Event> events;

	public ShoppingCart(long id) {
		this.id = id;
		this.events = new ArrayList<>();
	}

	public long getId() {
		return id;
	}

	public List<Event> getEvents() {
		return events;
	}

	public BigDecimal getPrice() {
		return events.stream().map(Event::getPrice).reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
	}
}
