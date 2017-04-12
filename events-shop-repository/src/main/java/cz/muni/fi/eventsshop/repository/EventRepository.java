package cz.muni.fi.eventsshop.repository;

import cz.muni.fi.eventsshop.model.Event;

import javax.enterprise.context.ApplicationScoped;
import java.util.Arrays;
import java.util.List;

/**
 *
 */
@ApplicationScoped
public class EventRepository {

	public List<Event> getEvents() {
		return Arrays.asList(
				new Event(1, "Birthday party"),
				new Event(2, "Bowling"),
				new Event(3, "Wedding")
		);
	}
}
