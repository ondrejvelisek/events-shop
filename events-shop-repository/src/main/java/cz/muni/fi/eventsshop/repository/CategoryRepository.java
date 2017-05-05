package cz.muni.fi.eventsshop.repository;

import cz.muni.fi.eventsshop.exceptions.InternalException;
import cz.muni.fi.eventsshop.model.Category;

import java.util.List;

/**
 * Repository is responsible for persisting and getting beans from data storage.
 */
public interface CategoryRepository {

	void createCategory(Category category) throws InternalException;

	List<Category> getAllCategories() throws InternalException;

	Category getCategoryById(long id) throws InternalException;

	void updateCategory(Category category) throws InternalException;

	void deleteCategory(Category category) throws InternalException;

}
