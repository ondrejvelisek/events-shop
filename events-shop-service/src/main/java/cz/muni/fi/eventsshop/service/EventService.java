package cz.muni.fi.eventsshop.service;

import cz.muni.fi.eventsshop.repository.EventRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

/**
 *
 */
@ApplicationScoped
public class EventService {

	@Inject
	private EventRepository eventRepository;

	public List<cz.muni.fi.eventsshop.model.Event> getEvents() {
		return eventRepository.getEvents();
	}

}
