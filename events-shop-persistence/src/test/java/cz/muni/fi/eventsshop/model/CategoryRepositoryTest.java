package cz.muni.fi.eventsshop.model;

import cz.muni.fi.eventsshop.repository.CategoryRepository;
import javax.inject.Inject;
import javax.validation.ConstraintViolationException;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.Test;

@RunWith(Arquillian.class)
public class CategoryRepositoryTest {

    @Inject
    CategoryRepository categoryRepository;

    Category category;

//    @Deployment
//    public static WebArchive deployment() {
//        return EventsShopTestDeployment.deployment();
//    }
    @Deployment
    public static Archive<?> getDeployment() {
        return ShrinkWrap
                .create(WebArchive.class, "tests.war")
                .addPackages(true, "cz.muni.fi.eventsshop")
                .addAsResource("META-INF/persistence.xml");
    }

    @Before
    public void init() {
        category = new Category();
        category.setName("cars");
        category.setDescription("long description");

    }

    @Test
    public void testCreate() {
        categoryRepository.create(category);
        Assert.assertEquals(category, categoryRepository.find(category.getId()));
    }

    @Test(expected = javax.persistence.PersistenceException.class)
    public void testDoubleCreate() {
        categoryRepository.create(category);
        categoryRepository.create(category);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullCreate() {
        category = null;
        categoryRepository.create(category);
    }

    @Test(expected = ConstraintViolationException.class)
    public void testNoNameCreate() {
        category.setName("");
        categoryRepository.create(category);
    }

    @Test(expected = ConstraintViolationException.class)
    public void testNullNameCreate() {
        category.setName(null);
        categoryRepository.create(category);
    }

    @Test
    public void testUpdate() {
        categoryRepository.create(category);
        category.setName("other");
        categoryRepository.update(category);
        Assert.assertEquals(category, categoryRepository.find(category.getId()));

    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullUpdate() {
        categoryRepository.create(category);
        category = null;
        categoryRepository.update(category);
    }

    @Test(expected = ConstraintViolationException.class)
    public void testNoNameUpdate() {
        categoryRepository.create(category);
        category.setName("");
        categoryRepository.update(category);
    }

    @Test(expected = ConstraintViolationException.class)
    public void testNullNameUpdate() {
        categoryRepository.create(category);
        category.setName(null);
        categoryRepository.update(category);
    }

    @Test
    public void testFind() {
        categoryRepository.create(category);
        Assert.assertEquals(category, categoryRepository.find(category.getId()));
    }

    @Test (expected = IllegalArgumentException.class)
    public void testFindNull() {
        //categoryRepository.create(category);
        categoryRepository.find(null);
    }

    @Test 
    public void testDelete() {
        categoryRepository.create(category);
        categoryRepository.delete(category);
    }
    
    @Test
    public void testDeleteAndFind() {
        categoryRepository.create(category);
        categoryRepository.delete(category);
        Assert.assertNull(categoryRepository.find(category.getId()));
    }

}
