package cz.muni.fi.eventsshop.rest;

import cz.muni.fi.eventsshop.exceptions.InternalException;
import cz.muni.fi.eventsshop.facade.DTO.EventDTO;
import cz.muni.fi.eventsshop.facade.EventFacade;
import cz.muni.fi.eventsshop.model.Event;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Path;
import java.util.List;

/**
 * Created by patrik.cyprian on 14.5.2017.
 */
@ApplicationScoped
@Path("/events")
public class EventRestImpl implements EventRest {

    @Inject
    private EventFacade facade;

    @Override
    public EventDTO createEvent(EventDTO event) throws InternalException {
        return facade.createEvent(event);
    }

    @Override
    public List<EventDTO> getAllEvents() throws InternalException {
        return facade.getAllEvents();
    }

    @Override
    public EventDTO getEventById(long id) throws InternalException {
        return facade.getEventById(id);
    }

    @Override
    public void updateEvent(long id, EventDTO event) throws InternalException {
        facade.updateEvent(id, event);
    }

    @Override
    public void deleteEvent(long id) throws InternalException {
        facade.deleteEvent(id);
    }
}
