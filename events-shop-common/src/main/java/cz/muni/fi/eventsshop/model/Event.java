package cz.muni.fi.eventsshop.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Bean representing event which customer is ordering
 */
public class Event extends Bean {

	private String name;
	private List<ServiceOrder> serviceOrders;

	public Event() {
	}

	public Event(String name) {
		this.name = name;
		this.serviceOrders = new ArrayList<>();
	}

	public String getName() {
		return name;
	}

	public List<ServiceOrder> getServiceOrders() {
		return serviceOrders;
	}

        
        //TODO do eventsrepository
//	public BigDecimal getPrice() {
//		return serviceOrders.stream().map(ServiceOrder::getPrice).reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
//	}

}
