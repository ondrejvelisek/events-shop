package cz.muni.fi.eventsshop.service;

import cz.muni.fi.eventsshop.exceptions.InternalException;
import cz.muni.fi.eventsshop.model.ServiceOrder;

import java.util.List;

/**
 * Created by patrik.cyprian on 14.5.2017.
 */
public interface ServiceOrderService {
        void createServiceOrder(ServiceOrder serviceOrder) throws InternalException;

        List<ServiceOrder> getAllServiceOrders() throws InternalException;

        ServiceOrder getServiceOrderById(long id) throws InternalException;

        void updateServiceOrder(ServiceOrder serviceOrder) throws InternalException;

        void deleteServiceOrder(ServiceOrder serviceOrder) throws InternalException;
}
