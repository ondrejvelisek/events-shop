package cz.muni.fi.eventsshop.model;

import java.math.BigDecimal;

/**
 *
 */
public class ServiceOrder implements HasId {

	private long id;
	private Service service;
	private int persons;

	public ServiceOrder(long id, Service service, int persons) {
		this.id = id;
		this.service = service;
		this.persons = persons;
	}

	public long getId() {
		return id;
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
