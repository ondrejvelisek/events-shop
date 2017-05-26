package cz.muni.fi.eventsshop.service.impl;

import cz.muni.fi.eventsshop.exceptions.InternalException;
import cz.muni.fi.eventsshop.repository.EventServiceRepository;
import cz.muni.fi.eventsshop.service.EventServiceService;
import cz.muni.fi.eventsshop.model.EventService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

/**
 * Created by patrik.cyprian on 26.5.2017.
 */
@ApplicationScoped
public class EventServiceServiceImpl implements EventServiceService {

    @Inject
    private EventServiceRepository eventServiceRepository;

    @Override
    public void createEventService(EventService eventService) throws InternalException {
        eventServiceRepository.create(eventService);
    }

    @Override
    public List<EventService> getAllEventServices() throws InternalException {
        return eventServiceRepository.findAll();
    }

    @Override
    public EventService getEventServiceById(long id) throws InternalException {
        return eventServiceRepository.find(id);
    }

    @Override
    public void updateEventService(EventService eventService) throws InternalException {
        eventServiceRepository.update(eventService);
    }

    @Override
    public void deleteEventService(EventService eventService) throws InternalException {
        eventServiceRepository.delete(eventService);
    }
}
