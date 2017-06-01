package cz.muni.fi.eventsshop.facade;

import cz.muni.fi.eventsshop.exceptions.InternalException;
import cz.muni.fi.eventsshop.service.DTO.ServiceDTO;

import java.util.List;

/**
 * Created by patrik.cyprian on 14.5.2017.
 */
public interface ServiceFacade {

    ServiceDTO createService(ServiceDTO data) throws InternalException;

    List<ServiceDTO> getAllServices() throws InternalException;

    ServiceDTO getServiceById(long id) throws InternalException;

    void updateService(long id, ServiceDTO data) throws InternalException;

    void deleteService(long id) throws InternalException;
}
