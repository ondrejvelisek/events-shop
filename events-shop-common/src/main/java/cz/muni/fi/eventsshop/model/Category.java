package cz.muni.fi.eventsshop.model;

/**
 * Bean representing category of a service.
 */
public class Category extends Bean {

	private String name;
	private String description;

	public Category() {
		// JavaEE JSON parser needs default constructor
	}
        
        // NEVIM ZDA TOTO TU MA BYT?
	public Category(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}


}
