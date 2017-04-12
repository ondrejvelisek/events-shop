package cz.muni.fi.eventsshop.rest;

import cz.muni.fi.eventsshop.model.Event;
import cz.muni.fi.eventsshop.service.EventService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 *
 */
@Path("/events")
public class EventRest {

	@Inject
	private EventService eventService;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Event> getMessages() {
		return eventService.getEvents();
	}

}

