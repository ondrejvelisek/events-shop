package cz.muni.fi.eventsshop.facade;


import cz.muni.fi.eventsshop.exceptions.InternalException;
import cz.muni.fi.eventsshop.model.ServiceOrder;
import cz.muni.fi.eventsshop.service.ServiceOrderService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by patrik.cyprian on 14.5.2017.
 */
@Transactional
@ApplicationScoped
public class ServiceOrderFacadeImpl implements ServiceOrderFacade {

    @Inject
    private ServiceOrderService service;

    @Override
    public ServiceOrder createServiceOrder(ServiceOrder data) throws InternalException {
        service.createServiceOrder(data);
        return service.getServiceOrderById(data.getId());
    }

    @Override
    public List<ServiceOrder> getAllServiceOrders() throws InternalException {
        return service.getAllServiceOrders();
    }

    @Override
    public ServiceOrder getServiceOrderById(long id) throws InternalException {
        return service.getServiceOrderById(id);
    }

    @Override
    public void updateServiceOrder(long id, ServiceOrder data) throws InternalException {
        ServiceOrder serviceOrder = service.getServiceOrderById(id);
        serviceOrder.adjust(data);
        service.updateServiceOrder(serviceOrder);
    }

    @Override
    public void deleteServiceOrder(long id) throws InternalException {
        ServiceOrder serviceOrder = service.getServiceOrderById(id);
        service.deleteServiceOrder(serviceOrder);
    }
}
