package cz.muni.fi.eventsshop.rest;

import cz.muni.fi.eventsshop.exceptions.InternalException;
import cz.muni.fi.eventsshop.model.Category;
import cz.muni.fi.eventsshop.facade.CategoryFacade;

import javax.inject.Inject;
import javax.ws.rs.Path;
import java.util.List;

/**
 *
 */
@Path("/categories")
public class CategoryRestImpl implements CategoryRest {

	@Inject
	private CategoryFacade facade;

	@Override
	public Category createCategory(Category category) throws InternalException {
		return facade.createCategory(category);
	}

	@Override
	public List<Category> getAllCategories() throws InternalException {
		return facade.getAllCategories();
	}

	@Override
	public Category getCategoryById(long id) throws InternalException {
		return facade.getCategoryById(id);
	}

	@Override
	public void updateCategory(long id, Category category) throws InternalException {
		facade.updateCategory(id, category);
	}

	@Override
	public void deleteCategory(long id) throws InternalException {
		facade.deleteCategory(id);
	}

}

