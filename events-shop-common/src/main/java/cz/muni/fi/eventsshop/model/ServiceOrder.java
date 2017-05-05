package cz.muni.fi.eventsshop.model;

import java.math.BigDecimal;

/**
 * Bean representing relation between Service and Event.
 */
public class ServiceOrder extends Bean {

	private Service service;
	private int persons;

	public ServiceOrder() {
	}

	public ServiceOrder(Service service, int persons) {
		this.service = service;
		this.persons = persons;
	}

	public Service getService() {
		return service;
	}

	public int getPersons() {
		return persons;
	}

	public BigDecimal getPrice() {
		return service.getPricePerPerson().multiply(BigDecimal.valueOf(persons));
	}

}
