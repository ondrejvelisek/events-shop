package cz.muni.fi.eventsshop.repository.impl;

import cz.muni.fi.eventsshop.model.Category;
import cz.muni.fi.eventsshop.repository.CategoryRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import javax.transaction.Transactional;

/**
 * Created by peter on 5/26/17.
 */

@ApplicationScoped
@Transactional
public class CategoryRepositoryImpl implements CategoryRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Category create(Category category) {
        manager.persist(category);
        return category;
    }

    @Override
    public Category update(Category category) {
        manager.merge(category);
        return category;
    }

    @Override
    public Category find(Long categoryId) {
        return manager.find(Category.class, categoryId);
    }

    @Override
    public List<Category> findAll() {
        return manager.createQuery("from " + Category.class.getName(), Category.class).getResultList();
    }

    @Override
    public void delete(Category category) {
        //manager.remove(category);
        manager.remove(manager.getReference(Category.class, category.getId()));
    }
}
