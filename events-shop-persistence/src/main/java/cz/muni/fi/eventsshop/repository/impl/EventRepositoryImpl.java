package cz.muni.fi.eventsshop.repository.impl;


import cz.muni.fi.eventsshop.model.Event;
import cz.muni.fi.eventsshop.repository.EventRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by peter on 5/26/17.
 */
@ApplicationScoped
public class EventRepositoryImpl implements EventRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Event create(Event event) {
        manager.persist(event);
        return event;
    }

    @Override
    public Event update(Event event) {
        manager.merge(event);
        return event;
    }

    @Override
    public Event find(Long eventId) {
        return manager.find(Event.class, eventId);
    }

    @Override
    public List<Event> findAll() {
        return manager.createQuery("from " + Event.class.getName(), Event.class).getResultList();
    }

    @Override
    public List<Event> upcomingEvents() {
        Date now = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(now);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        c.add(Calendar.DATE, 3);
        now = c.getTime();
        c.add(Calendar.DATE, 1);
        Date four = c.getTime();
        return manager.createQuery("FROM " + Event.class.getName() + " e WHERE e.date > :date AND e.date <= :four", Event.class)
                .setParameter("date", now)
                .setParameter("four", four)
                .getResultList();
    }

    @Override
    public void delete(Event event) {
        manager.remove(event);
    }
}
