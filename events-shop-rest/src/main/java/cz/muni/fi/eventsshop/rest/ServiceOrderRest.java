package cz.muni.fi.eventsshop.rest;

import cz.muni.fi.eventsshop.exceptions.InternalException;
import cz.muni.fi.eventsshop.model.ServiceOrder;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by patrik.cyprian on 14.5.2017.
 */
public interface ServiceOrderRest {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    ServiceOrder createServiceOrder(ServiceOrder data) throws InternalException;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    List<ServiceOrder> getAllServiceOrders() throws InternalException;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    ServiceOrder getServiceOrderById(@PathParam("id") long id) throws InternalException;

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    void updateServiceOrder(@PathParam("id") long id,ServiceOrder serviceOrder) throws InternalException;

    @DELETE
    @Path("/{id}")
    void deleteServiceOrder(@PathParam("id") long id) throws InternalException;
}
