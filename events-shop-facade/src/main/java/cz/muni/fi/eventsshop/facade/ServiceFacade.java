package cz.muni.fi.eventsshop.facade;

import cz.muni.fi.eventsshop.exceptions.InternalException;
import cz.muni.fi.eventsshop.model.Service;

import java.util.List;

/**
 * Created by patrik.cyprian on 14.5.2017.
 */
public interface ServiceFacade {

    Service createService(Service data) throws InternalException;

    List<Service> getAllServices() throws InternalException;

    Service getServiceById(long id) throws InternalException;

    void updateService(long id, Service data) throws InternalException;

    void deleteService(long id) throws InternalException;
}
