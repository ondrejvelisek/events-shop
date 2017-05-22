package cz.muni.fi.eventsshop.facade;

import cz.muni.fi.eventsshop.exceptions.InternalException;
import cz.muni.fi.eventsshop.model.Category;

import java.util.List;

/**
 * Is responsible for Transactions.
 */
public interface CategoryFacade {

	Category createCategory(Category data) throws InternalException;

	List<Category> getAllCategories() throws InternalException;

	Category getCategoryById(long id) throws InternalException;

	void updateCategory(long id, Category data) throws InternalException;

	void deleteCategory(long id) throws InternalException;

}
