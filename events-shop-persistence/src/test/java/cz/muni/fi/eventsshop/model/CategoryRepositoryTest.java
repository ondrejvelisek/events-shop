package cz.muni.fi.eventsshop.model;

import cz.muni.fi.eventsshop.EventsShopTestDeployment;
import cz.muni.fi.eventsshop.repository.CategoryRepository;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.validation.ConstraintViolationException;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.Test;

@RunWith(Arquillian.class)
public class CategoryRepositoryTest {

    @Inject
    private CategoryRepository categoryRepository;

    private Category category;

    @Deployment
    public static WebArchive deployment() {
        return EventsShopTestDeployment.deployment();
    }

    @Before
    public void init() {
        category = new Category();
        category.setName("cars");
        category.setDescription("long description");
    }    

    @Test
    @Transactional(TransactionMode.ROLLBACK)
    public void testCreate() {
        categoryRepository.create(category);
        Assert.assertEquals(category, categoryRepository.find(category.getId()));
    }

    @Test//(expected = javax.persistence.PersistenceException.class)
    @Transactional(TransactionMode.ROLLBACK)
    public void testDoubleCreate() {
        categoryRepository.create(category);
        categoryRepository.create(category);
    }

    @Test(expected = IllegalArgumentException.class)
    @Transactional(TransactionMode.ROLLBACK)
    public void testNullCreate() {
        category = null;
        categoryRepository.create(category);
    }

    @Test(expected = ConstraintViolationException.class)
    @Transactional(TransactionMode.ROLLBACK)
    public void testNoNameCreate() {
        category.setName("");
        categoryRepository.create(category);
    }

    @Test(expected = ConstraintViolationException.class)
    @Transactional(TransactionMode.ROLLBACK)
    public void testNullNameCreate() {
        category.setName(null);
        categoryRepository.create(category);
    }

    @Test
    @Transactional(TransactionMode.ROLLBACK)
    public void testUpdate() {
        categoryRepository.create(category);
        category.setName("other");
        categoryRepository.update(category);
        Assert.assertEquals(category, categoryRepository.find(category.getId()));

    }

    @Test(expected = IllegalArgumentException.class)
    @Transactional(TransactionMode.ROLLBACK)
    public void testNullUpdate() {
        //categoryRepository.create(category);
        category = null;
        categoryRepository.update(category);
    }

    @Test(expected = ConstraintViolationException.class)
    @Transactional(TransactionMode.ROLLBACK)
    public void testNoNameUpdate() {
        category.setName("");
        categoryRepository.update(category);
    }

    @Test(expected = ConstraintViolationException.class)
    @Transactional(TransactionMode.ROLLBACK)
    public void testNullNameUpdate() {
        category.setName(null);
        categoryRepository.update(category);
    }
    @Test
    @Transactional(TransactionMode.ROLLBACK)
    public void testFind() {
        categoryRepository.create(category);
        Assert.assertEquals(category, categoryRepository.find(category.getId()));
    }

    @Test(expected = IllegalArgumentException.class)
    @Transactional(TransactionMode.ROLLBACK)
    public void testFindNull() {
        categoryRepository.find(null);
    }

    @Test
    @Transactional(TransactionMode.ROLLBACK)
    public void testFindAll() {
        Category category2 = new Category();
        category2.setName("cars2");
        category2.setDescription("long description2");
        categoryRepository.create(category);
        categoryRepository.create(category2);
        List<Category> categoriesList = new ArrayList<>();
        categoriesList.add(category);
        categoriesList.add(category2);
        Assert.assertEquals(categoriesList, categoryRepository.findAll());

    }

    @Test
    @Transactional(TransactionMode.ROLLBACK)
    public void testDelete() {
        categoryRepository.create(category);
        categoryRepository.delete(category);
    }
    
    @Test(expected = IllegalArgumentException.class)
    @Transactional(TransactionMode.ROLLBACK)
    public void testDeleteNull() {
        categoryRepository.delete(null);
    }

    @Test
    @Transactional(TransactionMode.ROLLBACK)
    public void testDeleteAndFind() {
        categoryRepository.create(category);
        categoryRepository.delete(category);
        Assert.assertNull(categoryRepository.find(category.getId()));
    }

}
