package cz.muni.fi.eventsshop.service.impl;

import cz.muni.fi.eventsshop.exceptions.InternalException;
import cz.muni.fi.eventsshop.model.Event;
import cz.muni.fi.eventsshop.repository.EventRepository;
import cz.muni.fi.eventsshop.service.EventService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

/**
 * Created by patrik.cyprian on 26.5.2017.
 */
@ApplicationScoped
public class EventServiceImpl implements EventService {

    @Inject
    private EventRepository eventRepository;

    @Override
    public void createEvent(Event event) throws InternalException {
        eventRepository.create(event);
    }

    @Override
    public List<Event> getAllEvents() throws InternalException {
        return eventRepository.findAll();
    }

    @Override
    public List<Event> getUpcomingEvents() throws InternalException {
        return eventRepository.upcomingEvents();
    }

    @Override
    public Event getEventById(long id) throws InternalException {
        return eventRepository.find(id);
    }

    @Override
    public void updateEvent(Event event) throws InternalException {
        eventRepository.update(event);
    }

    @Override
    public void deleteEvent(Event event) throws InternalException {
        eventRepository.delete(event);
    }
}
