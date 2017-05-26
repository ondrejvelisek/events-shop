package cz.muni.fi.eventsshop.rest;

import cz.muni.fi.eventsshop.exceptions.InternalException;
import cz.muni.fi.eventsshop.facade.ServiceOrderFacade;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Path;
import java.util.List;

/**
 * Created by patrik.cyprian on 14.5.2017.
 */
@ApplicationScoped
@Path("/service-orders")
public class ServiceOrderRestImpl implements ServiceOrderRest {

//    @Inject
//    private ServiceOrderFacade facade;
//
//    @Override
//    public ServiceOrder createServiceOrder(ServiceOrder serviceOrder) throws InternalException {
//        return createServiceOrder(serviceOrder);
//    }
//
//    @Override
//    public List<ServiceOrder> getAllServiceOrders() throws InternalException {
//        return facade.getAllServiceOrders();
//    }
//
//    @Override
//    public ServiceOrder getServiceOrderById(long id) throws InternalException {
//        return facade.getServiceOrderById(id);
//    }
//
//    @Override
//    public void updateServiceOrder(long id, ServiceOrder serviceOrder) throws InternalException {
//        facade.updateServiceOrder(id, serviceOrder);
//    }
//
//    @Override
//    public void deleteServiceOrder(long id) throws InternalException {
//        facade.deleteServiceOrder(id);
//    }
}
