package cz.muni.fi.eventsshop.facade;

import cz.muni.fi.eventsshop.exceptions.BeanNotExistsException;
import cz.muni.fi.eventsshop.exceptions.InternalException;
import cz.muni.fi.eventsshop.model.Category;
import cz.muni.fi.eventsshop.service.CategoryService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@ApplicationScoped
public class CategoryFacadeImpl implements CategoryFacade {

	@Inject
	private CategoryService service;

	public Category createCategory(Category data) throws InternalException {
		service.createCategory(data);
		return service.getCategoryById(data.getId());
	}

	public List<Category> getAllCategories() throws InternalException {
		return service.getAllCategories();
	}

	public Category getCategoryById(long id) throws InternalException {
		return service.getCategoryById(id);
	}

	public void updateCategory(long id, Category data) throws InternalException {
		if (service.getCategoryById(id) == null){
			throw new BeanNotExistsException("Category with id "+ id + " does not exist.");
		}
		service.updateCategory(data);
	}

	public void deleteCategory(long id) throws InternalException {
		Category category = service.getCategoryById(id);
		service.deleteCategory(category);
	}
}
