package cz.muni.fi.eventsshop.service;

import cz.muni.fi.eventsshop.exceptions.InternalException;
import cz.muni.fi.eventsshop.model.Category;
import cz.muni.fi.eventsshop.repository.CategoryRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

/**
 * Created by ondrejvelisek on 4.5.17.
 */
@ApplicationScoped
public class CategoryServiceImpl implements CategoryService {

	@Inject
	private CategoryRepository categoryRepository;

	public void createCategory(Category category) throws InternalException {
		categoryRepository.createCategory(category);
	}

	public List<Category> getAllCategories() throws InternalException {
		return categoryRepository.getAllCategories();
	}

	public Category getCategoryById(long id) throws InternalException {
		return categoryRepository.getCategoryById(id);
	}

	public void updateCategory(Category category) throws InternalException {
		categoryRepository.updateCategory(category);
	}

	public void deleteCategory(Category category) throws InternalException {
		categoryRepository.deleteCategory(category);
	}

}
