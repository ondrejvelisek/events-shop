package cz.muni.fi.eventsshop.repository;

import cz.muni.fi.eventsshop.model.Category;

import java.util.List;

/**
 * Created by peter on 5/26/17.
 */
public interface CategoryRepository {
    Category create(Category category);
    Category update(Category category);
    Category find(Long categoryId);
    List<Category> findAll();
    void delete(Category category);
}
