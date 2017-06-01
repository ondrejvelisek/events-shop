package cz.muni.fi.eventsshop.rest;

import cz.muni.fi.eventsshop.exceptions.InternalException;
import cz.muni.fi.eventsshop.service.DTO.ServiceDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by patrik.cyprian on 14.5.2017.
 */
public interface ServiceRest {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    ServiceDTO createService(ServiceDTO service) throws InternalException;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    List<ServiceDTO> getAllServices() throws InternalException;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    ServiceDTO getServiceById(@PathParam("id") long id) throws InternalException;

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    void updateService(@PathParam("id") long id, ServiceDTO service) throws InternalException;

    @DELETE
    @Path("/{id}")
    void deleteService(@PathParam("id") long id) throws InternalException;
}
