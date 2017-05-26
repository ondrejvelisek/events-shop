package cz.muni.fi.eventsshop.service.impl;

import cz.muni.fi.eventsshop.model.Category;
import cz.muni.fi.eventsshop.repository.CategoryRepository;
import cz.muni.fi.eventsshop.exceptions.InternalException;
import cz.muni.fi.eventsshop.service.CategoryService;

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
		categoryRepository.create(category);
	}

	public List<Category> getAllCategories() throws InternalException {
		return categoryRepository.findAll();
	}

	public Category getCategoryById(long id) throws InternalException {
		return categoryRepository.find(id);
	}

	public void updateCategory(Category category) throws InternalException {
		categoryRepository.update(category);
	}

	public void deleteCategory(Category category) throws InternalException {
		categoryRepository.delete(category);
	}
}
