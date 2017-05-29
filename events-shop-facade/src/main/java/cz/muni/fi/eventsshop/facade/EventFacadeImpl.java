package cz.muni.fi.eventsshop.facade;

import cz.muni.fi.eventsshop.exceptions.BeanNotExistsException;
import cz.muni.fi.eventsshop.exceptions.InternalException;
import cz.muni.fi.eventsshop.model.Event;
import cz.muni.fi.eventsshop.service.EventService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by patrik.cyprian on 14.5.2017.
 */
@Transactional
@ApplicationScoped
public class EventFacadeImpl implements EventFacade {

    @Inject
    private EventService service;

    @Override
    public Event createEvent(Event data) throws InternalException {
        service.createEvent(data);
        return service.getEventById(data.getId());
    }

    @Override
    public List<Event> getAllEvents() throws InternalException {
        return service.getAllEvents();
    }

    @Override
    public Event getEventById(long id) throws InternalException {
        return service.getEventById(id);
    }

    @Override
    public void updateEvent(long id, Event data) throws InternalException {
        if (service.getEventById(id) == null){
            throw new BeanNotExistsException("Event with id "+ id + " does not exist.");
        }
        service.updateEvent(data);
    }

    @Override
    public void deleteEvent(long id) throws InternalException {
        Event event = service.getEventById(id);
        service.deleteEvent(event);
    }
}
