package cz.muni.fi.eventsshop.rest;

import cz.muni.fi.eventsshop.exceptions.InternalException;
import cz.muni.fi.eventsshop.model.Category;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Rest is responsible for formating and parsing data.
 * It does not manage Transaction so it SHOULD call only ONE facade method.
 */
public interface CategoryRest {

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	Category createCategory(Category category) throws InternalException;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	List<Category> getAllCategories() throws InternalException;

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	Category getCategoryById(@PathParam("id") long id) throws InternalException;

	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	void updateCategory(@PathParam("id") long id, Category category) throws InternalException;

	@DELETE
	@Path("/{id}")
	void deleteCategory(@PathParam("id") long id) throws InternalException;

}
