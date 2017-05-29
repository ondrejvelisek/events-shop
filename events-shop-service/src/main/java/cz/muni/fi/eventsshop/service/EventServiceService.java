package cz.muni.fi.eventsshop.service;

import cz.muni.fi.eventsshop.exceptions.InternalException;
import cz.muni.fi.eventsshop.model.EventService;

import java.util.List;

/**
 * Created by patrik.cyprian on 26.5.2017.
 */
public interface EventServiceService {
        void createEventService(EventService eventService) throws InternalException;

        List<EventService> getAllEventServices() throws InternalException;

        EventService getEventServiceById(long id) throws InternalException;

        void updateEventService(EventService eventService) throws InternalException;

        void deleteEventService(EventService eventService) throws InternalException;
}
