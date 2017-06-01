package cz.muni.fi.eventsshop.facade;

import cz.muni.fi.eventsshop.exceptions.InternalException;
import cz.muni.fi.eventsshop.service.DTO.EventDTO;
import cz.muni.fi.eventsshop.model.Event;

import java.util.List;

/**
 * Created by patrik.cyprian on 14.5.2017.
 */
public interface EventFacade {

    EventDTO createEvent(EventDTO data) throws InternalException;

    List<EventDTO> getAllEvents() throws InternalException;

    List<Event> getUpcomingEvents() throws InternalException;

    EventDTO getEventById(long id) throws InternalException;

    void updateEvent(long id, EventDTO data) throws InternalException;

    void deleteEvent(long id) throws InternalException;
}
