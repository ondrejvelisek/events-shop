package cz.muni.fi.eventsshop.facade;

import cz.muni.fi.eventsshop.exceptions.BeanNotExistsException;
import cz.muni.fi.eventsshop.exceptions.InternalException;
import cz.muni.fi.eventsshop.facade.DTO.EventDTO;
import cz.muni.fi.eventsshop.model.Event;
import cz.muni.fi.eventsshop.service.BeanMappingService;
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


    @Inject
    private BeanMappingService beanMappingService;

    @Override
    public EventDTO createEvent(EventDTO data) throws InternalException {
        Event event = beanMappingService.mapTo(data, Event.class);
        service.createEvent(event);
        return beanMappingService.mapTo(data, EventDTO.class);
    }

    @Override
    public List<EventDTO> getAllEvents() throws InternalException {
        return beanMappingService.mapTo(service.getAllEvents(), EventDTO.class);
    }

    @Override
    public List<Event> getUpcomingEvents() throws InternalException {
        return service.getUpcomingEvents();
    }

    @Override
    public EventDTO getEventById(long id) throws InternalException {
        return beanMappingService.mapTo(service.getEventById(id), EventDTO.class);
    }

    @Override
    public void updateEvent(long id, EventDTO data) throws InternalException {
        if (service.getEventById(id) == null){
            throw new BeanNotExistsException("Event with id "+ id + " does not exist.");
        }
        Event event = beanMappingService.mapTo(data, Event.class);
        service.updateEvent(event);
    }

    @Override
    public void deleteEvent(long id) throws InternalException {
        Event event = service.getEventById(id);
        service.deleteEvent(event);
    }
}
