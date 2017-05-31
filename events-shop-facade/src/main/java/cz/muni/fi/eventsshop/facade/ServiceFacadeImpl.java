package cz.muni.fi.eventsshop.facade;

import cz.muni.fi.eventsshop.exceptions.BeanNotExistsException;
import cz.muni.fi.eventsshop.exceptions.InternalException;
import cz.muni.fi.eventsshop.facade.DTO.ServiceDTO;
import cz.muni.fi.eventsshop.model.Service;
import cz.muni.fi.eventsshop.service.BeanMappingService;
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

    @Inject
    private BeanMappingService beanMappingService;

    @Override
    public ServiceDTO createService(ServiceDTO data) throws InternalException {
        Service serv = beanMappingService.mapTo(data, Service.class);
        service.createService(serv);
        return  beanMappingService.mapTo(serv, ServiceDTO.class);
    }

    @Override
    public List<ServiceDTO> getAllServices() throws InternalException {
        return beanMappingService.mapTo(service.getAllServices(), ServiceDTO.class);
    }

    @Override
    public ServiceDTO getServiceById(long id) throws InternalException {
        return beanMappingService.mapTo(service.getServiceById(id), ServiceDTO.class);
    }

    @Override
    public void updateService(long id, ServiceDTO data) throws InternalException {
        if (service.getServiceById(id) == null){
            throw new BeanNotExistsException("Service with id "+ id + " does not exist.");
        }
        Service serv = beanMappingService.mapTo(data, Service.class);
        service.updateService(serv);

    }

    @Override
    public void deleteService(long id) throws InternalException {
        Service serv = service.getServiceById(id);
        service.deleteService(serv);

    }
}
