package cz.muni.fi.eventsshop.repository;

import cz.muni.fi.eventsshop.model.EventService;

import java.util.List;

/**
 * Created by patrik.cyprian on 26.5.2017.
 */
public interface EventServiceRepository {
    EventService create(EventService eventService);
    EventService update(EventService eventService);
    EventService find(Long eventServiceId);
    List<EventService> findAll();
    void delete(EventService eventService);
}
