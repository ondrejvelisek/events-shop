package cz.muni.fi.eventsshop.repository;

import cz.muni.fi.eventsshop.model.Service;

import java.util.List;

/**
 * Created by peter on 5/26/17.
 */
public interface ServiceRepository {

    Service create(Service service);

    Service update(Service service);

    Service find(Long serviceId);

    List<Service> findAll();

    void delete(Service service);
}
