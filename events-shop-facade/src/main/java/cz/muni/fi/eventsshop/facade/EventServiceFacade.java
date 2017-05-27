package cz.muni.fi.eventsshop.facade;

import cz.muni.fi.eventsshop.exceptions.InternalException;
import cz.muni.fi.eventsshop.model.EventService;

import java.util.List;

/**
 * Created by pato on 26.5.2017.
 */
public interface EventServiceFacade {
    EventService createEventService(EventService data) throws InternalException;

    List<EventService> getAllEventServices() throws InternalException;

    EventService getEventServiceById(long id) throws InternalException;

    void updateEventService(long id, EventService data) throws InternalException;

    void deleteEventService(long id) throws InternalException;
}
