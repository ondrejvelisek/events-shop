package cz.muni.fi.eventsshop.rest;

import cz.muni.fi.eventsshop.exceptions.InternalException;
import cz.muni.fi.eventsshop.facade.DTO.ServiceDTO;
import cz.muni.fi.eventsshop.facade.ServiceFacade;
import cz.muni.fi.eventsshop.model.Service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Path;
import java.util.List;

/**
 * Created by patrik.cyprian on 14.5.2017.
 */
@ApplicationScoped
@Path("/services")
public class ServiceRestImpl implements ServiceRest {

    @Inject
    private ServiceFacade facade;

    @Override
    public ServiceDTO createService(ServiceDTO service) throws InternalException {
        return facade.createService(service);
    }

    @Override
    public List<ServiceDTO> getAllServices() throws InternalException {
        return facade.getAllServices();
    }

    @Override
    public ServiceDTO getServiceById(long id) throws InternalException {
        return facade.getServiceById(id);
    }

    @Override
    public void updateService(long id, ServiceDTO service) throws InternalException {
        facade.updateService(id, service);
    }

    @Override
    public void deleteService(long id) throws InternalException {
        facade.deleteService(id);
    }
}
