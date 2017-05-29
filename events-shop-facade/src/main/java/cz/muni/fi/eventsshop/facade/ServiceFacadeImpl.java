package cz.muni.fi.eventsshop.facade;

import cz.muni.fi.eventsshop.exceptions.BeanNotExistsException;
import cz.muni.fi.eventsshop.exceptions.InternalException;
import cz.muni.fi.eventsshop.model.Service;
import cz.muni.fi.eventsshop.service.ServiceService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by patrik.cyprian on 14.5.2017.
 */
@Transactional
@ApplicationScoped
public class ServiceFacadeImpl implements ServiceFacade {

    @Inject
    private ServiceService service;

    @Override
    public Service createService(Service data) throws InternalException {
        service.createService(data);
        return service.getServiceById(data.getId());
    }

    @Override
    public List<Service> getAllServices() throws InternalException {
        return service.getAllServices();
    }

    @Override
    public Service getServiceById(long id) throws InternalException {
        return service.getServiceById(id);
    }

    @Override
    public void updateService(long id, Service data) throws InternalException {
        if (service.getServiceById(id) == null){
            throw new BeanNotExistsException("Service with id "+ id + " does not exist.");
        }
        service.updateService(data);

    }

    @Override
    public void deleteService(long id) throws InternalException {
        Service serv = service.getServiceById(id);
        service.deleteService(serv);

    }
}
