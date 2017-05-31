package cz.muni.fi.eventsshop.repository;

import cz.muni.fi.eventsshop.model.Event;

import java.util.List;

/**
 * Created by peter on 5/26/17.
 */
public interface EventRepository {

    Event create(Event event);

    Event update(Event event);

    Event find(Long eventId);

    List<Event> findAll();

    List<Event> upcomingEvents();

    void delete(Event event);
}
