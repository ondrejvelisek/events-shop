package cz.muni.fi.eventsshop.facade;

import cz.muni.fi.eventsshop.exceptions.InternalException;
import cz.muni.fi.eventsshop.model.Event;

import java.util.List;

/**
 * Created by patrik.cyprian on 14.5.2017.
 */
public interface EventFacade {

    Event createEvent(Event data) throws InternalException;

    List<Event> getAllEvents() throws InternalException;

    Event getEventById(long id) throws InternalException;

    void updateEvent(long id, Event data) throws InternalException;

    void deleteEvent(long id) throws InternalException;
}
