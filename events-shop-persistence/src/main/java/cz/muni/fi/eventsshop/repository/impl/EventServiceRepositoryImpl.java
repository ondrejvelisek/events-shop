package cz.muni.fi.eventsshop.repository.impl;

import cz.muni.fi.eventsshop.model.EventService;
import cz.muni.fi.eventsshop.repository.EventServiceRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by patrik.cyprian on 26.5.2017.
 */
@ApplicationScoped
public class EventServiceRepositoryImpl implements EventServiceRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public EventService create(EventService eventService) {
        manager.persist(eventService);
        return eventService;
    }

    @Override
    public EventService update(EventService eventService) {
        manager.merge(eventService);
        return eventService;
    }

    @Override
    public EventService find(Long eventServiceId) {
        return manager.find(EventService.class, eventServiceId);
    }

    @Override
    public List<EventService> findAll() {
        return manager.createQuery("from " + EventService.class.getName(), EventService.class).getResultList();
    }

    @Override
    public void delete(EventService eventService) {
        manager.remove(eventService);
    }
}
