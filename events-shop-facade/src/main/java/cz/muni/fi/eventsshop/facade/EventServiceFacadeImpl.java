package cz.muni.fi.eventsshop.facade;

import cz.muni.fi.eventsshop.exceptions.BeanNotExistsException;
import cz.muni.fi.eventsshop.exceptions.InternalException;
import cz.muni.fi.eventsshop.model.EventService;
import cz.muni.fi.eventsshop.service.EventServiceService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by patrik.cyprian on 26.5.2017.
 */
@Transactional
@ApplicationScoped
public class EventServiceFacadeImpl implements EventServiceFacade {

    @Inject
    private EventServiceService eventServiceService;

    @Override
    public EventService createEventService(EventService data) throws InternalException {
        eventServiceService.createEventService(data);
        return eventServiceService.getEventServiceById(data.getId());
    }

    @Override
    public List<EventService> getAllEventServices() throws InternalException {
        return eventServiceService.getAllEventServices();
    }

    @Override
    public EventService getEventServiceById(long id) throws InternalException {
        return eventServiceService.getEventServiceById(id);
    }

    @Override
    public void updateEventService(long id, EventService data) throws InternalException {
        if (eventServiceService.getEventServiceById(id) == null){
            throw new BeanNotExistsException("EventService with id "+ id + " does not exist.");
        }
        eventServiceService.updateEventService(data);
    }

    @Override
    public void deleteEventService(long id) throws InternalException {
        EventService eventService = eventServiceService.getEventServiceById(id);
        eventServiceService.deleteEventService(eventService);
    }
}
