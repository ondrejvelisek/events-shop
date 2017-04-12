package cz.muni.fi.eventsshop.model;

/**
 *
 */
public class Event implements HasId {

	private long id;
	private String name;

	public Event(long id, String name) {
		this.id = id;
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}
}
