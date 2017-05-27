package cz.muni.fi.eventsshop.service;

import java.util.List;

import cz.muni.fi.eventsshop.model.Category;
import cz.muni.fi.eventsshop.exceptions.InternalException;

/**
 * Service is responsible for bussiness logic
 */
public interface CategoryService {

	void createCategory(Category category) throws InternalException;

	List<Category> getAllCategories() throws InternalException;

	Category getCategoryById(long id) throws InternalException;

	void updateCategory(Category category) throws InternalException;

	void deleteCategory(Category category) throws InternalException;

}
