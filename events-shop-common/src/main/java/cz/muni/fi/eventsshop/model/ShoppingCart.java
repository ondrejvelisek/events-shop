package cz.muni.fi.eventsshop.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Bean representing shopping cart of a customer.
 */
public class ShoppingCart extends Bean {

	private List<Event> events;

	public ShoppingCart() {
		this.events = new ArrayList<>();
	}

	public List<Event> getEvents() {
		return events;
	}

	public BigDecimal getPrice() {
		return events.stream().map(Event::getPrice).reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
	}

}
