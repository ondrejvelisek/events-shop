package cz.muni.fi.eventsshop.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 *
 */
public class Event implements HasId {

	private long id;
	private String name;
	private List<ServiceOrder> serviceOrders;

	public Event(long id, String name) {
		this.id = id;
		this.name = name;
		this.serviceOrders = new ArrayList<>();
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public List<ServiceOrder> getServiceOrders() {
		return serviceOrders;
	}

	public BigDecimal getPrice() {
		return serviceOrders.stream().map(ServiceOrder::getPrice).reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
	}
}
