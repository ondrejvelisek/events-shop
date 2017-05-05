package cz.muni.fi.eventsshop.repository;

import cz.muni.fi.eventsshop.exceptions.BeanAlreadyExistsException;
import cz.muni.fi.eventsshop.exceptions.BeanNotExistsException;
import cz.muni.fi.eventsshop.exceptions.InternalException;
import cz.muni.fi.eventsshop.model.Category;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * Stupid implementation using in-memory Map. No transaction or concurrency support.
 */
@ApplicationScoped
public class CategoryRepositoryImpl implements CategoryRepository {

	private Map<Long, Category> categories;

	@PostConstruct
	private void init() {
		categories = new HashMap<>();
		createCategory(new Category("Prostory", "Kde Vaše událost bude probíhat?"));
		createCategory(new Category("Jidlo a pití", "Ke každé společenské události patří něco dobrého k snědku."));
		createCategory(new Category("Zábava", "Čím hosty pobavíme? Hudba, sport, kulturní show, ..."));
	}

	public void createCategory(Category category) {
		if (categories.containsKey(category.getId())) {
			throw new BeanAlreadyExistsException("Category with id "+category.getId()+" already exists.");
		}
		Set<Long> ids = categories.keySet();
		long lastId;
		try {
			lastId = Collections.max(ids);
		} catch (NoSuchElementException e) {
			lastId = 0;
		}
		categories.put(lastId + 1, category);
		category.setId(lastId + 1);
	}

	public List<Category> getAllCategories() {
		return new ArrayList<>(categories.values());
	}

	public Category getCategoryById(long id) {
		if (!categories.containsKey(id)) {
			throw new BeanNotExistsException("Category with id "+id+" was not found.");
		}
		return categories.get(id);
	}

	public void updateCategory(Category category) {
		if (!categories.containsKey(category.getId())) {
			throw new BeanNotExistsException("Updating category "+category+" was not found.");
		}
		categories.put(category.getId(), category);
	}

	public void deleteCategory(Category category) {
		if (!categories.containsKey(category.getId())) {
			throw new BeanNotExistsException("Deleting category "+category+" was not found.");
		}
		categories.remove(category.getId());
	}
}
