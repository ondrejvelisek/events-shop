package cz.muni.fi.eventsshop.facade;

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
		Category category = service.getCategoryById(id);
		category.adjust(data);
		service.updateCategory(category);
	}

	public void deleteCategory(long id) throws InternalException {
		Category category = service.getCategoryById(id);
		service.deleteCategory(category);
	}
}
