package cz.muni.fi.eventsshop.service;

import cz.muni.fi.eventsshop.exceptions.InternalException;
import cz.muni.fi.eventsshop.model.Service;

import java.util.List;

/**
 * Created by patrik.cyprian on 14.5.2017.
 */
public interface ServiceService {
    void createService(Service service) throws InternalException;

    List<Service> getAllServices() throws InternalException;

    Service getServiceById(long id) throws InternalException;

    void updateService(Service service) throws InternalException;

    void deleteService(Service service) throws InternalException;
}
