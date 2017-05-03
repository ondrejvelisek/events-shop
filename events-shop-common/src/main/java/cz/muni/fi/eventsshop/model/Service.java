package cz.muni.fi.eventsshop.model;

import java.math.BigDecimal;
import java.util.List;

/**
 *
 */
public class Service implements HasId {

	private long id;
	private String name;
	private String description;
	private BigDecimal pricePerPerson;
	private List<Category> categories;

	public Service(long id, String name, String description, BigDecimal pricePerPerson, List<Category> categories) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.pricePerPerson = pricePerPerson;
		this.categories = categories;
	}

	public long getId() {
		return id;
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
}
