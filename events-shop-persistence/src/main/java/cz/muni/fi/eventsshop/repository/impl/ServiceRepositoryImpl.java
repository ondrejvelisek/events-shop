package cz.muni.fi.eventsshop.repository.impl;


import cz.muni.fi.eventsshop.model.Service;
import cz.muni.fi.eventsshop.repository.ServiceRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by peter on 5/26/17.
 */
@ApplicationScoped
public class ServiceRepositoryImpl implements ServiceRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Service create(Service service) {
        manager.persist(service);
        return service;
    }

    @Override
    public Service update(Service service) {
        manager.merge(service);
        return service;
    }

    @Override
    public Service find(Long serviceId) {
        return manager.find(Service.class, serviceId);
    }

    @Override
    public List<Service> findAll() {
        return manager.createQuery("from " + Service.class.getName(), Service.class).getResultList();
    }

    @Override
    public void delete(Service service) {
        manager.remove(service);
    }
}
