package cz.muni.fi.eventsshop.model;

import java.math.BigDecimal;
import java.util.List;

/**
 * Bean representing type of service. It is base unit which customer can order.
 */
public class Service extends Bean implements Adjustable<Service> {

	private String name;
	private String description;
	private BigDecimal pricePerPerson;
	private List<Category> categories;

	public Service() {
		// JavaEE JSON parser needs default constructor
	}

	public Service(String name, String description, BigDecimal pricePerPerson, List<Category> categories) {
		this.name = name;
		this.description = description;
		this.pricePerPerson = pricePerPerson;
		this.categories = categories;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public BigDecimal getPricePerPerson() {
		return pricePerPerson;
	}

	public List<Category> getCategories() {
		return categories;
	}

	@Override
	public void adjust(Service bean) {
		this.name = bean.getName();
		this.description = bean.getDescription();
		this.pricePerPerson = bean.getPricePerPerson();
		this.categories = bean.getCategories();
	}

}
