package cz.muni.fi.eventsshop.service.impl;

import cz.muni.fi.eventsshop.exceptions.InternalException;
import cz.muni.fi.eventsshop.model.Service;
import cz.muni.fi.eventsshop.repository.ServiceRepository;
import cz.muni.fi.eventsshop.service.ServiceService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

/**
 * Created by patrik.cyprian on 26.5.2017.
 */
@ApplicationScoped
public class ServiceServiceImpl implements ServiceService {

    @Inject
    private ServiceRepository serviceRepository;

    @Override
    public void createService(Service service) throws InternalException {
        serviceRepository.create(service);
    }

    @Override
    public List<Service> getAllServices() throws InternalException {
        return serviceRepository.findAll();
    }

    @Override
    public Service getServiceById(long id) throws InternalException {
        return serviceRepository.find(id);
    }

    @Override
    public void updateService(Service service) throws InternalException {
        serviceRepository.update(service);
    }

    @Override
    public void deleteService(Service service) throws InternalException {
        serviceRepository.delete(service);
    }


}
