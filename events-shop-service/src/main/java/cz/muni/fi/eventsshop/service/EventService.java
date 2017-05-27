package cz.muni.fi.eventsshop.service;

import cz.muni.fi.eventsshop.exceptions.InternalException;
import cz.muni.fi.eventsshop.model.Event;

import java.util.List;

/**
 * Created by patrik.cyprian on 14.5.2017.
 */
public interface EventService {
    void createEvent(Event event) throws InternalException;

    List<Event> getAllEvents() throws InternalException;

    Event getEventById(long id) throws InternalException;

    void updateEvent(Event event) throws InternalException;

    void deleteEvent(Event event) throws InternalException;
}
