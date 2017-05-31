package cz.muni.fi.eventsshop.rest;

import cz.muni.fi.eventsshop.exceptions.InternalException;
import cz.muni.fi.eventsshop.facade.DTO.EventDTO;
import cz.muni.fi.eventsshop.model.Event;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by patrik.cyprian on 14.5.2017.
 */
public interface EventRest {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    EventDTO createEvent(EventDTO event) throws InternalException;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    List<EventDTO> getAllEvents() throws InternalException;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    EventDTO getEventById(@PathParam("id") long id) throws InternalException;

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    void updateEvent(@PathParam("id") long id, EventDTO event) throws InternalException;

    @DELETE
    @Path("/{id}")
    void deleteEvent(@PathParam("id") long id) throws InternalException;
}
